package com.grabme.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.grabme.service.CategoryService;
import com.grabme.vo.ShopVO;

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

	@ApiOperation(value = "주변에 있는 가게들", notes = "주변에 있는 가게들 리스트를 10개씩 가져온다.")
	@GetMapping
	@ResponseBody
	public Map<String, Object> getShopList(@ApiParam(value = "위도", required = true) @RequestParam double y,
			@ApiParam(value = "경도", required = true) @RequestParam double x,
			@ApiParam(value = "카테고리 번호", required = true) @RequestParam int category_idx,
			@ApiParam(value = "시작 번호", required = true) @RequestParam int startNum) {

		List<ShopVO> list = category_service.selectCategoryWithXY(x, y, category_idx, startNum);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("shopList", list);

		return map;
	}

	@ApiOperation(value = "가게 이름 검색", notes = "검색어가 포함된 가게들을 거리순으로 10개씩 가져온다.")
	@GetMapping("/2")
	@ResponseBody
	public Map<String, Object> getSearchTitle(@ApiParam(value = "위도", required = true) @RequestParam double y,
			@ApiParam(value = "경도", required = true) @RequestParam double x,
			@ApiParam(value = "카테고리 번호", required = true) @RequestParam int category_idx,
			@ApiParam(value = "시작 번호", required = true) @RequestParam int startNum,
			@ApiParam(value = "검색 단어", required = true) @RequestParam String word) {

		List<ShopVO> list = category_service.searchTitle(x, y, category_idx, startNum, word);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("shopList", list);

		return map;
	}

}