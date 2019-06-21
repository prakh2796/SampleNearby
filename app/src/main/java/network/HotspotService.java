package network;

import android.content.Context;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;

import java.lang.reflect.Method;

public class HotspotService {
    //check whether wifi hotspot on or off
    public static boolean isApOn(Context context) {
        WifiManager wifimanager = (WifiManager) context.getSystemService(context.WIFI_SERVICE);
        try {
            Method method = wifimanager.getClass().getDeclaredMethod("isWifiApEnabled");
            method.setAccessible(true);
            return (Boolean) method.invoke(wifimanager);
        }
        catch (Throwable ignored) {}
        return false;
    }

    // toggle wifi hotspot on or off
//    public static boolean configApState(Context context) {
//        WifiManager wifimanager = (WifiManager) context.getSystemService(context.WIFI_SERVICE);
//        WifiConfiguration wifiConfiguration = null;
//        try {
//            // if WiFi is on, turn it off
//            if(isApOn(context)) {
//                wifimanager.setWifiEnabled(false);
//            }
//            Method method = wifimanager.getClass().getMethod("setWifiApEnabled", WifiConfiguration.class, boolean.class);
//            wifiConfiguration.SSID = "RescueMe";
//            method.invoke(wifimanager, wifiConfiguration, !isApOn(context));
//            return true;
//        }
//        catch (Exception e) {
//            e.printStackTrace();
//        }
//        return false;
//    }
    //turn hotspot on
    public static boolean turnHotspotOn(Context context) {
        WifiManager wifiManager = (WifiManager) context.getSystemService(context.WIFI_SERVICE);
        wifiManager.setWifiEnabled(false);
        WifiConfiguration wifiConfiguration = getWifiApConfiguration();
        wifiConfiguration.SSID = "RescueMe";
        try {
            Method method = wifiManager.getClass().getMethod("setWifiApEnabled", WifiConfiguration.class, boolean.class);
            method.invoke(wifiManager, wifiConfiguration, true);
            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static WifiConfiguration getWifiApConfiguration() {
        WifiConfiguration conf = new WifiConfiguration();
        conf.SSID =  "RescuMe";
        conf.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.NONE);
        return conf;
    }
}
