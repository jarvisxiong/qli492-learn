package com.lianjia.trace;

public class ServerSpanAndEndpoint implements SpanAndEndpoint {
	private final ServerSpanState state;

	static ServerSpanAndEndpoint create(ServerClientAndLocalSpanState state) {
		return new ServerSpanAndEndpoint(state);
	}

	ServerSpanAndEndpoint(ServerSpanState state) {
		if (state == null) {
			throw new NullPointerException("Null state");
		}
		this.state = state;
	}

	@Override
	public Span span() {
		return state().getCurrentServerSpan();
	}

	@Override
	public Endpoint endpoint() {
		return state().endpoint();
	}

	public ServerSpanState state() {
		return state;
	}
}
