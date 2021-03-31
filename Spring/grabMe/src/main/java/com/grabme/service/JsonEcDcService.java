package com.grabme.service;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

@Service
public class JsonEcDcService {

<<<<<<< HEAD
	// json 파싱 후 JSONObject 형태로 반환해준다.
=======
	// json 파싱 후 JSONObject 형태로 반환해준다
>>>>>>> 60d5062df8d8312803ec6450f4f6bfac333d90c3
	public JSONObject jsonDc(String str) {

		JSONParser parser = new JSONParser();
		Object obj = null;

		try {
			obj = parser.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		JSONObject jsonObj = (JSONObject) obj;

		return jsonObj;
	}

}
