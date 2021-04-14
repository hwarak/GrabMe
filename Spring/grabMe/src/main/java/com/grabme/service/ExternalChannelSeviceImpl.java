package com.grabme.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grabme.dao.ExternalChannelDAO;

@Service
public class ExternalChannelSeviceImpl implements ExternalChannelService {

	@Autowired
	private ExternalChannelDAO dao;

	// 가게 채널 등록
	@Override
	public void insertURL(int shopIdx, String katalkURL, String instaURL) {
		dao.insertURL(shopIdx, katalkURL, instaURL);

	}

}
