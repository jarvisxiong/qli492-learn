package com.lianjia.trace;

import com.lianjia.trace.util.Util;

public final class ThreadLocalServerClientAndLocalSpanState implements ServerClientAndLocalSpanState {

	private final static ThreadLocal<Span> currentServerSpan = new ThreadLocal<Span>();
	private final static ThreadLocal<Span> currentClientSpan = new ThreadLocal<Span>();
	private final static ThreadLocal<Span> currentLocalSpan = new ThreadLocal<Span>();

	private final Endpoint endpoint;

	public ThreadLocalServerClientAndLocalSpanState(String ip, int port, String serviceName) {
		this(Endpoint.create(ip, port, serviceName));
	}

	public ThreadLocalServerClientAndLocalSpanState(Endpoint endpoint) {
		Util.checkNotNull(endpoint, "endpoint must be specified.");
		Util.checkNotBlank(endpoint.getServiceName(), "Service name must be specified.");
		this.endpoint = endpoint;
	}

	@Override
	public Span getCurrentServerSpan() {
		return currentServerSpan.get();
	}

	@Override
	public void setCurrentServerSpan(final Span span) {
		if (span == null) {
			currentServerSpan.remove();
		} else {
			currentServerSpan.set(span);
		}
	}

	@Override
	public Endpoint endpoint() {
		return endpoint;
	}

	@Override
	public Span getCurrentClientSpan() {
		return currentClientSpan.get();
	}

	@Override
	public void setCurrentClientSpan(final Span span) {
		currentClientSpan.set(span);
	}

	@Override
	public Span getCurrentLocalSpan() {
		return currentLocalSpan.get();
	}

	@Override
	public void setCurrentLocalSpan(Span span) {
		if (span == null) {
			currentLocalSpan.remove();
		} else {
			currentLocalSpan.set(span);
		}
	}
}
