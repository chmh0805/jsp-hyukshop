package com.shop.shop.domain.user;

import lombok.Data;

@Data
public class KakaoUser {
	private int id; // 유저 id(번호) 값
	private String nickname; // 이름
	private String auth;
}
