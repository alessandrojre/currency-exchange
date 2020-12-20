package com.bcp.reactive.controller.exchange.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @author Alessandro Riega
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class CurrencyExchangeRequest {

    @NotNull(message = "amount is required")
    private BigDecimal amount;

    @NotNull(message = "originCurrency is required")
    private String originCurrency;

    @NotNull(message = "destinationCurrency is required")
    private String destinationCurrency;

}
