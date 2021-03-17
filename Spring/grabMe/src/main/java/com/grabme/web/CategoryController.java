package com.grabme.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
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

	@ApiOperation(value = "주변에 있는 가게들", notes = "주변에 있는 가게들 리스트를 가져온다.")
	@GetMapping
	@ResponseBody
	public String getShop(@ApiParam(value = "위도", required = true) @RequestParam double y,
			@ApiParam(value = "경도", required = true) @RequestParam double x,
			@ApiParam(value = "카테고리 번호", required = true) @RequestParam int category_idx) {

		List<ShopVO> list = category_service.selectCategoryWithXY(x, y, category_idx);

		return new Gson().toJsonTree(list).toString();
	}

}