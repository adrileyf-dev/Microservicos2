package br.com.microservices.orchestrated.inventoryservice.core.dto;

import br.com.microservices.orchestrated.inventoryservice.core.enuns.ESagaStatus;
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

    private String souce;
    private ESagaStatus status;
    private  String mesage;
    private LocalDateTime creatAt;



}
