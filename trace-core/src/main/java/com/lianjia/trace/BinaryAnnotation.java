package com.lianjia.trace;

import java.io.Serializable;

import com.lianjia.trace.util.Util;

public class BinaryAnnotation implements Serializable {
	private static final long serialVersionUID = 1L;
	private String key;
	private String value;
	private String type;
	private Integer duration;
	private Endpoint host;

	public BinaryAnnotation() {
	}
	
	public BinaryAnnotation(String key, String value, Endpoint host) {
		this.key = Util.checkNotBlank(key, "Null or blank key");
		this.value = Util.checkNotNull(value, "Null value");
		this.host = host;
	}

	public static BinaryAnnotation create(String key, Endpoint endpoint) {
		return create(key, "1", endpoint);
	}

	public static BinaryAnnotation create(String key, String value, Endpoint endpoint) {
		return new BinaryAnnotation(key, value, endpoint);
	}

	public Endpoint getHost() {
		return host;
	}

	public void setHost(Endpoint endpoint) {
		this.host = endpoint;
	}

	@Override
	public String toString() {
		return "BinaryAnnotation{" + "key='" + key + '\'' + ", value=" + value + ", type='" + type + '\''
				+ ", duration=" + duration + ", endpoint=" + host + '}';
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}
}
