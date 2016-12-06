package com.lianjia.trace;

import java.io.Serializable;

public class Endpoint implements Serializable {
	private static final long serialVersionUID = 1L;
	private String ip;
	private Integer port;
	private String serviceName;

	public Endpoint() {
	}

	public Endpoint(String ip, Integer port, String serviceName) {
		this.ip = ip;
		this.port = port;
		this.serviceName = serviceName;
	}

	public static Endpoint create(String ip, Integer port, String serviceName) {
		return new Endpoint(ip, port, serviceName);
	}

	public String getIp() {
		return ip;
	}

	public Integer getPort() {
		return port;
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

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
}
