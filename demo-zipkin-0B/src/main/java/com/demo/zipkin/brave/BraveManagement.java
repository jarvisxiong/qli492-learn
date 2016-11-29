package com.demo.zipkin.brave;

import com.demo.zipkin.dubbo.filter.BraveConsumerFilter;
import com.demo.zipkin.dubbo.filter.BraveProviderFilter;
import com.github.kristofa.brave.Brave;

public class BraveManagement {
	public Brave brave;

	public BraveManagement(Brave brave) {
		this.brave = brave;
		BraveConsumerFilter.setBrave(brave);
		BraveProviderFilter.setBrave(brave);
	}

	public void setBrave(Brave brave) {
		this.brave = brave;
		BraveConsumerFilter.setBrave(brave);
		BraveProviderFilter.setBrave(brave);
	}
}
