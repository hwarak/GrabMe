package com.grabme.web;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.JsonObject;
import com.grabme.service.CategoryService;
import com.grabme.service.ExternalChannelService;
import com.grabme.service.S3Service;
import com.grabme.service.ShopService;
import com.grabme.vo.CategoryVO;
import com.grabme.vo.ExternalChannelVO;
import com.grabme.vo.ShopAllVO;
import com.grabme.vo.ShopVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;

@Api(tags = { "3. Shop" })
@RequiredArgsConstructor
@RestController
@RequestMapping("/shop")
public class ShopController {

	@Autowired
	private S3Service s3_service;

	@Autowired
	private ShopService shop_service;

	@Autowired
	private CategoryService category_service;

	@Autowired
	private ExternalChannelService exchannel_service;

	// 가게 정보를 가져온다
	@ApiOperation(value = "가게 정보", notes = "가게 정보를 가져온다.")
	@GetMapping
	@ResponseBody
	public String shopInfoGet(@ApiParam(value = "유저 번호", required = true) @RequestParam int user_idx) {

		JsonObject obj = new JsonObject();
		List<ShopAllVO> list = shop_service.selectShopAllinfo(user_idx);

		int cn = shop_service.checkShop(user_idx);
		// 유저 번호로 등록된 가게가 있는지 확인

		if (cn == 0) {
			// 등록된 가게가 없다
			obj.addProperty("result", "no");
		} else {
			// 등록된 가게 있음
			// 가게 정보 보여주기
			obj.addProperty("result", "ok");
			for (ShopAllVO tmp : list) {
				obj.addProperty("category", category_service.selectName(tmp.getCategory_idx()));
				obj.addProperty("thumbnail", tmp.getThumbnail());
				obj.addProperty("title", tmp.getTitle());
				obj.addProperty("address", tmp.getAddress());
				obj.addProperty("introduction", tmp.getIntroduction());
				obj.addProperty("openkatalkURL", tmp.getOpenkatalkURL());
				obj.addProperty("instaURL", tmp.getInstaURL());
			}
		}
		return obj.toString();
	}

	// 가게 정보를 등록한다
	@ApiOperation(value = "가게 등록", notes = "가게 정보를 등록한다.")
	@PostMapping
	@ResponseBody
	public String shopInfoPost(@RequestParam int user_idx,
			@ApiParam(value = "가게 사진", required = true) @RequestPart MultipartFile file,
			@ApiParam(value = "가게 정보", required = true) ShopVO svo,
			@ApiParam(value = "카테고리 정보", required = true) CategoryVO cvo,
			@ApiParam(value = "외부 URL", required = true) ExternalChannelVO evo) throws IOException {

		String filePath = s3_service.upload(file);
		System.out.println("s3에 저장되는 경로 : " + filePath);

		// 가게 등록
		shop_service.insertShop(user_idx, category_service.selectIdx(cvo.getcategory()), filePath, svo.getTitle(),
				svo.getAddress(), svo.getIntroduction());

		// 가게 url 등록
		exchannel_service.insertURL(shop_service.selectShopIdx(user_idx), evo.getOpenkatalkURL(), evo.getInstaURL());

		// test용
		System.out.println(file.getOriginalFilename());
		System.out.println(user_idx);
		System.out.println(cvo.getcategory() + "/" + category_service.selectIdx(cvo.getcategory()));
		System.out.println(evo.getOpenkatalkURL() + "/" + evo.getInstaURL());

		JsonObject obj = new JsonObject();
		obj.addProperty("result", "ok");

		return obj.toString();
	}

	@ApiOperation(value = "가게 업데이트", notes = "가게 정보를 업데이트한다.")
	@PutMapping
	@ResponseBody
	public String shopInfoPut(@ApiParam(value = "업데이트 할 가게 정보", required = true) @RequestBody ShopAllVO savo,
			@ApiParam(value = "가게 번호", required = true) @RequestParam int shop_idx) {
		// 가게 정보를 업데이트 한다
		shop_service.updateShopAllinfo(shop_idx, savo.getCategory_idx(), savo.getThumbnail(), savo.getTitle(),
				savo.getAddress(), savo.getIntroduction(), savo.getOpenkatalkURL(), savo.getInstaURL());

		JsonObject obj = new JsonObject();
		obj.addProperty("result", "ok");

		return obj.toString();
	}
}
