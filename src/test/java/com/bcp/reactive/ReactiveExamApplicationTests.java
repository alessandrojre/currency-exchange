package com.bcp.reactive;

import com.bcp.reactive.controller.exchange.dto.CurrencyExchangeRequest;
import com.bcp.reactive.controller.exchange.dto.ExchangeRateRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

/**
 * @author Alessandro Riega
 */
@AutoConfigureWebTestClient
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class ReactiveExamApplicationTests {

    @Autowired
    private WebTestClient client;

    @Test
    public void contextLoads() {
    }

    @Test
    public void calculateCurrencyExchangeTest() {

        CurrencyExchangeRequest currencyExchangeRequest = CurrencyExchangeRequest.builder()
                .amount(new BigDecimal(250))
                .originCurrency("USD")
                .destinationCurrency("PEN")
                .build();

        client.post().uri("/v1/exchange/currency")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .header("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJyb2xlIjpbIlJPTEVfQURNSU4iXSwic3ViIjoiaGViZXJ0IiwiaWF0IjoxNjA4NDUxMDU1LCJleHAiOjMxMzMyODQzOTV9.97tVaOrqzF3v4o_Z8mCESTy737BbkVhoCZaHjtW9nGmqmXH9EQHndfUjkubTEo85nUzJucrOSUkaR62R6TqMew")
                .body(Mono.just(currencyExchangeRequest), CurrencyExchangeRequest.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.amount").isNotEmpty()
                .jsonPath("$.amount").isEqualTo("250")
                .jsonPath("$.amountWithExchangeRate").isEqualTo("895.0")
                .jsonPath("$.originCurrency").isEqualTo("USD")
                .jsonPath("$.destinationCurrency").isEqualTo("PEN")
                .jsonPath("$.exchangeRate").isEqualTo("3.58");

    }


    @Test
    public void updateCurrencyExchangeTest() {

        ExchangeRateRequest exchangeRateRequest = ExchangeRateRequest.builder()
                .exchangeRate(new BigDecimal(3.58).setScale(2, BigDecimal.ROUND_HALF_EVEN))
                .currencyTypeOrigin("USD")
                .currencyTypeDestination("PEN")
                .build();

        client.patch().uri("/v1/exchange/rate")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .header("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJyb2xlIjpbIlJPTEVfQURNSU4iXSwic3ViIjoiaGViZXJ0IiwiaWF0IjoxNjA4NDUxMDU1LCJleHAiOjMxMzMyODQzOTV9.97tVaOrqzF3v4o_Z8mCESTy737BbkVhoCZaHjtW9nGmqmXH9EQHndfUjkubTEo85nUzJucrOSUkaR62R6TqMew")
                .body(Mono.just(exchangeRateRequest), ExchangeRateRequest.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.exchangeRate").isNotEmpty()
                .jsonPath("$.exchangeRate").isEqualTo("3.58")
                .jsonPath("$.currencyTypeDestination").isEqualTo("PEN")
                .jsonPath("$.currencyTypeOrigin").isEqualTo("USD");

    }

}
