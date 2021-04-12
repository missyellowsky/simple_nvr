//package org.onvif.client;
//
//import de.onvif.discovery.OnvifDiscovery;
//
//import java.net.ConnectException;
//import java.net.URL;
//import java.util.Collection;
//import java.util.Map;
//
//import de.onvif.soap.OnvifDevice;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import javax.xml.soap.SOAPException;
//
//public class DiscoveryTest {
//  private static final Logger LOG = LoggerFactory.getLogger(DiscoveryTest.class);
//
//  public static void main(String[] args) throws SOAPException, ConnectException {
//    /*Collection<URL> urls = OnvifDiscovery.discoverOnvifURLs();
//    for (URL u : urls) {
//      LOG.info(u.toString());
//      OnvifDevice device = new OnvifDevice(u,"admin","1qaz2wsx");
//      String rtsp = device.getStreamUri();
//
//      System.out.println(rtsp);
//    }*/
//
//    Map<String, Collection<URL>> urls = OnvifDiscovery.discoverOnvifURLs("192.168.191.1");
//    for (String key:
//    urls.keySet()) {
//      Collection<URL> urlCollection = urls.get(key);
//      for (URL u : urlCollection) {
//        LOG.info(u.toString());
//        OnvifDevice device = new OnvifDevice(u,"admin","1qaz2wsx");
//        String rtsp = device.getStreamUri();
//        System.out.println(rtsp);
//      }
//    }
//  }
//}
