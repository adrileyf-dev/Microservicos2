package br.com.microservices.orchestrated.orderservice.Core.document;

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

    private  String souce;
    private  String status;
    private  String mesage;
    private LocalDateTime creatAt;



}
