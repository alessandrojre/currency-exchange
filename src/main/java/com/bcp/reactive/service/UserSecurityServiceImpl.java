package com.bcp.reactive.service;

import com.bcp.reactive.entity.security.User;
import com.bcp.reactive.repository.UserSecurityRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * @author Alessandro Riega
 */
@Service
public class UserSecurityServiceImpl implements UserSecurityService {

    private final UserSecurityRepository userSecurityRepository;

    public UserSecurityServiceImpl(UserSecurityRepository userSecurityRepository) {
        this.userSecurityRepository = userSecurityRepository;
    }

    @Override
    public Mono<User> saveUser(User user) {
        return userSecurityRepository.save(user);
    }

    @Override
    public Mono<User> findByUsername(String username) {
        return userSecurityRepository.findByUsername(username);
    }
}
