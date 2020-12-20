package com.bcp.reactive.entity.exchange;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

/**
 * @author Alessandro Riega
 */
@Builder
@Getter
@Setter
@Document(collection = "currencyExchange")
public class CurrencyExchange {

    @Id
    private String id;
    private BigDecimal amount;
    private BigDecimal amountWithExchangeRate;
    private String originCurrency;
    private String destinationCurrency;
    private BigDecimal exchangeRate;

}
