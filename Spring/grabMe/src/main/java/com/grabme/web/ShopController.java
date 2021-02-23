package com.grabme.web;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
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

@RestController
@Api(tags= {"3","Shop"})
@RequestMapping("/api/shop")
public class ShopController {

	@Autowired
	ShopService service_shop;

	@Autowired
	S3Service s3Service;

	@ApiOperation(value = "가게 등록", notes = "가게 정보를 등록한다")
	@RequestMapping(value = "/insert", method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	public String requestupload1(MultipartHttpServletRequest mtfRequest, @PathVariable("user_idx") int user_idx,
			@PathVariable("category_idx") int category_idx, @PathVariable("title") String title,
			@PathVariable("address") String address, @PathVariable("introduction") String introduction) throws IOException {

		MultipartFile mf = mtfRequest.getFile("file");
		String filePath = s3Service.upload(mf);
		service_shop.insertShop(1, 1, filePath, "올타임피트니스", "경기도 괴안동", "헬스장입니다");

		return "{\"result\":\"ok\"}";

	}

}
