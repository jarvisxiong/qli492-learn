package com.lianjia.trace;

import com.lianjia.trace.collector.SpanCollector;

/**
 * Used to submit application specific annotations.
 */
public abstract class AnnotationSubmitter {

	public abstract SpanAndEndpoint getSpanAndEndpoint();

	public void submitAnnotation(String value) {
		Span span = getSpanAndEndpoint().span();
		if (span != null) {
			Annotation annotation = Annotation.create(currentTimeMicroseconds(), value, getSpanAndEndpoint().endpoint());
			addAnnotation(span, annotation);
		}
	}

	public void submitAnnotation(String value, long timestamp) {
		Span span = getSpanAndEndpoint().span();
		if (span != null) {
			Annotation annotation = Annotation.create(timestamp, value, getSpanAndEndpoint().endpoint());
			addAnnotation(span, annotation);
		}
	}

	public void submitStartAnnotation(String annotationName) {
		Span span = getSpanAndEndpoint().span();
		if (span != null) {
			Annotation annotation = Annotation.create(currentTimeMicroseconds(), annotationName,
					getSpanAndEndpoint().endpoint());
			synchronized (span) {
				span.setTimestamp(annotation.getTimestamp());
				span.addToAnnotations(annotation);
			}
		}
	}

	public boolean submitEndAnnotation(String annotationName, SpanCollector spanCollector) {
		Span span = getSpanAndEndpoint().span();
		if (span == null) {
			return false;
		}
		Annotation annotation = Annotation.create(currentTimeMicroseconds(), annotationName,
				getSpanAndEndpoint().endpoint());
		span.addToAnnotations(annotation);
		if (span.getTimestamp() != null) {
			span.setDuration(annotation.getTimestamp() - span.getTimestamp());
		}
		spanCollector.collect(span);
		return true;
	}

	public void submitAddress(String key, String ip, int port, String serviceName) {
		Span span = getSpanAndEndpoint().span();
		if (span != null) {
			serviceName = serviceName != null ? serviceName : "unknown";
			Endpoint endpoint = Endpoint.create(ip, port, serviceName);
			BinaryAnnotation ba = BinaryAnnotation.create(key, endpoint);
			addBinaryAnnotation(span, ba);
		}
	}

	public void submitBinaryAnnotation(String key, String value) {
		Span span = getSpanAndEndpoint().span();
		if (span != null) {
			BinaryAnnotation ba = BinaryAnnotation.create(key, value, getSpanAndEndpoint().endpoint());
			addBinaryAnnotation(span, ba);
		}
	}

	public void submitBinaryAnnotation(String key, int value) {
		submitBinaryAnnotation(key, String.valueOf(value));
	}

	long currentTimeMicroseconds() {
		return System.currentTimeMillis() * 1000;
	}

	private void addAnnotation(Span span, Annotation annotation) {
		synchronized (span) {
			span.addToAnnotations(annotation);
		}
	}

	private void addBinaryAnnotation(Span span, BinaryAnnotation ba) {
		synchronized (span) {
			span.addToBinaryAnnotations(ba);
		}
	}

	public AnnotationSubmitter() {
	}
}
