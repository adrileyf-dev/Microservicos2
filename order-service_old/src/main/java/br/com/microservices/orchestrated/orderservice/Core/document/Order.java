package br.com.microservices.orchestrated.orderservice.Core.document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import nonapi.io.github.classgraph.json.Id;
import org.apache.catalina.LifecycleState;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder@Document(collation = "order")
    public class Order {
    @Id
    private  String id;
    private List<OrderProduct> products;
    private LocalDateTime creatAt;
    private String transactionId;
    private double totalAmount;
    private int totalItens;



}
