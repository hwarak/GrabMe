package com.grabme.dao;

public interface ExternalChannelDAO {

	// 가게 채널 등록
	public void insertURL(int shopIdx, String katalkURL, String instaURL);

}
