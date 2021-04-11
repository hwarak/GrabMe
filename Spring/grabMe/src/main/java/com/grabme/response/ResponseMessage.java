package com.grabme.response;

public class ResponseMessage {
	
	public static final String SEND_CODE = "인증번호 전송 성공";
	public static final String CREATED_USER = "계정 생성 성공";
	public static final String DELETE_USER = "계정 삭제 성공";
	
	public static final String SEND_LIST = "리스트 전송 성공";
	public static final String NO_CONTENT = "보내줄 데이터 없음";
	
	public static final String CREATE_RESERVATION = "예약 성공";
	public static final String DELETE_RESERVATION = "예약 삭제 성공";
	
	public static final String CREATE_TIME = "타임 생성";
	public static final String DELETE_TIME = "타임 삭제";
	
	public static final String LOGIN_SUCCESS = "로그인 성공";
	public static final String LOGIN_FAIL = "로그인 실패";
	public static final String READ_USER = "회원 정보 조회 성공";
	public static final String NOT_FOUND_USER = "회원을 찾을 수 없습니다.";
	public static final String UPDATE_USER = "회원 정보 수정 성공";
	public static final String INTERNAL_SERVER_ERROR = "서버 내부 에러";
	public static final String DB_ERROR = "데이터베이스 에러";
	
}
