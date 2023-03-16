package com.eBankingPortal.config;

import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.LongDeserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.errors.LogAndContinueExceptionHandler;
import org.apache.kafka.streams.state.KeyValueStore;
import org.apache.kafka.streams.state.StoreBuilder;
import org.apache.kafka.streams.state.Stores;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerde;

import com.eBankingPortal.Response.TransactionResponse;

import java.util.Properties;
import static org.apache.kafka.streams.StreamsConfig.*;

public class KafkaConfig {

    private final Logger log = LoggerFactory.getLogger(KafkaConfig.class);

    @Value(value = "${kafka.bootstrapAddress}")
    private String bootstrapAddress;

    @Value(value = "${kafka.streams.applicationId}")
    private String applicationId;

    @Value(value = "${kafka.topics.msgTransactions.name}")
    private String msgTransactionsTopic;

    @Value(value = "${kafka.streams.stateStoreName}")
    private String stateStoreName;

    private Properties createConfigurationProperties() {
        final Properties props = new Properties();
        props.put(BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        props.put(APPLICATION_ID_CONFIG, applicationId);
        props.put(DEFAULT_DESERIALIZATION_EXCEPTION_HANDLER_CLASS_CONFIG, LogAndContinueExceptionHandler.class);
        props.put(StreamsConfig.STATE_DIR_CONFIG, "D:\\statestore");
        props.put(StreamsConfig.REPLICATION_FACTOR_CONFIG, 1);
        return props;
    }

    @Bean
    @Primary
    public KafkaStreams kafkaStreams() {
        log.info("Create Kafka Stream Bean with defined topology");
        Topology topology = this.buildTopology(new StreamsBuilder());
        final KafkaStreams kafkaStreams = new KafkaStreams(topology, createConfigurationProperties());
        kafkaStreams.start();
        return kafkaStreams;
    }

    private final ObjectFactory<KafkaStreamsProcessor> KafkaStreamsProcessorObjectFactory;

    private final Deserializer<Long> keyDeSerializer = new LongDeserializer();

    private final Deserializer<TransactionResponse> valueDeSerializer = new JsonDeserializer<>(
            TransactionResponse.class);

    private final Serde<Long> keySerializer = Serdes.Long();

    private final Serde<TransactionResponse> valueSerializer = new JsonSerde<>(TransactionResponse.class);

    public KafkaConfig(ObjectFactory<KafkaStreamsProcessor> trasactionsStreamsProcessorObjectFactory) {
        KafkaStreamsProcessorObjectFactory = trasactionsStreamsProcessorObjectFactory;
    }

    public KafkaStreamsProcessor getKafkaStreamsProcessor() {
        return KafkaStreamsProcessorObjectFactory.getObject();
    }

    public Topology buildTopology(StreamsBuilder streamsBuilder) {
        Topology topology = streamsBuilder.build();

        StoreBuilder<KeyValueStore<Long, TransactionResponse>> stateStoreBuilder = Stores
                .keyValueStoreBuilder(Stores.persistentKeyValueStore(stateStoreName), keySerializer, valueSerializer);

        topology.addSource("Source", keyDeSerializer, valueDeSerializer, msgTransactionsTopic)
                .addProcessor("Process", this::getKafkaStreamsProcessor, "Source")
                .addStateStore(stateStoreBuilder, "Process");
        return topology;
    }

}
