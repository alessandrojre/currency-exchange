package com.bcp.reactive.service;

import com.bcp.reactive.entity.exchange.CurrencyExchange;
import reactor.core.publisher.Mono;

/**
 * @author Alessandro Riega
 */
public interface CurrencyExchangeService {

    Mono<CurrencyExchange> saveExchangeRate(CurrencyExchange currencyExchange);

}
