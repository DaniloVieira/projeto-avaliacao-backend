package com.zalpi.avaliacaobackend.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseHttpType {

	INTERNAL_SERVER_ERROR(500),
	BAD_REQUEST(400),
	OK(200);

	private int code;

}
