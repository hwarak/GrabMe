package com.grabme.web;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.grabme.service.S3Service;
import com.grabme.service.ShopService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = { "3. Shop" })
@RequiredArgsConstructor
@RestController
@RequestMapping("/shop")
public class ShopController {

	@Autowired
	ShopService shop_service;

	@Autowired
	S3Service s3_service;

	@ApiOperation(value = "가게 등록", notes = "가게 정보를 입력받아 데이터베이스에 저장한다")
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	public String requestupload1(MultipartHttpServletRequest mtfRequest 
//			@PathVariable("user_idx") int user_idx,
//			@PathVariable("category_idx") int category_idx, @PathVariable("title") String title,
//			@PathVariable("address") String address, @PathVariable("introduction") String introduction
			)
			throws IOException {

		MultipartFile mf = mtfRequest.getFile("file");
		String filePath = s3_service.upload(mf);
		System.out.println(filePath);
		//shop_service.insertShop(1, 1, filePath, "ㅁㅁㅁ", "ㄴㄴㄴ", "ㄹㄹㄹ");

		return "{\"result\":\"ok\"}";

	}

}
