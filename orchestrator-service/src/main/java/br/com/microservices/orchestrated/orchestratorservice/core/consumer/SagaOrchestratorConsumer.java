package br.com.microservices.orchestrated.orchestratorservice.core.consumer;

import br.com.microservices.orchestrated.orchestratorservice.core.utils.JsonUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class SagaOrchestratorConsumer {
    private final JsonUtil jsonUtil;
    @KafkaListener(
            groupId = "${spring.kafka.consumer.group-id}",
            topics = "${spring.kafka.topic.start-saga}"
    )
    public void consumeStarSagaEvent(String payload)
    {
        log.info("Receving ending notification event{} from start-saga topics",payload);
        var event = jsonUtil.toEvent(payload);
        log.info(event.toString());
    }
    @KafkaListener(
            groupId = "${spring.kafka.consumer.group-id}",
            topics = "${spring.kafka.topic.orchestrator}"
    )
    public void consumeOrchestrator(String payload)
    {
        log.info("Receving ending notification event{} from orchestrator topics",payload);
        var event = jsonUtil.toEvent(payload);
        log.info(event.toString());
    }

    @KafkaListener(
            groupId = "${spring.kafka.consumer.group-id}",
            topics = "${spring.kafka.topic.finish-success}"
    )
    public void consumefinishsuccess(String payload)
    {
        log.info("Receving ending notification event{} from finish-success topics",payload);
        var event = jsonUtil.toEvent(payload);
        log.info(event.toString());
    }
    @KafkaListener(
            groupId = "${spring.kafka.consumer.group-id}",
            topics = "${spring.kafka.topic.finish-fail}"
    )
    public void consumefinishfail(String payload)
    {
        log.info("Receving ending notification event{} from finish-fail topics",payload);
        var event = jsonUtil.toEvent(payload);
        log.info(event.toString());
    }
}