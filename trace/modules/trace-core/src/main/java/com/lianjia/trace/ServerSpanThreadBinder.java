package com.lianjia.trace;

import com.lianjia.trace.util.Util;

public class ServerSpanThreadBinder {
	private final ServerSpanState state;

	public ServerSpanThreadBinder(ServerSpanState state) {
		this.state = Util.checkNotNull(state, "state");
	}

	public ServerSpan getCurrentServerSpan() {
		return state.getCurrentServerSpan();
	}

	public void setCurrentSpan(final ServerSpan span) {
		state.setCurrentServerSpan(span);
	}
}
