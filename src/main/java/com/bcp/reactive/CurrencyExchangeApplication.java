package com.bcp.reactive;

import com.bcp.reactive.entity.exchange.ExchangeRate;
import com.bcp.reactive.entity.security.Role;
import com.bcp.reactive.entity.security.User;
import com.bcp.reactive.service.ExchangeRateService;
import com.bcp.reactive.service.UserSecurityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import reactor.core.publisher.Flux;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;

/**
 * @author Alessandro Riega
 */
@Slf4j
@SpringBootApplication
public class CurrencyExchangeApplication implements CommandLineRunner {

    private final ReactiveMongoTemplate mongoTemplate;
    private final ExchangeRateService exchangeRateService;
    private final UserSecurityService userSecurityService;

    public CurrencyExchangeApplication(ReactiveMongoTemplate mongoTemplate,
                                       ExchangeRateService exchangeRateService,
                                       UserSecurityService userSecurityService) {
        this.mongoTemplate = mongoTemplate;
        this.exchangeRateService = exchangeRateService;
        this.userSecurityService = userSecurityService;
    }

    public static void main(String[] args) {
        SpringApplication.run(CurrencyExchangeApplication.class, args);
    }

    @Override
    public void run(String... args) {

        deleteCollections();

        /*creación de tipos de cambio*/
        createExchangeRates();

        /*creación de usuarios para security*/
        createUsersSecurity();

    }


    public void deleteCollections() {
        mongoTemplate.dropCollection("exchangeRate").subscribe();
        mongoTemplate.dropCollection("currencyExchange").subscribe();
        mongoTemplate.dropCollection("userSecurity").subscribe();
    }

    public void createExchangeRates() {
        ExchangeRate penToDollar = ExchangeRate.builder()
                .currencyTypeOrigin("PEN").currencyTypeDestination("USD").exchangeRate(new BigDecimal(0.28)
                        .setScale(2, BigDecimal.ROUND_HALF_EVEN)).build();

        ExchangeRate penToEuro = ExchangeRate.builder()
                .currencyTypeOrigin("PEN").currencyTypeDestination("EURO").exchangeRate(new BigDecimal(0.23)
                        .setScale(2, BigDecimal.ROUND_HALF_EVEN)).build();

        ExchangeRate dollarToPen = ExchangeRate.builder()
                .currencyTypeOrigin("USD").currencyTypeDestination("PEN").exchangeRate(new BigDecimal(3.58)
                        .setScale(2, BigDecimal.ROUND_HALF_EVEN)).build();

        ExchangeRate dollarToEuro = ExchangeRate.builder()
                .currencyTypeOrigin("USD").currencyTypeDestination("EURO").exchangeRate(new BigDecimal(0.82)
                        .setScale(2, BigDecimal.ROUND_HALF_EVEN)).build();

        ExchangeRate euroToPen = ExchangeRate.builder()
                .currencyTypeOrigin("EURO").currencyTypeDestination("PEN").exchangeRate(new BigDecimal(4.39)
                        .setScale(2, BigDecimal.ROUND_HALF_EVEN)).build();

        ExchangeRate euroToDollar = ExchangeRate.builder()
                .currencyTypeOrigin("EURO").currencyTypeDestination("USD").exchangeRate(new BigDecimal(1.23)
                        .setScale(2, BigDecimal.ROUND_HALF_EVEN)).build();


        Flux.just(penToDollar, penToEuro, dollarToEuro, dollarToPen, euroToDollar, euroToPen)
                .flatMap(exchangeRateService::saveExchangeRate)
                .doOnNext(exchange -> log.info("save exchangeRate: " + exchange.getCurrencyTypeOrigin() + ", exchangeId: " + exchange.getId()))
                .subscribe(exchange -> log.info("subscribe exchangeRate: " + exchange.getId()));
    }

    public void createUsersSecurity() {

        User userOne = User.builder()
                .username("alessandro")
                .password("xVxAggzVQsNCer0n4LbRA0TvsZQaI/b43pABQ3EW7U4=")
                .enabled(true)
                .roles(Collections.singletonList(Role.ROLE_ADMIN))
                .build();

        User userTwo = User.builder()
                .username("hebert")
                .password("96/Q6+FAAzPGTuXAUfTdWOni5LReekZt0NRFo81/u80=")
                .enabled(true)
                .roles(Collections.singletonList(Role.ROLE_ADMIN))
                .build();

        User userGuest = User.builder()
                .username("guest")
                .password("FIT9/Bg4V+KiTII69LDmpG1412qDoFH6ET0scmrQYjw=")
                .enabled(true)
                .roles(Collections.singletonList(Role.ROLE_USER))
                .build();

        Flux.just(userOne, userTwo,userGuest)
                .flatMap(userSecurityService::saveUser)
                .doOnNext(user -> log.info("saveUser: " + user.getUsername() + ", userId: " + user.getId()))
                .subscribe(user -> log.info("subscribe user: " + user.toString()));
    }
}
