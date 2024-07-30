package br.com.microservices.orchestrated.paymentservice.core.enuns;

public enum ESagaStatus {
    SUCESSO,
    ROLLBACK_PENDING,
    FAIL
}
