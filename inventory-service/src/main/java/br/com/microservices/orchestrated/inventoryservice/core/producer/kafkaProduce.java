package br.com.microservices.orchestrated.inventoryservice.core.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Log4j2
@RequiredArgsConstructor
@Component
public class kafkaProduce {
    private final KafkaTemplate<String,String> kafkaTemplate;
    @Value("${spring.kafka.topic.orchestrator}")
    private String orchestratorTopic;
      public void sendEvent(String payload){
        try {
            log.info("Send event to topic{} if data {}",orchestratorTopic,payload);
            kafkaTemplate.send(orchestratorTopic,payload);
        }catch (Exception ex){
          log.error("Error trying to send data to topics {} with data {} ",orchestratorTopic,payload,ex);
        }

    }
}
