package br.com.microservices.orchestrated.productvalidationservice.core.service;

import br.com.microservices.orchestrated.productvalidationservice.core.Componentes.EventHistoryManager;
import br.com.microservices.orchestrated.productvalidationservice.core.dto.Event;
import br.com.microservices.orchestrated.productvalidationservice.core.producer.KafkaProducer;
import br.com.microservices.orchestrated.productvalidationservice.core.repository.ProductRepository;
import br.com.microservices.orchestrated.productvalidationservice.core.repository.ValidationRepository;
import br.com.microservices.orchestrated.productvalidationservice.core.utils.JsonUtil;
import jakarta.validation.ValidationException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static br.com.microservices.orchestrated.productvalidationservice.core.enuns.ESagaStatus.*;

@Slf4j
@Service
@AllArgsConstructor
public class ProductValidationService {
    private static final String CURRENT_SOURCE = "PRODUCT_VALIDATION_SERVICE";

    private final JsonUtil jsonUtil;
    private final KafkaProducer producer;
    private final ProductRepository productRepository;
    private final ValidationRepository validationRepository;
    private final EventHistoryManager HistoryManager;
    private final createValidationService createValidationService;
    private final ProductStatus productStatus;
    public void validateExistingProducts(Event event) {
       try {
            checkCurrentValidation(event);
            createValidationService.createValidation(event,true);
            handleSuccess(event);
        } catch (Exception ex) {
            log.error("Error trying to validate product: ", ex);
            handleFailCurrentNotExecuted(event, ex.getMessage());
        }
        producer.sendEvent(jsonUtil.toJason(event));
    }
    private void checkCurrentValidation(Event event) {
        productStatus.validateProductsInformed(event);
        if (validationRepository.existsByOrderIdAndTransactionId(
                event.getOrderId(), event.getTransactionId())) {
            throw new ValidationException("There's another transactionId for this validation.");
        }
        event.getPayload().getProducts().forEach(product -> {
            productStatus.validateProductInformed(product);
            productStatus.validateExistingProduct(product.getProduct().getCode());
        });
    }
    private void handleSuccess(Event event) {
        event.setStatus( SUCESSO);
        event.setSource(CURRENT_SOURCE);
        HistoryManager.addHistory(event, "Products are validated successfully!");
    }
    private void handleFailCurrentNotExecuted(Event event, String message) {
        event.setStatus(ROLLBACK_PENDING);
        event.setSource(CURRENT_SOURCE);
        HistoryManager.addHistory(event, "Fail to validate products: ".concat(message));
    }
    public void rollbackEvent(Event event) {
        changeValidationToFail(event);
        event.setStatus(FAIL);
        event.setSource(CURRENT_SOURCE);
        HistoryManager.addHistory(event, "Rollback executed on product validation!");
        producer.sendEvent(jsonUtil.toJason(event));
    }
    private void changeValidationToFail(Event event) {
         validationRepository
         .findByOrderIdAndTransactionId(event.getOrderId(), event.getTransactionId())
         .ifPresentOrElse(validation -> {
           validation.setSuccess(false);
           validationRepository.save(validation);
     },() -> createValidationService.createValidation(event, false));
   }
}
