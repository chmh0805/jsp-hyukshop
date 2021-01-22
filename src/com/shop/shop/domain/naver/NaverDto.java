package com.shop.shop.domain.naver;

import lombok.Data;

@Data
public class NaverDto {
	private String access_token;
	private String refresh_token;
	private String token_type;
	private Integer expires_in;
}
