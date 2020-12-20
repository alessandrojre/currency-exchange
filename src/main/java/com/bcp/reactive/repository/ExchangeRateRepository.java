package com.bcp.reactive.repository;

import com.bcp.reactive.entity.exchange.ExchangeRate;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

/**
 * @author Alessandro Riega
 */
public interface ExchangeRateRepository extends ReactiveMongoRepository<ExchangeRate, String> {

    Mono<ExchangeRate> findByCurrencyTypeOriginAndCurrencyTypeDestination(String currencyTypeOrigin, String currencyTypeDestination);
}
