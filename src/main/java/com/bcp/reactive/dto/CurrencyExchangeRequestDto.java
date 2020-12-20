package com.bcp.reactive.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * @author Alessandro Riega
 */
@Builder
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CurrencyExchangeRequestDto {

    private BigDecimal amount;

    private String originCurrency;

    private String destinationCurrency;

    private BigDecimal exchangeRate;
}
