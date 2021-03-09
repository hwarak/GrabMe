package com.grabme.web;

import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.grabme.service.TimeService;
import com.grabme.vo.TimeVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;

@Api(tags = { "4. Time" })
@RequiredArgsConstructor
@RestController
@RequestMapping("/time")
public class TimeController {

	@Autowired
	private TimeService time_service;

	@ApiOperation(value = "예약시간 추가", notes = "날짜와 예약시간을 입력받아 데이터베이스에 추가한다.")
	@PostMapping
	@ResponseBody
	public String timeInfoPost(@ApiParam(value = "예약시간,날짜 정보 받기", required = true) @RequestBody String timeInfo,
			@ApiParam(value = "가게 번호", required = true) @RequestParam int shop_idx) {

		try {

			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObj = (JSONObject) jsonParser.parse(timeInfo);

			String date = (String) jsonObj.get("date");

			JSONArray time = (JSONArray) jsonObj.get(date);
			Iterator<String> iterator = time.iterator();

			while (iterator.hasNext()) {
				time_service.insertTime(shop_idx, date, iterator.next());
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

		JsonObject obj = new JsonObject();
		obj.addProperty("result", "ok");

		return obj.toString();
	}

	@ApiOperation(value = "날짜별 예약시간 보기", notes = "날짜를 받으면 예약시간들을 보여준다.")
	@GetMapping
	@ResponseBody
	public String timeInfoGet(@ApiParam(value = "날짜", required = true) @RequestBody TimeVO tvo) {

		List<TimeVO> list = time_service.selectDate(tvo.getShop_idx(), tvo.getDate());

		return new Gson().toJsonTree(list).toString();
	}
	
}
