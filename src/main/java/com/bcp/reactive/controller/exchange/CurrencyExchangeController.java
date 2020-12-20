package com.bcp.reactive.controller.exchange;

import com.bcp.reactive.controller.exchange.dto.CurrencyExchangeRequest;
import com.bcp.reactive.controller.exchange.dto.ExchangeRateRequest;
import com.bcp.reactive.dto.CurrencyExchangeResponseDto;
import com.bcp.reactive.entity.exchange.ExchangeRate;
import com.bcp.reactive.facade.CurrencyExchangeFacade;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

/**
 * @author Alessandro Riega
 */
@RestController
@RequestMapping("/v1")
public class CurrencyExchangeController {

    private final CurrencyExchangeFacade currencyExchangeFacade;

    public CurrencyExchangeController(CurrencyExchangeFacade currencyExchangeFacade) {
        this.currencyExchangeFacade = currencyExchangeFacade;
    }

    @PostMapping("/exchange/currency")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public Mono<ResponseEntity<CurrencyExchangeResponseDto>> calculateCurrencyExchange(@Valid @RequestBody CurrencyExchangeRequest currencyExchangeRequest) {
        return currencyExchangeFacade.calculateExchangeRate(currencyExchangeRequest).map(p -> ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(p))
                .defaultIfEmpty(ResponseEntity.badRequest().build());
    }


    @PatchMapping("/exchange/rate")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public Mono<ResponseEntity<ExchangeRate>> updateCurrencyExchange(@Valid @RequestBody ExchangeRateRequest exchangeRateRequest) {
        return currencyExchangeFacade.updateExchangeRate(exchangeRateRequest).map(p -> ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(p))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }


}


