package com.bcp.reactive.repository;

import com.bcp.reactive.entity.security.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

/**
 * @author Alessandro Riega
 */
public interface UserSecurityRepository extends ReactiveMongoRepository<User, String> {

    Mono<User> findByUsername(String username);
}
