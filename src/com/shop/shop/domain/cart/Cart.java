package com.shop.shop.domain.cart;

import java.sql.Timestamp;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Cart {
	private int id;
	private int userId;
	private int productId;
	private Timestamp createDate;
}
