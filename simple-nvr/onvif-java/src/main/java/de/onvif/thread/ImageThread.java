package de.onvif.thread;

import de.onvif.cache.CacheUtil;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.bytedeco.javacv.OpenCVFrameConverter;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ImageThread {
    public static class MyRunnable implements Runnable {
        // 创建线程池
        public static ExecutorService es = Executors.newCachedThreadPool();

        String path=Thread.currentThread().getContextClassLoader().getResource("").getPath().substring(1).replace("/", "\\\\");


        private Thread nowThread;

        Frame frame;

        public Frame getFrame() {
            return frame;
        }

        public void setFrame(Frame frame) {
            this.frame = frame;
        }

        public MyRunnable(Frame frame){
            this.frame = frame;
        }

        // 中断线程
        public void setInterrupted(String key) {
            CacheUtil.PUSHMAP.get(key).setExitcode(1);
        }

        OpenCVFrameConverter.ToIplImage convertToMat = new OpenCVFrameConverter.ToIplImage();

        @Override
        public void run() {
            // 直播流
            try {
                // 获取当前线程存入缓存
                nowThread = Thread.currentThread();
                imageCut1( path);
            } catch (Exception e) {

            }
        }

        public void imageCut1(String path) {
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    if (null ==frame ||null ==frame) {
                        return;
                    }
                    Java2DFrameConverter converter =new Java2DFrameConverter();
                    try {
                        BufferedImage bi =converter.getBufferedImage(frame);
                        String ranstr= UUID.randomUUID().toString();
                        File output =new File(path+ranstr+".png");
                        ImageIO.write(bi,"png",output);
                    }catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            },3000,30000);
        }
    }


}
