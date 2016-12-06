package com.lianjia.trace;

import com.lianjia.trace.collector.SpanCollector;
import com.lianjia.trace.interceptor.ClientRequestInterceptor;
import com.lianjia.trace.interceptor.ClientResponseInterceptor;
import com.lianjia.trace.interceptor.ServerRequestInterceptor;
import com.lianjia.trace.interceptor.ServerResponseInterceptor;
import com.lianjia.trace.sampler.Sampler;
import com.lianjia.trace.tracer.ClientTracer;
import com.lianjia.trace.tracer.ServerTracer;
import com.lianjia.trace.util.RandomSpanIdGenerator;
import com.lianjia.trace.util.SpanIdGenerator;
import com.lianjia.trace.util.Util;

public class Trace {
	private final ServerTracer serverTracer;
	private final ClientTracer clientTracer;
	private final LocalTracer localTracer;
	private final ServerRequestInterceptor serverRequestInterceptor;
	private final ServerResponseInterceptor serverResponseInterceptor;
	private final ClientRequestInterceptor clientRequestInterceptor;
	private final ClientResponseInterceptor clientResponseInterceptor;
	private final ServerSpanThreadBinder serverSpanThreadBinder;
	private final ClientSpanThreadBinder clientSpanThreadBinder;

	public static class Creator {
		private final ServerClientAndLocalSpanState state;
		private SpanCollector spanCollector;
		private SpanIdGenerator spanIdGenerator = new RandomSpanIdGenerator();
		private Sampler sampler = Sampler.create(1.0f);

		public Creator() {
			this("unknown");
		}

		public Creator(String serviceName) {
			String ip = LocalIpAddressUtil.resolveLocalAddress().getHostAddress();
			state = new ThreadLocalServerClientAndLocalSpanState(ip, 0, serviceName);
		}

		public Creator(String ip, int port, String serviceName) {
			state = new ThreadLocalServerClientAndLocalSpanState(ip, port, serviceName);
		}

		public Creator(ServerClientAndLocalSpanState state) {
			this.state = Util.checkNotNull(state, "state must be specified.");
		}

		public Creator traceSampler(Sampler sampler) {
			this.sampler = sampler;
			return this;
		}

		public Creator spanCollector(SpanCollector spanCollector) {
			this.spanCollector = spanCollector;
			return this;
		}

		public Trace create() {
			return new Trace(this);
		}

	}

	public ClientTracer clientTracer() {
		return clientTracer;
	}

	public LocalTracer localTracer() {
		return localTracer;
	}

	public ServerTracer serverTracer() {
		return serverTracer;
	}

	public ClientRequestInterceptor clientRequestInterceptor() {
		return clientRequestInterceptor;
	}

	public ClientResponseInterceptor clientResponseInterceptor() {
		return clientResponseInterceptor;
	}

	public ServerRequestInterceptor serverRequestInterceptor() {
		return serverRequestInterceptor;
	}

	public ServerResponseInterceptor serverResponseInterceptor() {
		return serverResponseInterceptor;
	}

	public ServerSpanThreadBinder serverSpanThreadBinder() {
		return serverSpanThreadBinder;
	}

	public ClientSpanThreadBinder clientSpanThreadBinder() {
		return clientSpanThreadBinder;
	}

	private Trace(Creator creator) {
		serverTracer = ServerTracer.create(ServerSpanAndEndpoint.create(creator.state), creator.spanIdGenerator, creator.spanCollector,
				creator.sampler);
		clientTracer = ClientTracer.create(ClientSpanAndEndpoint.create(creator.state), creator.spanIdGenerator, creator.spanCollector,
				creator.sampler);
		localTracer = LocalTracer.create(LocalSpanAndEndpoint.create(creator.state), creator.spanIdGenerator, creator.spanCollector, creator.sampler);

		serverRequestInterceptor = new ServerRequestInterceptor(serverTracer);
		serverResponseInterceptor = new ServerResponseInterceptor(serverTracer);
		clientRequestInterceptor = new ClientRequestInterceptor(clientTracer);
		clientResponseInterceptor = new ClientResponseInterceptor(clientTracer);

		serverSpanThreadBinder = new ServerSpanThreadBinder(creator.state);
		clientSpanThreadBinder = new ClientSpanThreadBinder(creator.state);
	}
}
