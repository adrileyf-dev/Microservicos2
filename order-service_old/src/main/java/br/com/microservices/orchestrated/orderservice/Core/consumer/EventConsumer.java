package br.com.microservices.orchestrated.orderservice.Core.consumer;

import br.com.microservices.orchestrated.orderservice.Core.service.EventService;
import br.com.microservices.orchestrated.orderservice.Core.utils.JsonUtil;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class EventConsumer {
    private final EventService service;
    private final JsonUtil jsonUtil;
    @KafkaListener(
            groupId = "${spring.kafka.consumer.group-id}",
             topics = "${spring.kafka.topic.notify-ending}"
    )
    public void consumeNotifyEndingEvent(String payload)
     {
          log.info("Receving ending notification event{} from notify-ending topics",payload);
          var event = jsonUtil.toEvent(payload);
          service.notifyEnding(event);
      }
}
