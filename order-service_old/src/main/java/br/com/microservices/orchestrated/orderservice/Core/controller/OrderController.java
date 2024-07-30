package br.com.microservices.orchestrated.orderservice.Core.controller;

import br.com.microservices.orchestrated.orderservice.Core.document.Order;
import br.com.microservices.orchestrated.orderservice.Core.dto.OrderRequest;
import br.com.microservices.orchestrated.orderservice.Core.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
@AllArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public Order createOrder(@RequestBody OrderRequest orderRequest){
        return  orderService.createOrder(orderRequest);
    }

    @GetMapping("/palavra")
    public String getPalavra() {
        return "este";
    }

}
