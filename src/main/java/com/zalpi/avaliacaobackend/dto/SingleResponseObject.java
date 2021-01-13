package com.zalpi.avaliacaobackend.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SingleResponseObject<T> extends ResponseObject<T> {

	//private T value;

	public SingleResponseObject(T value) {
		super.value = value;
	}

//	public String getType() {
//		return value != null ? "_" + value.getClass().getSimpleName().toLowerCase() : null;
//	}

}
