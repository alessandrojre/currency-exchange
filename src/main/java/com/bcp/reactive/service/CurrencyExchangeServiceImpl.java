package com.bcp.reactive.service;

import com.bcp.reactive.entity.exchange.CurrencyExchange;
import com.bcp.reactive.repository.CurrencyExchangeRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * @author Alessandro Riega
 */
@Service
public class CurrencyExchangeServiceImpl implements CurrencyExchangeService {

    private final CurrencyExchangeRepository currencyExchangeRepository;

    public CurrencyExchangeServiceImpl(CurrencyExchangeRepository currencyExchangeRepository) {
        this.currencyExchangeRepository = currencyExchangeRepository;
    }

    @Override
    public Mono<CurrencyExchange> saveExchangeRate(CurrencyExchange currencyExchange) {
        return currencyExchangeRepository.save(currencyExchange);
    }
}
