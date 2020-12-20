package com.bcp.reactive.repository;

import com.bcp.reactive.entity.exchange.CurrencyExchange;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

/**
 * @author Alessandro Riega
 */
public interface CurrencyExchangeRepository extends ReactiveMongoRepository<CurrencyExchange, String> {

}
