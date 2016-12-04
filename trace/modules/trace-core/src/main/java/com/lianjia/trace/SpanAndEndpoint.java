package com.lianjia.trace;

public interface SpanAndEndpoint {

    /**
     * Gets the span to which to add annotations.
     *
     * @return Span to which to add annotations. Can be <code>null</code>. In that case the different submit methods will not
     *         do anything.
     */
    Span span();

    /**
     * Indicates the network context of the local service being traced.
     *
     * @return Endpoint to add to annotations. Cannot be <code>null</code>.
     */
    Endpoint endpoint();
}
