package br.com.microservices.orchestrated.productvalidationservice.core.dto;

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
    public class Order {
    private  String id;
    private List<OrderProduct> products;
    private LocalDateTime creatAt;
    private String transactionId;
    private double totalAmount;
    private int totalItems;



}
