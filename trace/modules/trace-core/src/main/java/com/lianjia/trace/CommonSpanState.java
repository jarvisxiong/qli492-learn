package com.lianjia.trace;

public interface CommonSpanState {

	Boolean sample();

	Endpoint endpoint();
}
