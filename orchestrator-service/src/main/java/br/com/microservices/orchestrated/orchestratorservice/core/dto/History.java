package br.com.microservices.orchestrated.orchestratorservice.core.dto;

import br.com.microservices.orchestrated.orchestratorservice.core.enuns.ESventSouce;
import br.com.microservices.orchestrated.orchestratorservice.core.enuns.ESagaStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class History {

    private ESventSouce souce;
    private ESagaStatus status;
    private  String mesage;
    private LocalDateTime creatAt;



}
