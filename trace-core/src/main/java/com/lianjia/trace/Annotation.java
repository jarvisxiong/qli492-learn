package com.lianjia.trace;

import java.io.Serializable;

public class Annotation implements Serializable {
	private static final long serialVersionUID = -1L;
	private Long timestamp;
	private String value;
	private Endpoint endpoint;
	private Integer duration;

	public Annotation() {
	}

	public Annotation(Long timestamp, String value, Endpoint endpoint) {
		this.timestamp = timestamp;
		this.value = value;
		this.endpoint = endpoint;
	}

	public static Annotation create(long timestamp, String value, Endpoint endpoint) {
		return new Annotation(timestamp, value, endpoint);
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Endpoint getEndpoint() {
		return endpoint;
	}

	public void setEndpoint(Endpoint endpoint) {
		this.endpoint = endpoint;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}
}
