package com.lianjia.trace;

import com.lianjia.trace.util.Util;

public final class ClientSpanThreadBinder {
	private final ClientSpanState state;

	public ClientSpanThreadBinder(ClientSpanState state) {
		this.state = Util.checkNotNull(state, "state");
	}

	public Span getCurrentClientSpan() {
		return state.getCurrentClientSpan();
	}

	public void setCurrentSpan(Span span) {
		state.setCurrentClientSpan(span);
	}
}
