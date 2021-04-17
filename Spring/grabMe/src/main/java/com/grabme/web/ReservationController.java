package com.grabme.web;

import java.util.List;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.grabme.response.DefaultRes;
import com.grabme.response.ResponseMessage;
import com.grabme.response.StatusCode;
import com.grabme.service.JsonEcDcService;
import com.grabme.service.ReservationService;
import com.grabme.service.UserService;
import com.grabme.vo.ShopListResVO;
import com.grabme.vo.SignResVO;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/reservation")
public class ReservationController {

	@Autowired
	private ReservationService reservationService;

	@Autowired
	private JsonEcDcService jsonService;

	// 예약하는 유저번호와 타임을 입력받아 데이터베이스에 저장한다
	@PostMapping
	@ResponseBody
	public ResponseEntity reservationPost(@RequestBody String str) {

		System.out.println("클라이언트 요청 : " + str);
		
		SignResVO srvo = SignResVO.getSignResVOObject();

		// json 파싱 후 반환
		JSONObject obj = jsonService.jsonDc(str);

		int timeIdx = (int) (long) obj.get("timeIdx");
		int userIdx = (int) (long) obj.get("userIdx");

		// 유저 idx와 시간 idx를 가져와 디비에 저장해준다
		reservationService.insertReservation(userIdx, timeIdx);
		
		// front에서 받는 데이터 형태로 맞춰주기
		srvo.setResult("ok");
		srvo.setCode("");
		
		return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.CREATE_RESERVATION,srvo),HttpStatus.OK);
		
	}

	// 유저 번호를 입력받아 예약 내역을 출력한다.
	@GetMapping
	@ResponseBody
	public ResponseEntity reservationGet(@RequestParam int userIdx) {

		// 앞으로 예약된 예약리스트를 가져온다.(과거는 가져오지 않음)
		List<ShopListResVO> list = reservationService.selectReservationList(userIdx);
		if (list.isEmpty()) {
			// 리스트가 비어있을 때 에외처리
			return new ResponseEntity(DefaultRes.res(StatusCode.NO_CONTENT, ResponseMessage.NO_CONTENT, list), HttpStatus.OK);
		}

		return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.SEND_LIST,list),HttpStatus.OK);
	}

	// 예약번호를 가져와 예약을 삭제한다
	@DeleteMapping
	@ResponseBody
	public ResponseEntity reservationDelete(@RequestBody String str) {

		System.out.println("클라이언트 요청 : " + str);
		
		SignResVO srvo = SignResVO.getSignResVOObject();

		// json 파싱 후 반환
		JSONObject obj = jsonService.jsonDc(str);

		int reservationIdx = (int) (long) obj.get("reservationIdx");
		 
		// 선택된 예약 idx 찾아서 삭제하기
		reservationService.deleteReservation(reservationIdx);

		// front에서 받는 데이터 형태로 맞춰주기
		srvo.setResult("ok");
		srvo.setCode("");
		
		return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.DELETE_RESERVATION,srvo),HttpStatus.OK);
	}
}
