package br.com.microservices.orchestrated.orderservice.Core.document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collation = "Event")
public class Event {
    @Id
    private  String id;
    private  String transactionId;
    private  String orderId;
    private  Order payload;
    private  String source;
    private  String status;
    private  List<History> eventHistory;
    private  LocalDateTime CreatedAt;





}
