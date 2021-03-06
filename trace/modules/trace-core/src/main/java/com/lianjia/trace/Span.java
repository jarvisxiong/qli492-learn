package com.lianjia.trace;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Span implements Serializable {
	private static final long serialVersionUID = 1L;
	private String traceId;
	private String id;
	private String name;
	private String parentId;
	private String serviceId;
	private Long timestamp;
	private List<Annotation> annotations;
	private List<BinaryAnnotation> binaryAnnotations;
	private Long duration;

	public Span addToAnnotations(Annotation elem) {
		if (this.annotations == Collections.EMPTY_LIST) {
			this.annotations = new ArrayList<Annotation>();
		}
		this.annotations.add(elem);
		return this;
	}

	public Span addToBinaryAnnotations(BinaryAnnotation elem) {
		if (this.binaryAnnotations == Collections.EMPTY_LIST) {
			this.binaryAnnotations = new ArrayList<BinaryAnnotation>();
		}
		this.binaryAnnotations.add(elem);
		return this;
	}

	public String getTraceId() {
		return traceId;
	}

	public void setTraceId(String traceId) {
		this.traceId = traceId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	public List<Annotation> getAnnotations() {
		return annotations;
	}

	public void setAnnotations(List<Annotation> annotations) {
		this.annotations = annotations;
	}

	public List<BinaryAnnotation> getBinaryAnnotations() {
		return binaryAnnotations;
	}

	public void setBinaryAnnotations(List<BinaryAnnotation> binaryAnnotations) {
		this.binaryAnnotations = binaryAnnotations;
	}

	public Long getDuration() {
		return duration;
	}

	public void setDuration(Long duration) {
		this.duration = duration;
	}

	@Override
	public String toString() {
		return "Span{" + "traceId=" + traceId + ", id=" + id + ", parentId=" + parentId + ", serviceId=" + serviceId
				+ ", spanName='" + name + '\'' + ", annotations=" + annotations + ", binaryAnnotations="
				+ binaryAnnotations + ", isSample=" + '}';
	}
}
