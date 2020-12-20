package com.bcp.reactive.entity.exchange;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

/**
 * @author Alessandro Riega
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "exchangeRate")
public class ExchangeRate {

    @Id
    private String id;
    private String currencyTypeOrigin;
    private String currencyTypeDestination;
    private BigDecimal exchangeRate;

}
