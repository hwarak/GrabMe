package com.grabme.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grabme.dao.ExternalChannelDAO;

@Service
public class ExternalChannelSeviceImpl implements ExternalChannelService {

	@Autowired
	private ExternalChannelDAO dao;

	// insert shop url
	@Override
	public void insertURL(int shop_idx, String openkatalkURL, String instaURL) {
		dao.insertURL(shop_idx, openkatalkURL, instaURL);

	}

}
