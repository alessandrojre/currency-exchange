package com.bcp.reactive.service;

import com.bcp.reactive.dto.CurrencyExchangeRequestDto;
import com.bcp.reactive.entity.exchange.ExchangeRate;
import com.bcp.reactive.repository.ExchangeRateRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * @author Alessandro Riega
 */
@Service
public class ExchangeRateServiceImpl implements ExchangeRateService {

    private final ExchangeRateRepository exchangeRateRepository;

    public ExchangeRateServiceImpl(ExchangeRateRepository exchangeRateRepository) {
        this.exchangeRateRepository = exchangeRateRepository;
    }

    @Override
    public Mono<ExchangeRate> saveExchangeRate(ExchangeRate exchangeRate) {
        return exchangeRateRepository.save(exchangeRate);
    }

    @Override
    public Mono<ExchangeRate> getExchangeRate(CurrencyExchangeRequestDto currencyExchangeRequestDto) {

        return exchangeRateRepository.findByCurrencyTypeOriginAndCurrencyTypeDestination(
                currencyExchangeRequestDto.getOriginCurrency(),
                currencyExchangeRequestDto.getDestinationCurrency());
    }

}
