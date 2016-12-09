package com.lianjia.trace;

import java.io.Serializable;
import java.lang.management.ManagementFactory;

public class Endpoint implements Serializable {
	private static final long serialVersionUID = 1L;
	private String ip;
	private Integer port;
	private String pid;
	private String serviceName;

	public Endpoint() {
	}

	public Endpoint(String ip, Integer port, String pid, String serviceName) {
		this.ip = ip;
		this.port = port;
		this.pid = pid;
		this.serviceName = serviceName;
	}

	public static Endpoint create(String ip, Integer port, String serviceName) {
		String name = ManagementFactory.getRuntimeMXBean().getName();
		String pid = (name != null) ? name.split("@")[0] : "0";
		return new Endpoint(ip, port, pid, serviceName);
	}

	public String getIp() {
		return ip;
	}

	public Integer getPort() {
		return port;
	}

	public String getPid() {
		return pid;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
}
