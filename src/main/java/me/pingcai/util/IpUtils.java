package me.pingcai.util;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author huangpingcai
 * @since 2018/9/1 16:13
 */
public class IpUtils {

    public static String getIp(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    /**
     * 将 IP 地址转换成无符号整形,MySQL中存储方式为: INT UNSIGNED
     *
     * @param ip
     * @return
     */
    public static long ip2Long(String ip) {
        if (StringUtils.isEmpty(ip)) {
            return 0;
        }
        String[] values = ip.split("\\.");
        if (values.length != 4) {
            throw new IllegalArgumentException("invalid ipV4 string : " + ip);
        }
        long result = 0;
        long t;
        for (int i = 0; i < values.length; i++) {
            t = Long.parseLong(values[i]);
            if (t < 0 || t > 255) {
                throw new IllegalArgumentException("invalid ipV4 string : " + ip);
            }
            result += t << (24 - i * 8);
        }
        return result;
    }

    public static String long2Ip(long longIp) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.valueOf((longIp >>> 24))).append(".")
                .append(String.valueOf((longIp & 0x00FFFFFF) >>> 16)).append(".")
                .append(String.valueOf((longIp & 0x0000FFFF) >>> 8)).append(".")
                .append(String.valueOf((longIp & 0x000000FF)));
        return sb.toString();
    }

    public static void main(String[] args) {
        String ip = "127.0.0.1";
        long res = ip2Long(ip);
        System.out.println(res);
        System.out.println(long2Ip(res));
    }
}
