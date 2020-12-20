package com.bcp.reactive.controller.security.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author Alessandro Riega
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserRequest {
	
	private String username;
	
	private String password;

}
