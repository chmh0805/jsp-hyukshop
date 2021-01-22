package com.shop.shop.domain.user.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JoinUser {
	private String name;
	private String username;
	private String email;
	private String phone;
	private String address;
	private String password;
}
