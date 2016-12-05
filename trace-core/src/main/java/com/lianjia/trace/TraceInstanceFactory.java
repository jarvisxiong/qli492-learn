package com.lianjia.trace;

import org.springframework.beans.factory.FactoryBean;

import com.lianjia.trace.collector.SpanCollector;
import com.lianjia.trace.sampler.Sampler;

public class TraceInstanceFactory implements FactoryBean<Trace> {
	private String serviceName;
	private SpanCollector spanCollector;

	private Sampler sampler = Sampler.create(1.0f);

	public void setSampler(Sampler sampler) {
		this.sampler = sampler;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setSpanCollector(SpanCollector spanCollector) {
		this.spanCollector = spanCollector;
	}

	@Override
	public Trace getObject() throws Exception {
		return new Trace.Creator(serviceName).spanCollector(spanCollector).traceSampler(sampler).create();
	}

	@Override
	public Class<?> getObjectType() {
		return Trace.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}
}