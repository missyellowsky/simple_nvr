package com.recorder.recorder.service.impl;

import com.recorder.recorder.beans.RecordParam;
import com.recorder.recorder.beans.Result;
import com.recorder.recorder.util.RSAEncrypt;
import de.onvif.beans.CameraPojo;
import com.recorder.recorder.service.FFmpegService;
import com.recorder.recorder.util.componentUtils.RedisDataUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import java.io.*;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.recorder.recorder.beans.constant.Constant.FLV;
import static com.recorder.recorder.beans.constant.Constant.HLS;
import static com.recorder.recorder.util.CommenUtils.generateShortUuid;

@Service
@Slf4j
public class FFmpegServiceImpl implements FFmpegService {

    @Value("${record.path}")
    private String path;

    @Value("${record.compensate}")
    private Long compensate;

    @Value("${ffmpeg.path}")
    private String ffmpegPath;

    @Value("${rsa.publicKey}")
    private String publicKey;

    @Value("${rsa-member}")
    private String member;

    @Value("${config.push_port}")
    private String port;

    @Value("${config.play_port}")
    private String playPort;

    @Autowired
    private RedisDataUtil redisDataUtil;

    ExecutorService executorService = Executors.newFixedThreadPool(5);

    @Override
    public Result playRecord(RecordParam recordParam) {
        Map<String, Long> map = getRecordFile(recordParam.getCameraIp(), recordParam.getStartTime(), recordParam.getEndTime());
        if (map == null) {
            return Result.fail("no record video, please check the record time");
        }
        String file = map.get("file").toString();
        Long videoLength = map.get("videoLength");
        Long skip = map.get("skip");
        file = path + "/" + recordParam.getCameraIp() + "-" + file + ".flv";
        String url = startRecord(file, videoLength, skip, recordParam);
        return Result.success(url);
    }

    private Map<String, Long> getRecordFile(String ip, String startTime, String endTime) {
        Long startDate = Long.parseLong(startTime.substring(0, 10)) + compensate;
        Long endDate = Long.parseLong(endTime.substring(0, 10)) + compensate;
        if (startDate > endDate) {
            return null;
        }
        Map<String, Long> result = new HashMap<>();
        Long videoLength = endDate - startDate;
        result.put("videoLength", videoLength);
        System.out.println(path);
        File file = new File(path);
        FilenameFilter fileNameFilter = new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                int lastIndex = name.indexOf('-');
                String str = name.substring(0, lastIndex);
                if (str.equals(ip)) {
                    return true;
                }
                return false;
            }
        };
        File[] files = file.listFiles(fileNameFilter);
        System.out.println(files);
        List<Long> names = new ArrayList<>();
        for (File file1 :
                files) {
            String[] nameArray = file1.getName().split("-|\\.");
            if (nameArray.length < 6) {
                continue;
            }
            names.add(Long.parseLong(nameArray[4]));
        }
        names.add(startDate);
        names.add(endDate);
        int count = Collections.frequency(names, startDate);
        if (count > 1) {
            result.put("file", startDate);
            result.put("skip", 0L);
            return result;
        }
        names.sort(Comparator.comparingLong(Long::longValue));
        if (names.indexOf(endDate) < 2) {
            return null;
        }
        if (names.get(names.indexOf(endDate) - 1) == startDate) {
            result.put("file", names.get(names.indexOf(startDate) - 1));
            result.put("skip", startDate - names.get(names.indexOf(startDate) - 1));
            return result;
        } else {
            if ((endDate - names.get(names.indexOf(endDate) - 1) > (endDate - startDate) / 2) || names.indexOf(startDate) == 0) {
                result.put("file", names.get(names.indexOf(endDate) - 1));
                result.put("skip", 0L);
                return result;
            } else {
                result.put("file", names.get(names.indexOf(startDate) - 1));
                result.put("skip", startDate - names.get(names.indexOf(startDate) - 1));
                return result;
            }
        }
    }

    public String startRecord(String file, Long videoLength, Long skip, RecordParam recordParam) {
        CameraPojo cameraPojo = getCameraByIp(recordParam.getCameraIp());
        List<String> hosts = cameraPojo.getHost();
        String id = generateShortUuid();
        //String id = "123";
        List<String> urls = new ArrayList<>();
        String rsa = null;
        try {
            rsa = java.net.URLEncoder.encode(java.net.URLEncoder.encode(java.net.URLEncoder.encode(RSAEncrypt.encrypt(member + "-" + System.currentTimeMillis(), publicKey), "UTF-8"),"UTF-8"),"UTF-8");
        } catch (Exception e) {
            rsa = "";
        }
        String url = chooseStreamType(recordParam.getStreamType(), cameraPojo.getPlayHost(), recordParam.getCameraIp(),id);
        String finalRsa = rsa;
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                for (String host :
                        hosts) {
                    String output = "rtmp://" + host + ":" + port + "/recorder/" + id + "?pass=" + finalRsa;
                    //组装command
                    String command = assembleCommand(file, videoLength, skip, output);
                    System.out.println(command);
                    //执行command
                    try {
                        exec(command);
                    } catch (IOException e) {
                        log.error("执行命令失败: " + command);
                        log.error("执行命令失败原因: " + e.getMessage());
                    }
                }
            }
        });
        try {
            Thread.sleep(200L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return url;
    }

    private String chooseStreamType(String type, String host, String ip,String id) {
        String newUrl;
        switch (type) {
            case HLS:
                newUrl = "http://" + host + ":" + playPort + "/recordhls/" + id + "/index.m3u8";
                break;
            case FLV:
                newUrl = "http://" + host + ":" + playPort + "/live?port=" + port + "&app=recorder&stream=" + id;
                break;
            default:
                newUrl = "rtmp://" + host + ":" + port + "/recorder/" + id;
        }
        return newUrl;
    }

    /**
     * 组装命令
     *
     * @return
     */
    private String assembleCommand(String file, Long videoLength, Long skip, String url) {
        try {
            if (!StringUtils.isEmpty(ffmpegPath)) {
                String ffmpeg = ffmpegPath + "ffmpeg";
                // -i：输入流地址或者文件绝对地址
                StringBuilder comm = new StringBuilder(ffmpeg + " -ss " + skip + " -i ");
                // 输入地址，输出地址，应用名，twoPart：0-推一个元码流；1-推一个自定义推流；2-推两个流（一个是自定义，一个是元码）
                String input = file;
                String output = url;
                String twoPart = "1";
                /*if (urls.size() > 1) {
                    twoPart = "2";
                }*/
                // 默认h264解码
                String codec = "libx264";
                // 输入地址
                comm.append(input + " -t " + videoLength);
                // 当twoPart为0时，只推一个元码流
                comm.append(" -vcodec " + codec);

                // -f ：转换格式，默认flv
                String fmt = "flv";
                comm.append(" -f " + fmt);
                // -r :帧率，默认25；-g :帧间隔
                String fps = "50";
                comm.append(" -r " + fps);
                comm.append(" -g " + fps);
                // -s 分辨率 默认是原分辨率
                /*String rs = "640x360";
                comm.append(" -s " + rs);*/
                // 输出地址+发布的应用名
                comm.append(" " + output);
                // 当twoPart为2时推两个流，一个自定义流，一个元码流
               /* if ("2".equals(twoPart)) {
                    // 一个视频源，可以有多个输出，第二个输出为拷贝源视频输出，不改变视频的各项参数
                    comm.append(" -vcodec copy  -f flv ").append(urls.get(1));
                }*/

                return comm.toString();

            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }

    private CameraPojo getCameraByIp(String ip) {
        List<CameraPojo> cameraPojoList = redisDataUtil.getCameraHostFromRedis();
        CameraPojo cameraPojo = new CameraPojo();
        cameraPojo.setIp(ip);
        CameraPojo cameraPojoRedis = cameraPojoList.get(cameraPojoList.indexOf(cameraPojo));
        return cameraPojoRedis;
    }

    /**
     * 执行命令行并获取进程
     *
     * @param cmd
     * @return
     * @throws IOException
     */
    public static void exec(String cmd) throws IOException {
        Runtime runtime = Runtime.getRuntime();
        Process process = runtime.exec(cmd);// 执行命令获取主进程
    }

}
