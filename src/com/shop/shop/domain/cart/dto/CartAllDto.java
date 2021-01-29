package com.shop.shop.domain.cart.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CartAllDto {
	private int id;
	private int productId;
	private String companyName;
	private String productName;
	private String imgUrl_1;
	private long price;
}
