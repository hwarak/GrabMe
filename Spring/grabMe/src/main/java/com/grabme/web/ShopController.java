package com.grabme.web;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.grabme.response.DefaultRes;
import com.grabme.response.ResponseMessage;
import com.grabme.response.StatusCode;
import com.grabme.service.S3Service;
import com.grabme.service.ShopService;
import com.grabme.vo.ShopVO;
import com.grabme.vo.SignResVO;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/shop")
public class ShopController {

	@Autowired
	private S3Service s3Service;

	@Autowired
	private ShopService shopService;

	
	// 가게 정보를 가져온다
	@GetMapping
	@ResponseBody
	public ResponseEntity shopInfoGet(@RequestParam int shopIdx) {

		ShopVO svo = shopService.selectShopAllInfo(shopIdx);

		return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.SEND_SHOP_INFO,svo),HttpStatus.OK);
	}

	
	// 가게 정보를 등록한다
	@PostMapping
	@ResponseBody
	public ResponseEntity shopInfoPost(@RequestPart MultipartFile file,ShopVO svoPre) throws IOException {
		
		String filePath = s3Service.upload(file);
		System.out.println("s3에 저장되는 경로 : " + filePath);
		
		// 빈 칸 체크
		ShopVO svo = shopService.checkEmpty(svoPre);
		
		// 가게 등록
		shopService.insertShop(svo.getOwnerIdx(), svo.getCategoryIdx(), filePath, svo.getShopTitle(),
				svo.getShopAddress(), svo.getShopPhone(), svo.getShopIntroduction(),
				svo.getShopLon(), svo.getShopLat(), svo.getShopKatalkUrl(), svo.getShopInstaUrl());

		SignResVO srvo = SignResVO.getSignResVOObject();
		srvo.setResult("ok");
		srvo.setCode("");

		return new ResponseEntity(DefaultRes.res(StatusCode.CREATED, ResponseMessage.CREATE_SHOP,srvo),HttpStatus.OK);
	}

	
	// 가게 업데이트 
	// multipart 때문에 PutMapping X
	@PostMapping("/2")
	@ResponseBody
	public ResponseEntity shopInfoPut(@RequestPart MultipartFile file,ShopVO svoPre) throws IOException {
		
		String filePath = svoPre.getShopThumbnail();
		
		// file 변경이 감지된다면
		if(!file.getOriginalFilename().equals("")) {
			filePath = s3Service.upload(file);
			System.out.println("s3에 저장되는 경로 : " + filePath);
		}
		
		svoPre.setShopThumbnail(filePath);
	
		// 빈 칸 체크
		ShopVO svo = shopService.checkEmpty(svoPre);
		
		// 가게 정보를 업데이트 한다
		shopService.updateShopAllInfo(svo);

		SignResVO srvo = SignResVO.getSignResVOObject();
		srvo.setResult("ok");
		srvo.setCode("");

		return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.UPDATE_SHOP,srvo),HttpStatus.OK);

	}
}
