package com.grabme.vo;

import lombok.Data;

@Data
public class ShopListResVO {

	private int reservation_idx;
	private int idx;
	private String thumbnail;
	private String title;
	private String address;
	private String phone;
	private String time_date;
	private String time_time;

	private ShopListResVO() {}
	
	private static class ShopResVOHolder {
		public static final ShopListResVO INSTANCE = new ShopListResVO();
	}

	public static ShopListResVO getShopResVOObject() {
		return ShopResVOHolder.INSTANCE;
	}


	

	

}
