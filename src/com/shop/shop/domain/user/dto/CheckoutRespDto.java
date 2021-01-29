package com.shop.shop.domain.user.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CheckoutRespDto {
	private int id;
	private String name;
	private String address;
	private String phone;
	private String email;
}
