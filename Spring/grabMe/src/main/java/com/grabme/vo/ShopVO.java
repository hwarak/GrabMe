package com.grabme.vo;

import lombok.Data;

@Data
public class ShopVO {

	private int idx;
	private int user_idx;
	private int category_idx;
	private String thumbnail;
	private String title;
	private String address;
	private String introduction;

}
