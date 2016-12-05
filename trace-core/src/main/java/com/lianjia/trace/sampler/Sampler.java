package com.lianjia.trace.sampler;

public abstract class Sampler {

	public static final Sampler ALWAYS_SAMPLE = new Sampler() {
		@Override
		public boolean isSampled(String traceId) {
			return true;
		}

		@Override
		public String toString() {
			return "AlwaysSample";
		}
	};

	public static final Sampler NEVER_SAMPLE = new Sampler() {
		@Override
		public boolean isSampled(String traceId) {
			return false;
		}

		@Override
		public String toString() {
			return "NeverSample";
		}
	};

	/** Returns true if the trace ID should be measured. */
	public abstract boolean isSampled(String traceId);

	/**
	 * Returns a sampler, given a rate expressed as a percentage.
	 *
	 * <p>
	 * The sampler returned is good for low volumes of traffic (<100K requests),
	 * as it is precise. If you have high volumes of traffic, consider
	 * {@link BoundarySampler}.
	 *
	 * @param rate
	 *            minimum sample rate is 0.0001, or 0.01% of traces
	 */
	public static Sampler create(float rate) {
		return CountingSampler.create(rate);
	}
}