package com.grabme.vo;

import lombok.Data;

@Data
public class TimeVO {
	private int time_idx;
	private int shop_idx;
	private String time_date;
	private String time_time;
	private boolean time_status;
	private int time_people;
	private int time_max_people;
	private boolean time_del_status;
}
