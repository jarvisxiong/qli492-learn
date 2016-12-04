package com.lianjia.trace;

public interface ClientSpanState extends CommonSpanState {

	Span getCurrentClientSpan();
	
	void setCurrentClientSpan(final Span span);
}
