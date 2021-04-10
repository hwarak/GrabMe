package com.grabme.vo;

import lombok.Data;

@Data
public class ShopResVO {

	private int idx;
	private int reservation_idx;
	private String date;
	private String time;
	private String thumbnail;
	private String title;
	private String address;

	private ShopResVO() {}
	
	private static class ShopResVOHolder {
		public static final ShopResVO INSTANCE = new ShopResVO();
	}

	public static ShopResVO getShopResVOObject() {
		return ShopResVOHolder.INSTANCE;
	}


	

	

}
