package br.com.microservices.orchestrated.orchestratorservice.core.dto;

import br.com.microservices.orchestrated.orchestratorservice.core.enuns.ESventSouce;
import br.com.microservices.orchestrated.orchestratorservice.core.enuns.ESagaStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Event {
    private  String id;
    private  String transactionId;
    private  String orderId;
    private  Order payload;
    private  ESventSouce souce;
    private  ESagaStatus status;
    private  List<History> eventHistory;
    private  LocalDateTime creatAt;
}
