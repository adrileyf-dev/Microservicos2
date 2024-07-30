package br.com.microservices.orchestrated.inventoryservice.core.consumer;

import br.com.microservices.orchestrated.inventoryservice.core.utils.JsonUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class InventoryConsumer {
    private final JsonUtil jsonUtil;
    @KafkaListener(
            groupId = "${spring.kafka.consumer.group-id}",
            topics  = "${spring.kafka.topic.inventory-sucess}"
    )
    public void  inventorySucess(String payload)
    {
        log.info("Receving sucess event{} from inventory-sucess topics ",payload);
        var event = jsonUtil.toEvent(payload);
        log.info(event.toString());
    }
    @KafkaListener(
            groupId = "${spring.kafka.consumer.group-id}",
            topics = "${spring.kafka.topic.payment-fail}"
    )
    public void inventoryFail(String payload)
    {
        log.info("Receiving roolback  event{} from inventory-fail topics",payload);
        var event = jsonUtil.toEvent(payload);
        log.info(event.toString());
    }
}