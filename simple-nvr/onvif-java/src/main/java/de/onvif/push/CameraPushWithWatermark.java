package de.onvif.push;

import de.onvif.beans.CameraPojo;
import de.onvif.beans.Config;
import lombok.extern.slf4j.Slf4j;
import org.bytedeco.ffmpeg.global.avcodec;
import org.bytedeco.ffmpeg.global.avutil;
import org.bytedeco.javacv.*;
import org.bytedeco.opencv.global.opencv_imgproc;
import org.bytedeco.opencv.opencv_core.Mat;
import org.springframework.context.ApplicationContext;
import sun.font.FontDesignMetrics;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

@Slf4j
public class CameraPushWithWatermark {
    //private final static Logger logger = LoggerFactory.getLogger(CameraPushWithWatermark.class);
    // 配置类
    private static Config config;

    // 通过applicationContext上下文获取Config类
    public static void setApplicationContext(ApplicationContext applicationContext) {
        config = applicationContext.getBean(Config.class);
    }

    private CameraPojo pojo;// 设备信息
    private FFmpegFrameRecorder recorder;// 解码器
    private FFmpegFrameRecorder recorderExtra;// 第二解码器
    private FFmpegFrameGrabberNew grabber;// 采集器
    private int err_index = 0;// 推流过程中出现错误的次数
    private int exitcode = 0;// 退出状态码：0-正常退出;1-手动中断;
    private double framerate = 0;// 帧率

    public void setExitcode(int exitcode) {
        this.exitcode = exitcode;
    }

    public int getExitcode() {
        return exitcode;
    }
    private final static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public CameraPushWithWatermark(CameraPojo cameraPojo) {
        this.pojo = cameraPojo;
    }

    /**
     * @return void
     * @Title: release
     * @Description:资源释放
     **/
    public void release() {
        try {
            grabber.stop();
            grabber.close();
            if (recorder != null) {
                recorder.stop();
                recorder.release();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @return void
     * @Title: push
     * @Description:推送视频流数据包
     **/
    public void push() {
        try {
            //avutil.av_log_set_level(avutil.AV_LOG_INFO);
            //FFmpegLogCallback.set();
            grabber = new FFmpegFrameGrabberNew(pojo.getRtsp());
            grabber.setOption("rtsp_transport", "tcp");
            // 设置采集器构造超时时间
            grabber.setOption("stimeout", "2000000");
            grabber.setImageWidth(1000);
            grabber.setImageHeight(600);
            grabber.start(config.getMain_code());

            // 部分监控设备流信息里携带的帧率为9000，如出现此问题，会导致dts、pts时间戳计算失败，播放器无法播放，故出现错误的帧率时，默认为25帧
            if (grabber.getFrameRate() > 0 && grabber.getFrameRate() < 100) {
                framerate = grabber.getFrameRate();
            } else {
                framerate = 25;
            }
            int width = grabber.getImageWidth();
            int height = grabber.getImageHeight();
            // 若视频像素值为0，说明拉流异常，程序结束
            if (width == 0 && height == 0) {
                log.error(pojo.getRtsp() + "  拉流异常！");
                grabber.stop();
                grabber.close();
                release();
                return;
            }
            recorder = new FFmpegFrameRecorder(pojo.getRtmp(), grabber.getImageWidth(), grabber.getImageHeight());
            recorder.setInterleaved(true);
            // 关键帧间隔，一般与帧率相同或者是视频帧率的两倍
            recorder.setGopSize((int) framerate);
            // 视频帧率(保证视频质量的情况下最低25，低于25会出现闪屏)
            recorder.setFrameRate(framerate);
            // 设置比特率
            recorder.setVideoBitrate(grabber.getVideoBitrate());
            // 封装flv格式
            recorder.setFormat("flv");
            // h264编/解码器
            recorder.setVideoCodec(avcodec.AV_CODEC_ID_H264);
            recorder.setPixelFormat(avutil.AV_PIX_FMT_YUV420P);
            Map<String, String> videoOption = new HashMap<>();

            // 该参数用于降低延迟
            videoOption.put("tune", "zerolatency");
            /**
             ** 权衡quality(视频质量)和encode speed(编码速度) values(值)： *
             * ultrafast(终极快),superfast(超级快), veryfast(非常快), faster(很快), fast(快), *
             * medium(中等), slow(慢), slower(很慢), veryslow(非常慢) *
             * ultrafast(终极快)提供最少的压缩（低编码器CPU）和最大的视频流大小；而veryslow(非常慢)提供最佳的压缩（高编码器CPU）的同时降低视频流的大小
             */
            videoOption.put("preset", "ultrafast");
            // 画面质量参数，0~51；18~28是一个合理范围
            videoOption.put("crf", "28");
            recorder.setOptions(videoOption);
            //AVFormatContext fc = grabber.getFormatContext();
            recorder.start();
            log.info("开始推流 设备信息：[ip:" + pojo.getIp() + " channel:" + pojo.getChannel() + " stream:"
                    + pojo.getStream() + " starttime:" + pojo.getStarttime() + " endtime:" + pojo.getEndtime()
                    + " rtsp:" + pojo.getRtsp() + " url:" + pojo.getRtmp() + "]");

            // 清空探测时留下的缓存
            grabber.flush();



            //Java2DFrameConverter converter = new Java2DFrameConverter();
            OpenCVFrameConverter.ToIplImage converter = new OpenCVFrameConverter.ToIplImage();
            CanvasFrame canvasFrame = new CanvasFrame("人脸检测");//新建一个窗口
            org.bytedeco.opencv.opencv_core.Point point2 = new org.bytedeco.opencv.opencv_core.Point(100, 100);
            org.bytedeco.opencv.opencv_core.Scalar scalar2 = new org.bytedeco.opencv.opencv_core.Scalar(0, 0, 0, 0);

            while (grabber.grabPacket() != null) {
                String timeContent = sdf.format(new Date());
                Mat mat = converter.convertToMat(grabber.grabImage());
                // 加文字水印，opencv_imgproc.putText（图片，水印文字，文字位置，字体，字体大小，字体颜色，字体粗度，文字反锯齿，是否翻转文字）
                opencv_imgproc.putText(mat, timeContent, point2, opencv_imgproc.CV_FONT_VECTOR0, 1.5, scalar2, 3, 0,
                        false);
                recorder.setTimestamp(grabber.getTimestamp());

                recorder.record(converter.convert(mat));
                canvasFrame.showImage(converter.convert(mat));
               /* Frame frame = grabber.grab();
                BufferedImage bufferedImage = converter.getBufferedImage(frame);
                //bufferedImage = addSubtitle(bufferedImage, testStr.get(i++ % 300));
                // 视频帧赋值，写入输出流
                frame.image = converter.getFrame(bufferedImage).image;
                Frame rotatedFrame=converter.convert(bufferedImage);

                recorder.setTimestamp(grabber.getTimestamp());
                //canvasFrame.showImage(converter.getFrame(bufferedImage));
                recorder.record(converter.getFrame(bufferedImage));*/
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        } finally {
            release();
            log.info("推流结束 设备信息：[ip:" + pojo.getIp() + " channel:" + pojo.getChannel() + " stream:"
                    + pojo.getStream() + " starttime:" + pojo.getStarttime() + " endtime:" + pojo.getEndtime()
                    + " rtsp:" + pojo.getRtsp() + " url:" + pojo.getUrl() + "]");
        }
    }


    private static BufferedImage addSubtitle(BufferedImage bufImg, String subTitleContent) {

        // 添加字幕时的时间
        Font font = new Font("微软雅黑", Font.BOLD, 32);
        String timeContent = sdf.format(new Date());
        FontDesignMetrics metrics = FontDesignMetrics.getMetrics(font);
        Graphics2D graphics = bufImg.createGraphics();
        graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));

        //设置图片背景
        graphics.drawImage(bufImg, 0, 0, bufImg.getWidth(), bufImg.getHeight(), null);

        //设置左上方时间显示
        graphics.setColor(Color.orange);
        graphics.setFont(font);
        graphics.drawString(timeContent, 0, metrics.getAscent());

        // 计算文字长度，计算居中的x点坐标
        int textWidth = metrics.stringWidth(subTitleContent);
        int widthX = (bufImg.getWidth() - textWidth) / 2;
        graphics.setColor(Color.red);
        graphics.setFont(font);
        graphics.drawString(subTitleContent, widthX, bufImg.getHeight() - 100);
        graphics.dispose();
        return bufImg;
    }
}
