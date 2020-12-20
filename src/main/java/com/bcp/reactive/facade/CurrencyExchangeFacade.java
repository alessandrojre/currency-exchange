package com.bcp.reactive.facade;

import com.bcp.reactive.controller.exchange.dto.CurrencyExchangeRequest;
import com.bcp.reactive.controller.exchange.dto.ExchangeRateRequest;
import com.bcp.reactive.dto.CurrencyExchangeRequestDto;
import com.bcp.reactive.dto.CurrencyExchangeResponseDto;
import com.bcp.reactive.entity.exchange.CurrencyExchange;
import com.bcp.reactive.entity.exchange.ExchangeRate;
import com.bcp.reactive.service.CurrencyExchangeService;
import com.bcp.reactive.service.ExchangeRateService;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * @author Alessandro Riega
 */
@Component
public class CurrencyExchangeFacade {

    private final ExchangeRateService exchangeRateService;
    private final CurrencyExchangeService currencyExchangeService;

    public CurrencyExchangeFacade(ExchangeRateService exchangeRateService, CurrencyExchangeService currencyExchangeService) {
        this.exchangeRateService = exchangeRateService;
        this.currencyExchangeService = currencyExchangeService;
    }

    public Mono<CurrencyExchangeResponseDto> calculateExchangeRate(CurrencyExchangeRequest currencyExchangeRequest) {
        CurrencyExchangeRequestDto currencyExchangeRequestDto = buildCurrencyExchangeRequestDto(currencyExchangeRequest);
        return exchangeRateService.getExchangeRate(currencyExchangeRequestDto)
                .flatMap(exchangeRate -> saveExchangeRate(exchangeRate, currencyExchangeRequestDto));
    }

    public Mono<ExchangeRate> updateExchangeRate(ExchangeRateRequest exchangeRateRequest) {
        CurrencyExchangeRequestDto currencyExchangeRequestDto = buildExchangeRateRequestDto(exchangeRateRequest);
        return exchangeRateService.getExchangeRate(currencyExchangeRequestDto).flatMap(exchangeRate -> {
            exchangeRate.setExchangeRate(exchangeRateRequest.getExchangeRate());
            return exchangeRateService.saveExchangeRate(exchangeRate);
        });
    }

    private Mono<CurrencyExchangeResponseDto> saveExchangeRate(ExchangeRate exchangeRate, CurrencyExchangeRequestDto currencyExchangeRequestDto) {
        CurrencyExchange currencyExchange = CurrencyExchange.builder()
                .exchangeRate(exchangeRate.getExchangeRate())
                .amount(currencyExchangeRequestDto.getAmount())
                .originCurrency(currencyExchangeRequestDto.getOriginCurrency())
                .destinationCurrency(currencyExchangeRequestDto.getDestinationCurrency())
                .amountWithExchangeRate(exchangeRate.getExchangeRate().multiply(currencyExchangeRequestDto.getAmount()))
                .build();
        return currencyExchangeService.saveExchangeRate(currencyExchange).flatMap(this::buildCurrencyExchangeResponseDto);
    }

    private Mono<CurrencyExchangeResponseDto> buildCurrencyExchangeResponseDto(CurrencyExchange currencyExchange) {
        return Mono.just(CurrencyExchangeResponseDto.builder()
                .exchangeRate(currencyExchange.getExchangeRate())
                .amount(currencyExchange.getAmount())
                .originCurrency(currencyExchange.getOriginCurrency())
                .destinationCurrency(currencyExchange.getDestinationCurrency())
                .amountWithExchangeRate(currencyExchange.getAmountWithExchangeRate())
                .build());
    }

    private CurrencyExchangeRequestDto buildCurrencyExchangeRequestDto(CurrencyExchangeRequest currencyExchangeRequest) {
        return CurrencyExchangeRequestDto.builder()
                .amount(currencyExchangeRequest.getAmount())
                .originCurrency(currencyExchangeRequest.getOriginCurrency().toUpperCase())
                .destinationCurrency(currencyExchangeRequest.getDestinationCurrency().toUpperCase()).build();
    }

    private CurrencyExchangeRequestDto buildExchangeRateRequestDto(ExchangeRateRequest exchangeRateRequest) {
        return CurrencyExchangeRequestDto.builder()
                .exchangeRate(exchangeRateRequest.getExchangeRate())
                .originCurrency(exchangeRateRequest.getCurrencyTypeOrigin().toUpperCase())
                .destinationCurrency(exchangeRateRequest.getCurrencyTypeDestination().toUpperCase()).build();
    }

}
