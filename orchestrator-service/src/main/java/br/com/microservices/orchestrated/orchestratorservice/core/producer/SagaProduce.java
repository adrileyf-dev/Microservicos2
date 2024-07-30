package br.com.microservices.orchestrated.orchestratorservice.core.producer;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Log4j2
@AllArgsConstructor
@Component
public class SagaProduce {
    private final KafkaTemplate<String,String> kafkaTemplate;

      public void sendEvent(String payload,String topic){
        try {
            log.info("Send event to topic{} if data {}",topic,payload);
            kafkaTemplate.send(topic,payload);
        }catch (Exception ex){
          log.error("Error trying to send data to topics {} with data {} ",topic,payload,ex);
        }

    }
}
