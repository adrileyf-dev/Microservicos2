package br.com.microservices.orchestrated.orderservice.Core.repository;

import br.com.microservices.orchestrated.orderservice.Core.document.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<Order,String> {
}
