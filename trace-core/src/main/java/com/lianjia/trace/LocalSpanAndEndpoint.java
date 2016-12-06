package com.lianjia.trace;

public class LocalSpanAndEndpoint implements SpanAndEndpoint {
	private final ServerClientAndLocalSpanState state;

	public static LocalSpanAndEndpoint create(ServerClientAndLocalSpanState state) {
		return new LocalSpanAndEndpoint(state);
	}

	LocalSpanAndEndpoint(ServerClientAndLocalSpanState state) {
		if (state == null) {
			throw new NullPointerException("Null state");
		}
		this.state = state;
	}

	ServerClientAndLocalSpanState getState() {
		return state;
	}

	@Override
	public Span span() {
		return getState().getCurrentLocalSpan();
	}

	@Override
	public Endpoint endpoint() {
		return getState().endpoint();
	}
}
