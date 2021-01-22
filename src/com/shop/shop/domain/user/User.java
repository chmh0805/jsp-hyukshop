package com.shop.shop.domain.user;

import java.sql.Timestamp;

import lombok.Builder;

import lombok.Data;

@Builder
@Data
public class User {
	private int id; // 번호
	private String username; // 아이디
	private String name; // 이름
	private String email;
	private String phone;
	private String address;
	private String password;
	private String auth;
	private long kakaoId;
	private long naverId;
	private Timestamp createDate;
}
