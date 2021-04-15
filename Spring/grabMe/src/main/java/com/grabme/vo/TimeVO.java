package com.grabme.vo;

import lombok.Data;

@Data
public class TimeVO {
	private int timeIdx;
	private int shopIdx;
	private String timeDate;
	private String timeTime;
	private boolean timeStatus;
	private int timePeople;
	private int timeMaxPeople;
	private boolean timeDelStatus;
}
