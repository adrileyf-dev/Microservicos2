package br.com.microservices.orchestrated.orchestratorservice.config;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.*;

import java.util.HashMap;
import java.util.Map;

import static br.com.microservices.orchestrated.orchestratorservice.core.enuns.Etopics.*;

@EnableKafka
@Configuration
@RequiredArgsConstructor
public class kafka {
    private static final Integer PARTITION_COUNT = 1;
    private static final Integer REPLICA_COUNT = 1;
    @Value("@{spring.kafka.bootstrap-servers}")
    private String bootstrapservers;
    @Value("@{spring.kafka.consumer.group-id}")
    private String groupid;
    @Value("@{spring.kafka.consumer.auto-offset-reset}")
    private String autoOffsetReset;

    @Bean
    public ConsumerFactory<String, String> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(consumerProps());
    }

    private Map<String, Object> consumerProps() {
        var props = new HashMap<String, Object>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapservers);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupid);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, autoOffsetReset);
        return props;
    }
    @Bean
    public ProducerFactory<String, String> producerFactory() {
        return new DefaultKafkaProducerFactory<>(produceProps());
    }
    private Map<String, Object> produceProps() {
        var props = new HashMap<String, Object>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapservers);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringSerializer.class);
        return props;
    }
    @Bean
    public KafkaTemplate<String, String> kafkaTemplate(ProducerFactory<String, String> producerFactory) {
        return new KafkaTemplate<>(producerFactory);
    }

    private NewTopic buildTopics(String name) {
        return TopicBuilder
                .name(name)
                .replicas(REPLICA_COUNT)
                .partitions(PARTITION_COUNT)
                .build();
    }
    @Bean
    public NewTopic startSagaTopics() {
        return buildTopics(START_SAGA.getTopic());
    }

    @Bean
    public NewTopic orcherstrator() {
        return buildTopics(BASE_ORCHESTRATOR.getTopic());
    }

    @Bean
    public NewTopic finishSucess() {
        return buildTopics(FINISH_SUCCESS.getTopic());
    }

    @Bean
    public NewTopic finishFail() {
        return buildTopics(FINISH_FAIL.getTopic());
    }

    @Bean
    public NewTopic productValidationSuccessTopic() {
        return buildTopics(PRODUCT_VALIDATION_SUCCESS.getTopic());
    }

    @Bean
    public NewTopic productValidationFailTopic() {
        return buildTopics(PRODUCT_VALIDATION_FAIL.getTopic());
    }

    @Bean
    public NewTopic paymentSuccessTopic() {
        return buildTopics(PAYMENT_SUCCESS.getTopic());
    }

    @Bean
    public NewTopic paymentValidationFailTopic() {
        return buildTopics(PAYMENT_FAIL.getTopic());
    }

    @Bean
    public NewTopic inventoryValidationSuccessTopic() {
        return buildTopics(INVENTORY_SUCCESS.getTopic());
    }

    @Bean
    public NewTopic inventoryValidationFailTopic() {
        return buildTopics(INVENTORY_FAIL.getTopic());
    }
    @Bean
    public NewTopic notifyEndingTopic() {
        return buildTopics(NOTIFY_ENDING.getTopic());
    }
}


