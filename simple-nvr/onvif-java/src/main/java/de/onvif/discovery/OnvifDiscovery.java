package de.onvif.discovery;

import java.net.URL;
import java.util.Collection;
import java.util.Map;

/**
 * onvif设备发现
 */
public class OnvifDiscovery {

  public static Collection<URL> discoverOnvifURLs() {
    return DeviceDiscovery.discoverWsDevicesAsUrls("^http$", ".*onvif.*");
  }

  public static Map<String, Collection<URL>> discoverOnvifURLs(String net){
    return DeviceDiscovery.discoverWsDevicesAsUrlsByUrl(net, "^http$", ".*onvif.*");
  }
}
