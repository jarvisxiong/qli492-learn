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

	@Override
	public String toString() {
		return "Endpoint{" + "ip='" + ip + '\'' + ", port=" + port + ", serviceName='" + serviceName + '\'' + '}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Endpoint))
			return false;

		Endpoint endpoint = (Endpoint) o;

		if (!ip.equals(endpoint.ip))
			return false;
		if (!port.equals(endpoint.port))
			return false;
		if (!serviceName.equals(endpoint.serviceName))
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = ip.hashCode();
		result = 31 * result + port.hashCode();
		result = 31 * result + serviceName.hashCode();
		return result;
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
