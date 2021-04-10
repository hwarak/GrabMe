package com.grabme.web;

import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.grabme.response.DefaultRes;
import com.grabme.response.ResponseMessage;
import com.grabme.response.StatusCode;
import com.grabme.service.JsonEcDcService;
import com.grabme.service.TimeService;
import com.grabme.vo.SignResVO;
import com.grabme.vo.TimeVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;

@Api(tags = { "3. Time" })
@RequiredArgsConstructor
@RestController
@RequestMapping("/time")
public class TimeController {

	@Autowired
	private TimeService time_service;

	@Autowired
	private JsonEcDcService json_service;

	// 시간 테이블 업데이트 하기

	// (1) 시간 추가
	@ApiOperation(value = "시각 추가", notes = "시각을 추가한다.")
	@PostMapping
	@ResponseBody
	public ResponseEntity timeInfoPost(@ApiParam(value = "시간 정보", required = true) @RequestBody String str) {

		System.out.println("도연씨 요청 : " + str);

		SignResVO svo = SignResVO.getSignResVOObject();

		// json 파싱 후 반환
		JSONObject obj = json_service.jsonDc(str);

		String date = (String) obj.get("date");
		String time = (String) obj.get("time");
		int shop_idx = (int) (long) obj.get("shop_idx");
		int people = (int) (long) obj.get("people");

		time_service.insertTime(shop_idx, date, time, people);

		svo.setResult("ok");
		svo.setCode("");

		return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.CREATE_TIME, svo), HttpStatus.OK);
	}

	// (2) 시간 삭제
	@ApiOperation(value = "시각 삭제", notes = "해당 시각을 삭제한다.")
	@DeleteMapping
	@ResponseBody
	public ResponseEntity timeInfoDelete(@ApiParam(value = "시간 정보", required = true) @RequestBody String str) {

		System.out.println("도연씨 요청 : " + str);

		SignResVO svo = SignResVO.getSignResVOObject();

		// json 파싱 후 반환
		JSONObject obj = json_service.jsonDc(str);
		int idx = (int) (long) obj.get("idx");

		time_service.deleteTime(idx);

		svo.setResult("ok");
		svo.setCode("");

		return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.DELETE_TIME, svo), HttpStatus.OK);
	}

	// (3) 시간 업데이트
	@ApiOperation(value = "시각 업데이트", notes = "해당 시각을 다른 시각으로 바꾼다.")
	@PutMapping
	@ResponseBody
	public String timeInfoPut(@ApiParam(value = "시간 정보", required = true) @RequestBody TimeVO tvo) {

		time_service.updateTime(tvo.getTime(), tvo.getIdx());

		JsonObject obj = new JsonObject();
		obj.addProperty("result", "ok");

		return obj.toString();
	}

	// (4) 시간 가져오기
	@ApiOperation(value = "날짜별 예약 시각 보기", notes = "날짜를 받으면 예약시각들을 보여준다.")
	@GetMapping
	@ResponseBody
	public String timeInfoGet(@ApiParam(value = "가게 번호", required = true) @RequestParam int shop_idx,
			@ApiParam(value = "날짜", required = true) @RequestParam String date) {

		List<TimeVO> list = time_service.selectDate(shop_idx, date);

		return new Gson().toJsonTree(list).toString();
	}

	/*
	 * // 시간 배열로 받기
	 * 
	 * @ApiOperation(value = "예약시간 추가", notes = "날짜와 예약시간을 입력받아 데이터베이스에 추가한다.")
	 * 
	 * @PostMapping
	 * 
	 * @ResponseBody public String timeInfoPost(@ApiParam(value = "예약시간,날짜 정보 받기",
	 * required = true) @RequestBody String timeInfo,
	 * 
	 * @ApiParam(value = "가게 번호", required = true) @RequestParam int shop_idx) {
	 * 
	 * try {
	 * 
	 * JSONParser jsonParser = new JSONParser(); JSONObject jsonObj = (JSONObject)
	 * jsonParser.parse(timeInfo);
	 * 
	 * String date = (String) jsonObj.get("date");
	 * 
	 * JSONArray time = (JSONArray) jsonObj.get(date); Iterator<String> iterator =
	 * time.iterator();
	 * 
	 * while (iterator.hasNext()) { time_service.insertTime(shop_idx, date,
	 * iterator.next()); }
	 * 
	 * } catch (Exception e) { // TODO: handle exception }
	 * 
	 * JsonObject obj = new JsonObject(); obj.addProperty("result", "ok");
	 * 
	 * return obj.toString(); }
	 */

}
