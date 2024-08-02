package br.com.microservices.orchestrated.productvalidationservice.core.repository;


import br.com.microservices.orchestrated.productvalidationservice.core.model.Validation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ValidationRepository extends JpaRepository<Validation,Integer> {
    Boolean existsByOrderIdAndTransactionId(String orderid,String transactionId);
    Optional<Validation> findByOrderIdAndTransactionId(String orderid, String transactionId);

}