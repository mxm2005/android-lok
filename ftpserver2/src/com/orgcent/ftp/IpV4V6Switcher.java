package com.orgcent.ftp;

import java.net.InetAddress;
import java.lang.reflect.Field;
import java.lang.reflect.Constructor;

public class IpV4V6Switcher {

	private Field implField;
	private Field flagField;

	private Object v4Impl;
	private Object v6Impl;

	public IpV4V6Switcher() throws Exception {
		Class klass = InetAddress.class;
		implField = klass.getDeclaredField("impl");
		implField.setAccessible(true);

		flagField = InetAddress.class.getDeclaredField("preferIPv6Address");
		flagField.setAccessible(true);

		klass = Class.forName("java.net.Inet4AddressImpl");
		Constructor con = klass.getDeclaredConstructor();
		con.setAccessible(true);
		v4Impl = con.newInstance();

		klass = Class.forName("java.net.Inet6AddressImpl");
		con = klass.getDeclaredConstructor();
		con.setAccessible(true);
		v6Impl = con.newInstance();
	}

	public boolean isV4() throws Exception {
		return !isV6();
	}

	public boolean isV6() throws Exception {
		return (Boolean) flagField.get(null);
	}

	public void setV4() throws Exception {
		if (isV6()) {
			implField.set(null, v4Impl);
			flagField.set(null, false);
		}
	}

	public void setV6() throws Exception {
		if (isV4()) {
			implField.set(null, v6Impl);
			flagField.set(null, true);
		}
	}

}