package com.lianjia.trace;

public class DefaultServerSpan extends ServerSpan {
	private final Span span;
	private final Boolean sample;

	DefaultServerSpan(Span span, Boolean sample) {
		this.span = span;
		this.sample = sample;
	}

	@Override
	public Span getSpan() {
		return span;
	}

	@Override
	public Boolean getSample() {
		return sample;
	}

	@Override
	public String toString() {
		return "ServerSpan{" + "span=" + span + ", " + "sample=" + sample + "}";
	}

	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}
		if (o instanceof ServerSpan) {
			ServerSpan that = (ServerSpan) o;
			return ((this.span == null) ? (that.getSpan() == null) : this.span.equals(that.getSpan()))
					&& ((this.sample == null) ? (that.getSample() == null) : this.sample.equals(that.getSample()));
		}
		return false;
	}

	@Override
	public int hashCode() {
		int h = 1;
		h *= 1000003;
		h ^= (span == null) ? 0 : span.hashCode();
		h *= 1000003;
		h ^= (sample == null) ? 0 : sample.hashCode();
		return h;
	}

}
