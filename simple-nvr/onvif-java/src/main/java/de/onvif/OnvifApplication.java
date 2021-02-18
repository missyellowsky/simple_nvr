package de.onvif;

import de.onvif.cache.CacheUtil;
import de.onvif.push.CameraPush;
import de.onvif.push.CameraPushWithWatermark;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.FFmpegFrameRecorder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.ApplicationContext;

import java.util.Date;

@SpringBootApplication
public class OnvifApplication {
    public static void main(String[] args) {

        // 将服务启动时间存入缓存
        CacheUtil.STARTTIME = new Date().getTime();
        final ApplicationContext applicationContext = SpringApplication.run(OnvifApplication.class, args);
        // 将上下文传入RealPlay类中,以使其使用config中的变量
        CameraPushWithWatermark.setApplicationContext(applicationContext);
        CameraPush.setApplicationContext(applicationContext);
        // 服务启动执行FFmpegFrameGrabber和FFmpegFrameRecorder的tryLoad()，以免导致第一次推流时耗时。
        /*try {
            FFmpegFrameGrabber.tryLoad();
            FFmpegFrameRecorder.tryLoad();
        } catch (org.bytedeco.javacv.FrameRecorder.Exception e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }*/

    }
}
