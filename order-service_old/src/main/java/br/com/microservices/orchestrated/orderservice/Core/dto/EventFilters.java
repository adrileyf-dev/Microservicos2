package br.com.microservices.orchestrated.orderservice.Core.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventFilters {
    public String orderId;
    public String transactionId;
}

