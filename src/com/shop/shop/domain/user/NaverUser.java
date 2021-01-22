package com.shop.shop.domain.user;

import lombok.Data;

@Data
public class NaverUser {
	private int id; // 유저 id(번호) 값
	private String email; // 이메일
	private String mobile; // 전화번호
	private String name; // 이름
	private String auth;
}
