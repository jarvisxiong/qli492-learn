package com.lianjia.trace;

public interface LocalSpanState extends CommonSpanState {

	Span getCurrentLocalSpan();

	void setCurrentLocalSpan(Span span);
}
