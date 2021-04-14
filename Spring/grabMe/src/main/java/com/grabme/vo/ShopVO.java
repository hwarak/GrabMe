package com.grabme.vo;

import lombok.Data;

@Data
public class ShopVO {
	private int shopIdx;
	private int ownerIdx;
	private int categoryIdx;
	private String shopThumbnail;
	private String shopTitle;
	private String shopAddress;
	private String shopPhone;
	private String shopIntroduction;
	private double shopLon;
	private double shopLat;
	private String shopKatalkUrl;
	private String shopInstaUrl;
}
