package com.bcp.reactive.controller.exchange.dto;

import lombok.*;

import java.math.BigDecimal;

/**
 * @author Alessandro Riega
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ExchangeRateRequest {
    private String currencyTypeOrigin;
    private String currencyTypeDestination;
    private BigDecimal exchangeRate;
}
