package com.lianjia.trace.handler;

import com.lianjia.trace.collector.SpanCollector;

/**
 * Monitor {@linkplain SpanCollector} by implementing reactions to these events, e.g. updating suitable metrics.
 *
 * See DropwizardMetricsScribeCollectorMetricsHandlerExample in isSampled sources for an example.
 */
public interface SpanCollectorMetricsHandler {

    /**
     * Called when spans are submitted to SpanCollector for processing.
     *
     * @param quantity the number of spans accepted.
     */
    void incrementAcceptedSpans(int quantity);

    /**
     * Called when spans become lost for any reason and won't be delivered to the target collector.
     *
     * @param quantity the number of spans dropped.
     */
    void incrementDroppedSpans(int quantity);

}