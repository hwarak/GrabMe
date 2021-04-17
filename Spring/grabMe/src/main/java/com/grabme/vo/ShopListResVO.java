package com.grabme.vo;

import lombok.Data;

@Data
public class ShopListResVO {

	private int reservationIdx;
	private int shopIdx;
	private String shopThumbnail;
	private String shopTitle;
	private String shopAddress;
	private String shopPhone;
	private String timeDate;
	private String timeAvailable;

	private ShopListResVO() {}
	
	private static class ShopResVOHolder {
		public static final ShopListResVO INSTANCE = new ShopListResVO();
	}

	public static ShopListResVO getShopResVOObject() {
		return ShopResVOHolder.INSTANCE;
	}


	

	

}
