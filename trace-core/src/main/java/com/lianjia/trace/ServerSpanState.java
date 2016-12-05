package com.lianjia.trace;

public interface ServerSpanState extends CommonSpanState {

	Span getCurrentServerSpan();

	void setCurrentServerSpan(final Span span);
}
