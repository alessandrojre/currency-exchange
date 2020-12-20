package com.bcp.reactive.dto;

import lombok.Getter;
import lombok.Builder;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

/**
 * @author Alessandro Riega
 */
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CurrencyExchangeResponseDto {

    private BigDecimal amount;
    private BigDecimal amountWithExchangeRate;
    private String originCurrency;
    private String destinationCurrency;
    private BigDecimal exchangeRate;
}
