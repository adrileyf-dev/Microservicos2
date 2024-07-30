package br.com.microservices.orchestrated.paymentservice.core.consumer;

import br.com.microservices.orchestrated.paymentservice.core.utils.JsonUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class PaymentConsumer {
    private final JsonUtil jsonUtil;
    @KafkaListener(
            groupId = "${spring.kafka.consumer.group-id}",
            topics = "${spring.kafka.topic.payment-success}"
    )
    public void  paymentSuccess(String payload)
    {
        log.info("Receving sucess event{} from payment-success ",payload);
        var event = jsonUtil.toEvent(payload);
        log.info(event.toString());
    }
    @KafkaListener(
            groupId = "${spring.kafka.consumer.group-id}",
            topics = "${spring.kafka.topic.payment-fail}"
    )
    public void paymentFail(String payload)
    {
        log.info("Receiving roolback  event{} from payment-fail topics",payload);
        var event = jsonUtil.toEvent(payload);
        log.info(event.toString());
    }
}