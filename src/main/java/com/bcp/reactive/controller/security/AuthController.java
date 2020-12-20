package com.bcp.reactive.controller.security;

import com.bcp.reactive.controller.security.dto.UserRequest;
import com.bcp.reactive.controller.security.dto.UserResponse;
import com.bcp.reactive.security.Encoder;
import com.bcp.reactive.security.JWTUtil;
import com.bcp.reactive.service.UserSecurityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * @author Alessandro Riega
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JWTUtil jwtUtil;

    private final Encoder encoder;

    private final UserSecurityService userSecurityService;

    public AuthController(JWTUtil jwtUtil, Encoder encoder, UserSecurityService userSecurityService) {
        this.jwtUtil = jwtUtil;
        this.encoder = encoder;
        this.userSecurityService = userSecurityService;
    }

    @PostMapping("/login")
    public Mono<ResponseEntity<?>> login(@RequestBody UserRequest userRequest) {

        return userSecurityService.findByUsername(userRequest.getUsername()).map((userDetails) -> {
            if (encoder.encode(userRequest.getPassword()).contentEquals(userDetails.getPassword())) {
                return ResponseEntity.ok(new UserResponse(
                        jwtUtil.generateToken(userDetails),
                        userRequest.getUsername()));
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
        }).defaultIfEmpty(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }

}
