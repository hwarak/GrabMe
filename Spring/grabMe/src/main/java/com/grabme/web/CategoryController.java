package com.grabme.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.grabme.response.DefaultRes;
import com.grabme.response.ResponseMessage;
import com.grabme.response.StatusCode;
import com.grabme.service.CategoryService;
import com.grabme.vo.ShopListResVO;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/category")
public class CategoryController {

	@Autowired
	private CategoryService category_service;

	// 주변에 있는 가게들 리스트를 20개씩 가져온다.
	@GetMapping
	@ResponseBody
	public ResponseEntity getShopList(@RequestParam double shopLat,double shopLon,
			int categoryIdx,@RequestParam int startNum) {

		List<ShopListResVO> list = category_service.selectCategoryWithXY(shopLon, shopLat, categoryIdx, startNum);

		if (list.isEmpty()) {
			// 리스트가 비어있을 때 에외처리
			return new ResponseEntity(DefaultRes.res(StatusCode.NO_CONTENT, ResponseMessage.NO_CONTENT, list), HttpStatus.OK);
		}

		return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.SEND_LIST, list), HttpStatus.OK);
	}

	// 검색어가 포함된 가게들을 거리순으로 20개씩 가져온다.
	@GetMapping("/2")
	@ResponseBody
	public ResponseEntity getSearchTitle(@RequestParam double shopLat,double shopLon,
			int categoryIdx,int startNum, @RequestParam String word) {

		List<ShopListResVO> list = category_service.searchTitle(shopLon, shopLat, categoryIdx, startNum, word);

		if (list.isEmpty()) {
			// 리스트가 비어있을 때 에외처리
			return new ResponseEntity(DefaultRes.res(StatusCode.NO_CONTENT, ResponseMessage.NO_CONTENT, list), HttpStatus.OK);
		}

		return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.SEND_LIST, list), HttpStatus.OK);
	}

}