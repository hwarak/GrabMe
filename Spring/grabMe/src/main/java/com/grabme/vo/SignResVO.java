package com.grabme.vo;

import lombok.Data;

@Data
public class SignResVO {

	private String result;
	private String code;
	
    private SignResVO() {}

    private static class SignResVOHolder {
        public static final SignResVO INSTANCE = new SignResVO();
    }

    public static SignResVO getSignResVOObject() {
        return SignResVOHolder.INSTANCE;
    }
	
}
