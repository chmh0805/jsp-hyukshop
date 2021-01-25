package com.shop.shop.domain.favor;

import java.sql.Timestamp;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Favorite {
	private int id;
	private int userId;
	private int productId;
	private Timestamp createDate;
}
