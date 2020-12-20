package com.bcp.reactive.service;

import com.bcp.reactive.dto.CurrencyExchangeRequestDto;
import com.bcp.reactive.entity.exchange.ExchangeRate;
import reactor.core.publisher.Mono;

/**
 * @author Alessandro Riega
 */
public interface ExchangeRateService {

    Mono<ExchangeRate> saveExchangeRate(ExchangeRate exchangeRate);

    Mono<ExchangeRate> getExchangeRate(CurrencyExchangeRequestDto currencyExchangeRequestDto);
}
