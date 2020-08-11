package com.trimhelp.starter.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 *  获取Linux数据
 * @author hrimhelp
*/
public class WebToolUtil {

    private static Logger logger = LoggerFactory.getLogger(WebToolUtil.class) ;

    /**
     * 获取Linux下的IP地址
     *
     * @return IP地址
     */
    public static String getLinuxLocalIp() throws SocketException {
        logger.info("获取Linux下的IP地址方法开始");
        String ip = "127.0.0.1";
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
                NetworkInterface intf = en.nextElement();
                String name = intf.getName();
                if (!name.contains("docker") && !name.contains("lo")) {
                    for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {
                        InetAddress inetAddress = enumIpAddr.nextElement();
                        if (!inetAddress.isLoopbackAddress()) {
                            String ipaddress = inetAddress.getHostAddress().toString();
                            if (!ipaddress.contains("::") && !ipaddress.contains("0:0:") && !ipaddress.contains("fe80")) {
                                ip = ipaddress;
                                logger.info("获取Linux下的IP地址：{}",ipaddress);
                            }
                        }
                    }
                }
            }
        } catch (SocketException ex) {
            logger.error("获取Linux下的IP地址出现错误：{}", ex.getMessage());
        }
        logger.info("获取Linux下的IP地址方法结束");
        return ip;
    }
}
