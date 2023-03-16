package com.eBankingPortal.config;

import org.apache.kafka.streams.processor.api.Processor;
import org.apache.kafka.streams.processor.api.ProcessorContext;
import org.apache.kafka.streams.processor.api.Record;
import org.apache.kafka.streams.processor.StateStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import java.util.Objects;

import com.eBankingPortal.Request.TransactionRequest;
import com.eBankingPortal.Response.TransactionResponse;

public class KafkaStreamsProcessor implements Processor<Long, TransactionRequest, Long, TransactionResponse> {
    private final Logger log = LoggerFactory.getLogger(KafkaStreamsProcessor.class);

    private StateStore stateStore;

    @Value(value = "${kafka.streams.stateStoreName}")
    private String stateStoreName;

    @Override
    public void init(final ProcessorContext<Long, TransactionResponse> context) {
        stateStore = context.getStateStore(stateStoreName);
        Objects.requireNonNull(stateStore, "State store can't be null");
    }

    @Override
    public void process(Record<Long, TransactionRequest> record) {
        // int month = record.value().getMonth();
        // int year = record.value().getYear();
        // Long account = record.value().getAccountId();
        log.info("process transactions : {}", record);
    }

    @Override
    public void close() {

    }
}
