package br.com.microservices.orchestrated.orderservice.Core.dto;

import br.com.microservices.orchestrated.orderservice.Core.document.OrderProduct;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {
    private List<OrderProduct> products;
}
