package com.grabme.vo;

import lombok.Data;

@Data
public class TimeVO {

	private int idx;
	private int shop_idx;
	private String date;
	private String time;
	private boolean status;
	private int people;
	private int max_people;
	private boolean del_status;
	
	

}
