package com.lianjia.trace;

public class ClientSpanAndEndpoint implements SpanAndEndpoint {
	private final ServerClientAndLocalSpanState state;
	
	static ClientSpanAndEndpoint create(ServerClientAndLocalSpanState state) {
		return new ClientSpanAndEndpoint(state);
	}

	ClientSpanAndEndpoint(ServerClientAndLocalSpanState state) {
		if (state == null) {
			throw new NullPointerException("Null state");
		}
		this.state = state;
	}

	@Override
	public Span span() {
		 return getState().getCurrentClientSpan();
	}

	@Override
	public Endpoint endpoint() {
		return getState().endpoint();
	}

	public ServerClientAndLocalSpanState getState() {
		return state;
	}
}
