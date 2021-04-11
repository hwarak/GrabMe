package com.grabme.vo;

import lombok.Data;

@Data
public class ShopAllVO {

	private int user_idx;
	private int shop_idx;
	private int category_idx;
	private String thumbnail;
	private String title;
	private String address;
	private String introduction;
	private String openkatalkURL;
	private String instaURL;

}
