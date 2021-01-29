package com.shop.shop.domain.product.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CheckoutProdDto {
	private int id;
	private String productName;
	private String companyName;
	private String imgUrl_1;
	private long price;
}
