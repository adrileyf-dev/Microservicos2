package br.com.microservices.orchestrated.orderservice.Core.service;

import br.com.microservices.orchestrated.orderservice.Core.document.Event;
import br.com.microservices.orchestrated.orderservice.Core.document.Order;
import br.com.microservices.orchestrated.orderservice.Core.dto.OrderRequest;
import br.com.microservices.orchestrated.orderservice.Core.producer.SagaProduce;
import br.com.microservices.orchestrated.orderservice.Core.repository.OrderRepository;
import br.com.microservices.orchestrated.orderservice.Core.utils.JsonUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class OrderService {
    private  static final String TRANSACTION_ID_PATTEN = "%s_%s";
    private  final OrderRepository repository;
    private  final JsonUtil jsonUtil;
    private  final SagaProduce produce;
    private  final EventService eventService;

    public Order createOrder(OrderRequest orderRequest){
        var order = Order
                .builder()
                .products(orderRequest.getProducts())
                .creatAt(LocalDateTime.now())
                .transactionId(String.format(TRANSACTION_ID_PATTEN,Instant.now().toEpochMilli(), UUID.randomUUID()))
                .build();
        repository.save(order);
        produce.sendEvent(jsonUtil.toJason(createPayload(order)));
       return order;
    }

    private Event createPayload(Order order){
        var event = Event
                .builder()
                .orderId(order.getId())
                .transactionId(order.getTransactionId())
                .payload(order)
                .CreatedAt(LocalDateTime.now())
                .build();
        eventService.save(event);
        return  event;
    }
}
