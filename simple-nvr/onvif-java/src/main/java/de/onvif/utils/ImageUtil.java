package de.onvif.utils;

import de.onvif.thread.ImageThread;
import org.bytedeco.javacv.*;
import org.bytedeco.opencv.global.opencv_imgproc;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Rect;
import org.bytedeco.opencv.opencv_core.RectVector;
import org.bytedeco.opencv.opencv_core.Scalar;
import org.bytedeco.opencv.opencv_objdetect.CascadeClassifier;
import org.opencv.imgproc.Imgproc;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;

import javax.swing.*;

@EnableAsync
public class ImageUtil {

    @Async
    public void setFrame(ImageThread.MyRunnable job, Frame frame){
        job.setFrame(frame);
    }

    static {
        System.load("D:\\soft\\opencv\\opencv\\build\\java\\x64\\opencv_java401.dll");
    }
    /**
     * 测试自带人脸识别，视频识别
     * <p>Title: detectFace</p>
     * <p>Description: </p>
     * @author 陈阳
     * @date 2019年1月25日
     * @throws org.bytedeco.javacv.FrameGrabber.Exception
     * @throws InterruptedException
     */
    public static void detectFace() throws org.bytedeco.javacv.FrameGrabber.Exception, InterruptedException
    {
        //连接摄像头
        FFmpegFrameGrabber grabber = FFmpegFrameGrabber.createDefault("rtsp://admin:1qaz2wsx@10.88.40.250:554/Streaming/Channels/101?transportmode=unicast&profile=Profile_1");
        grabber.setOption("rtsp_transport", "tcp"); // 使用tcp的方式，不然会丢包很严重
        grabber.setOption("fflags", "nobuffer"); //
        // 一直报错的原因！！！就是因为是 2560 * 1440的太大了。。
        grabber.setImageWidth(780);
        grabber.setImageHeight(580);
        grabber.start();

        OpenCVFrameConverter.ToMat convertToMat = new OpenCVFrameConverter.ToMat();
        //加载检测器
        CascadeClassifier face_cascade = new CascadeClassifier(System.getProperty("user.dir")+"\\lib\\haarcascades\\haarcascade_frontalface_alt.xml");//初始化人脸检测器
        CascadeClassifier eye_cascade = new CascadeClassifier(System.getProperty("user.dir")+"\\lib\\haarcascades\\haarcascade_eye_tree_eyeglasses.xml");//初始化眼部检测器
        //定义人脸集合，矩形集合
        RectVector faces = new  RectVector();
        RectVector eyes = new  RectVector();

        CanvasFrame canvas = new CanvasFrame("人脸识别",1);//新建一个窗口
        canvas.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        canvas.setAlwaysOnTop(true);
        Frame frame;
        Mat mat;
        Mat videoMatGray;
        //未关闭就一直向窗口刷新图片。就是视频
        while (true) {
            //
            if (!canvas.isVisible()) {
                break;
            }
            frame = grabber.grabImage();

            //获取当前帧图片
            mat = convertToMat.convert(frame);
            if (mat == null || mat.empty())
                continue;
            //当前帧图片进行灰度+直方均衡
            videoMatGray = new  Mat();

            Mat finalVideoMatGray = videoMatGray;
            Frame finalFrame = frame;
            Mat finalMat = mat;
            CommenUtils.getConPool().execute(new Runnable() {
                @Override
                public void run() {
                    opencv_imgproc.cvtColor(finalMat, finalVideoMatGray, Imgproc.COLOR_BGRA2GRAY);
                    opencv_imgproc.equalizeHist(finalVideoMatGray, finalVideoMatGray);

                    //使用检测器进行检测，把结果放进集合中
                    face_cascade.detectMultiScale(finalVideoMatGray, faces);
                    eye_cascade.detectMultiScale(finalVideoMatGray, eyes);

                    //把所有人脸数据绘制到图片中
                    for (int i = 0; i < faces.size(); i++) {
                        Rect face = faces.get(i);
                        opencv_imgproc.rectangle(finalMat, face, Scalar.RED, 1, 8, 0);
                    }
                    for (int i = 0; i < eyes.size(); i++) {
                        Rect eye = eyes.get(i);
                        opencv_imgproc.rectangle(finalMat, eye, Scalar.GREEN, 1, 8, 0);
                    }

                    //把图片刷新到窗口
                    canvas.showImage(convertToMat.convert(finalMat));
                }
            });
            Thread.sleep(30);//30毫秒刷新一次图像
        }
    }

    public static void main(String[] args) throws FrameGrabber.Exception, InterruptedException {
        detectFace();
    }

}
