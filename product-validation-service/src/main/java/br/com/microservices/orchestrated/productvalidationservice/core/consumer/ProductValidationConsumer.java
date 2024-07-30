package br.com.microservices.orchestrated.productvalidationservice.core.consumer;
import br.com.microservices.orchestrated.productvalidationservice.core.utils.JsonUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
@Slf4j
@Component
@AllArgsConstructor
public class ProductValidationConsumer {
    private final JsonUtil jsonUtil;
    @KafkaListener(
            groupId = "${spring.kafka.consumer.group-id}",
            topics = "${spring.kafka.topic.product-validation-sucess}"
    )
    public void productValidationsucess(String payload)
    {
        log.info("Receving sucesse event{} from product-validation-sucess ",payload);
        var event = jsonUtil.toEvent(payload);
        log.info(event.toString());
    }
    @KafkaListener(
            groupId = "${spring.kafka.consumer.group-id}",
            topics = "${spring.kafka.topic.product-validation-fail}"
    )
    public void productValidationfail(String payload)
    {
        log.info("Receiving roolback  event{} from product-validation-fail topics",payload);
        var event = jsonUtil.toEvent(payload);
        log.info(event.toString());
    }
}