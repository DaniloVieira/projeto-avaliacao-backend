package com.zalpi.avaliacaobackend.util.misc;

import java.util.Objects;

import com.zalpi.avaliacaobackend.dto.ResponseObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseUtil {

	public static ResponseEntity<ResponseObject> createResponse(ResponseObject response) {

		HttpStatus httpStatus = getStatus(response);

		return new ResponseEntity<>(response, httpStatus);
	}

	public static HttpStatus getStatus(ResponseObject response) {
		if(Objects.nonNull(response.getResponseHttpType())){
			return HttpStatus.valueOf(response.getResponseHttpType().getCode());
		}
		return HttpStatus.OK;
	}
}