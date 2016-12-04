package com.lianjia.trace;

import java.io.Serializable;

public class Annotation implements Serializable {
	private static final long serialVersionUID = -1L;
	public static final String CLIENT_SEND = "cs";
	public static final String CLIENT_RECEIVE = "cr";
	public static final String SERVER_SEND = "ss";
	public static final String SERVER_RECEIVE = "sr";
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

	@Override
	public String toString() {
		return "Annotation{" + "timestamp=" + timestamp + ", value='" + value + '\'' + ", host=" + endpoint
				+ ", duration=" + duration + '}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Annotation))
			return false;

		Annotation that = (Annotation) o;

		if (duration != null && !duration.equals(that.duration))
			return false;
		if (!endpoint.equals(that.endpoint))
			return false;
		if (!timestamp.equals(that.timestamp))
			return false;
		if (!value.equals(that.value))
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = timestamp.hashCode();
		result = 31 * result + value.hashCode();
		result = 31 * result + endpoint.hashCode();
		result = 31 * result + duration.hashCode();
		return result;
	}
}
