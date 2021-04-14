package com.grabme.vo;

import lombok.Data;

@Data
public class ShopResVO {

	private int reservation_idx;
	private int idx;
	private String thumbnail;
	private String title;
	private String address;
	private String phone;
	private String time_date;
	private String time_time;

	private ShopResVO() {}
	
	private static class ShopResVOHolder {
		public static final ShopResVO INSTANCE = new ShopResVO();
	}

	public static ShopResVO getShopResVOObject() {
		return ShopResVOHolder.INSTANCE;
	}


	

	

}
