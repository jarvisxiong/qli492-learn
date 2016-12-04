package com.lianjia.trace;

public interface ServerSpanState extends CommonSpanState {

	ServerSpan getCurrentServerSpan();

	void setCurrentServerSpan(final ServerSpan span);
}
