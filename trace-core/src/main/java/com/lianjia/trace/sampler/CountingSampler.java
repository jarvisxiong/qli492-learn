package com.lianjia.trace.sampler;

import java.util.BitSet;
import java.util.Random;

public class CountingSampler extends Sampler {
	public static Sampler create(final float rate) {
		if (rate == 0) {
			return NEVER_SAMPLE;
		}
		if (rate == 1.0) {
			return ALWAYS_SAMPLE;
		}
		return new CountingSampler(rate);
	}

	private int i;
	private final BitSet sampleDecisions;

	CountingSampler(float rate) {
		int outOf100 = (int) (rate * 100.0f);
		this.sampleDecisions = randomBitSet(100, outOf100, new Random());
	}

	@Override
	public synchronized boolean isSampled(String traceIdIgnored) {
		boolean result = sampleDecisions.get(i++);
		if (i == 100)
			i = 0;
		return result;
	}

	@Override
	public String toString() {
		return "CountingSampler()";
	}

	static BitSet randomBitSet(int size, int cardinality, Random rnd) {
		BitSet result = new BitSet(size);
		int[] chosen = new int[cardinality];
		int i;
		for (i = 0; i < cardinality; ++i) {
			chosen[i] = i;
			result.set(i);
		}
		for (; i < size; ++i) {
			int j = rnd.nextInt(i + 1);
			if (j < cardinality) {
				result.clear(chosen[j]);
				result.set(i);
				chosen[j] = i;
			}
		}
		return result;
	}
}
