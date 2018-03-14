package com.QQ.util;

import java.net.InetAddress;

public class IpUtil {
	public static String getLocalHostAddress(){
		String hostName;
		try {
			InetAddress addr=InetAddress.getLocalHost();
			hostName=addr.getHostAddress();
		} catch (Exception e) {
			hostName="";
		}
		return hostName;
	}
}
