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
import com.grabme.vo.ShopResVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;

@Api(tags = { "5. Category" })
@RequiredArgsConstructor
@RestController
@RequestMapping("/category")
public class CategoryController {

	@Autowired
	private CategoryService category_service;

	@ApiOperation(value = "주변에 있는 가게들", notes = "주변에 있는 가게들 리스트를 20개씩 가져온다.")
	@GetMapping
	@ResponseBody
	public ResponseEntity getShopList(@ApiParam(value = "위도", required = true) @RequestParam double y,
			@ApiParam(value = "경도", required = true) @RequestParam double x,
			@ApiParam(value = "카테고리 번호", required = true) @RequestParam int categoryIdx,
			@ApiParam(value = "시작 번호", required = true) @RequestParam int startNum) {

		List<ShopResVO> list = category_service.selectCategoryWithXY(x, y, categoryIdx, startNum);

		if (list.isEmpty()) {
			// 리스트가 비어있을 때 에외처리
			return new ResponseEntity(DefaultRes.res(StatusCode.NO_CONTENT, ResponseMessage.NO_CONTENT, list), HttpStatus.OK);
		}

		return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.SEND_LIST, list), HttpStatus.OK);
	}

	@ApiOperation(value = "가게 이름 검색", notes = "검색어가 포함된 가게들을 거리순으로 20개씩 가져온다.")
	@GetMapping("/2")
	@ResponseBody
	public ResponseEntity getSearchTitle(@ApiParam(value = "위도", required = true) @RequestParam double y,
			@ApiParam(value = "경도", required = true) @RequestParam double x,
			@ApiParam(value = "카테고리 번호", required = true) @RequestParam int categoryIdx,
			@ApiParam(value = "시작 번호", required = true) @RequestParam int startNum,
			@ApiParam(value = "검색 단어", required = true) @RequestParam String word) {

		List<ShopResVO> list = category_service.searchTitle(x, y, categoryIdx, startNum, word);

		if (list.isEmpty()) {
			// 리스트가 비어있을 때 에외처리
			return new ResponseEntity(DefaultRes.res(StatusCode.NO_CONTENT, ResponseMessage.NO_CONTENT, list), HttpStatus.OK);
		}

		return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.SEND_LIST, list), HttpStatus.OK);
	}

}