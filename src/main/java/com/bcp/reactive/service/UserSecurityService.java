package com.bcp.reactive.service;

import com.bcp.reactive.entity.security.User;
import reactor.core.publisher.Mono;

/**
 * @author Alessandro Riega
 */
public interface UserSecurityService {

    Mono<User> saveUser(User user);

    Mono<User> findByUsername(String username);
}
