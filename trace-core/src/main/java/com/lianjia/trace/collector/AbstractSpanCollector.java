package com.lianjia.trace.collector;

import static java.util.concurrent.TimeUnit.SECONDS;

import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;

import com.lianjia.trace.Span;
import com.lianjia.trace.codec.SpanCodec;
import com.lianjia.trace.handler.SpanCollectorMetricsHandler;

public abstract class AbstractSpanCollector implements SpanCollector, Flushable, Closeable {
	private final SpanCollectorMetricsHandler metrics;
	private final SpanCodec codec;
	private final BlockingQueue<Span> pending = new LinkedBlockingQueue<Span>(1000);
	private final Flusher flusher;

	public AbstractSpanCollector(SpanCodec codec, SpanCollectorMetricsHandler metrics, int flushInterval) {
		this.metrics = metrics;
		this.flusher = flushInterval > 0 ? new Flusher(this, flushInterval) : null;
		this.codec = codec;
	}

	@Override
	public void collect(Span span) {
		metrics.incrementAcceptedSpans(1);
		if (!pending.offer(span)) {
			metrics.incrementDroppedSpans(1);
		}
	}

	@Override
	public void flush() {
		if (pending.isEmpty()) {
			return;
		}
		System.out.print("");
		List<Span> drained = new ArrayList<Span>(pending.size());
		pending.drainTo(drained);
		if (drained.isEmpty())
			return;

		int spanCount = drained.size();
		try {
			reportSpans(drained);
		} catch (IOException e) {
			metrics.incrementDroppedSpans(spanCount);
		} catch (RuntimeException e) {
			metrics.incrementDroppedSpans(spanCount);
		}
	}

	static final class Flusher implements Runnable {
		final Flushable flushable;
		final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

		Flusher(Flushable flushable, int flushInterval) {
			this.flushable = flushable;
			this.scheduler.scheduleWithFixedDelay(this, 0, flushInterval, SECONDS);
		}

		@Override
		public void run() {
			try {
				flushable.flush();
			} catch (IOException ignored) {
			}
		}
	}

	protected void reportSpans(List<Span> spans) throws IOException {
		byte[] encoded = codec.writeSpans(spans);
		sendSpans(encoded);
	}

	protected abstract void sendSpans(byte[] encoded) throws IOException;

	@Override
	public void close() {
		if (flusher != null)
			flusher.scheduler.shutdown();
		int dropped = pending.drainTo(new LinkedList<Span>());
		metrics.incrementDroppedSpans(dropped);
	}
}
