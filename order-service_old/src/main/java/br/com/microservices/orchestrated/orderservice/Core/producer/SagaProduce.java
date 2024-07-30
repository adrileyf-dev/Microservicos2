package br.com.microservices.orchestrated.orderservice.Core.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Log4j2
@RequiredArgsConstructor
@Component
public class SagaProduce {
    private final KafkaTemplate<String,String> kafkaTemplate;

    @Value("${spring.kafka.topic.start-saga}")
    private String startSagaTopic;
      public void sendEvent(String payload){
        try {
            log.info("Send event to topic{} if data {}",startSagaTopic,payload);
            kafkaTemplate.send(startSagaTopic,payload);
        }catch (Exception ex){
          log.error("Error trying to send data to topics {} with data {} ",startSagaTopic,payload,ex);
        }

    }
}
