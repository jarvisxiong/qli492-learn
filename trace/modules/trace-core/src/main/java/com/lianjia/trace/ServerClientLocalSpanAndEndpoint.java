package com.lianjia.trace;

public class ServerClientLocalSpanAndEndpoint implements SpanAndEndpoint {
	private ServerClientAndLocalSpanState state;

	ServerClientLocalSpanAndEndpoint(ServerClientAndLocalSpanState state) {
		if (state == null) {
			throw new NullPointerException("Null state");
		}
		this.state = state;
	}

	@Override
	public Span span() {
		return state().getCurrentClientSpan();
	}

	@Override
	public Endpoint endpoint() {
		return state().endpoint();
	}

	ServerClientAndLocalSpanState state() {
		return state;
	}

	@Override
	public String toString() {
		return "ServerClientLocalSpanAndEndpoint{" + "state=" + state + "}";
	}
}
