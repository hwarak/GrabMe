# GrabMe

![Alt text](/etc/images/GrabMeNew.png)

## 프로젝트 개요
```
그랩미는 사용자가 예약하고싶은 카테고리를 선택해서 주변에 있는 장소를 쉽고 빠르게 예약을 할수 있습니다.
사장님과 사용자간에 원활한 예약을 도와주는 어플입니다.   
```
## 프로젝트 추진 배경 및 필요성

* 코로나19 장기화로 인한 여가생활을 즐기는 사람들이 증가함에 따라 취미생활을 한곳에서 예약하고 관리하면 편하지 않을까하는 아이디어에서 영감을 얻어 Grab me 가 탄생하게 되었습니다. 필라테스나 기존 헬스어플, 카페, 독서실 등 많은곳에선 이미 예약 시스템이 도입 되어있지만 각자 다른 서비스를 이용해 예약을 해야한다는 단점이 있습니다.

* 그랩미는 사용자의 취향에 맞게 카테고리를 선택하면 현재 위치와 가장 가까운 장소부터 순차적으로 예약이 가능한 비지니스들을 보여줍니다. 원하는 비지니스를 선택한 후 원하는 날짜와 시간을 선택해 예약을 완료합니다.

* 완료된 예약 비지니스들은 마이페이지에서 확인할수 있으며 예약취소 및 위치 확인이 가능합니다.

* 그랩미에 서비스를 등록하고싶으면 사장님으로 가입한 후 비지니스 정보를 등록하고 예약을 받고싶은 날짜와 시간을 입력하면 사용자들의 예약을 받을 준비가 완료됩니다. 


## APIs
### Sign API
* **POST** /sign/check

  - request
   ```json
    {
      "userStatus": 1,
      "userPhone": "01012341234"
    }
    ```
  - response 1 : 데이터베이스에 없는 경우 -> 회원가입
   ```json
    {
        "statusCode": 200,
        "responseMessage": "인증번호 전송 성공",
        "data": {
            "result": "ok",
            "code": "2631"
        }
    }
    ```
  - response 2 : 데이터베이스에 있는 경우 -> 로그인
   ```json
    {
        "statusCode": 200,
        "responseMessage": "인증번호 전송 성공",
        "data": {
            "result": "no",
            "code": "8868"
        }
    }
    
* **POST** /sign/up

  - request
   ```json
    {
        "userStatus":1,
        "userPhone":"01012341234",
        "userName":"서민희"
    }
    ```
  - response
   ```json
    {
        "statusCode": 201,
        "responseMessage": "계정 생성 성공",
        "data": {
            "result": "ok",
            "code": ""
        }
    }
    ```
### Category API
* **GET** /category

  - request
   ```json
    http://localhost:8080/web/category?shopLon=126.936897574428&shopLat=37.555202179427276&categoryIdx=3&startNum=0
    ```
    
  - response 1 : 보내줄 데이터 있음 (코드 중략)
   ```json
    {
        "statusCode": 200,
        "responseMessage": "리스트 전송 성공",
        "data": [
            {
                "reservationIdx": 0,
                "shopIdx": 61,
                "shopThumbnail": "https://grabmebucket.s3.ap-northeast-2.amazonaws.com/61.jfif",
                "shopTitle": "투썸플레이스 신촌점",
                "shopAddress": "서울 서대문구 신촌로 93",
                "shopPhone": "02-3142-5995",
                "timeDate": null,
                "timeAvailable": null
            },
            {
                "reservationIdx": 0,
                "shopIdx": 71,
                "shopThumbnail": "https://grabmebucket.s3.ap-northeast-2.amazonaws.com/71.jfif",
                "shopTitle": "타르타르 신촌점",
                "shopAddress": "서울 서대문구 연세로2길 3",
                "shopPhone": "1800-1778",
                "timeDate": null,
                "timeAvailable": null
            },
            {
                "reservationIdx": 0,
                "shopIdx": 53,
                "shopThumbnail": "https://grabmebucket.s3.ap-northeast-2.amazonaws.com/53.jfif",
                "shopTitle": "레드버튼 신촌점",
                "shopAddress": "서울 서대문구 연세로 8",
                "shopPhone": "02-363-3799",
                "timeDate": null,
                "timeAvailable": null
            }
        ]
    }
    ```
   - response 2 : 보내줄 데이터 없음
    ```json
    {
        "statusCode": 204,
        "responseMessage": "보내줄 데이터 없음",
        "data": []
    }
    ```   

## GrabMe Demo
* SMS 인증을 통한 회원가입 및 로그인   
[CoolSMS](https://coolsms.co.kr/) : 문자 인증을 위한 API   
[atalk](http://atalk.co.kr/) : 대표번호 생성을 위한 사이트


![Alt text](/etc/gif/KakaoTalk_20210417_194135445_06.gif)![Alt text](/etc/gif/KakaoTalk_20210417_194135445.gif)

* 메인페이지
```
사용자의 취향에 맞게 카테고리를 선택하면 현재 위치와 가장 가까운 장소부터 순차적으로 예약이 가능한 비지니스들을 보여줍니다.   
원하는 비지니스를 선택한 후 원하는 날짜와 시간을 선택해 예약을 완료합니다.
```
![Alt text](/etc/gif/KakaoTalk_20210417_194135445_02.gif)![Alt text](/etc/gif/KakaoTalk_20210417_194135445_03.gif)

* 예약페이지 & 마이페이지
```
예약페이지 : 해당 비즈니스의 정보를 보여주고 사용자가 원하는 날짜와 시간대를 선택하여 예약을 할 수 있다.
마이페이지 : 프로필 수정 및 예약 목록을 볼 수 있다.
```
![Alt text](/etc/gif/KakaoTalk_20210417_194135445_04.gif)![Alt text](/etc/gif/KakaoTalk_20210417_194135445_05.gif)
