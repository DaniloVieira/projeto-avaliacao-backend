package com.zalpi.avaliacaobackend.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.zalpi.avaliacaobackend.constant.ResponseHttpType;
import lombok.Getter;

@Getter
public abstract class ResponseObject<T> {

	protected T value;
	private String message;
	private String error;
	private Exception e;
	@JsonIgnore
	private ResponseHttpType responseHttpType;
	@JsonIgnore
	private String strType ;

	public ResponseObject<T> message(String message) {
		this.message = message;
		return this;
	}

	public ResponseObject<T> cause(String cause) {
		this.error = cause;
		return this;
	}

	public ResponseObject<T> exception(Exception e) {
		this.e = e;
		return this;
	}

	public ResponseObject<T> responseHttpType(ResponseHttpType responseHttpType) {
		this.responseHttpType = responseHttpType;
		return this;
	}

	public ResponseObject<T> type(String strType) {
		this.strType = strType;
		return this;
	}

	//@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public LocalDateTime getTimestamp (){
		return LocalDateTime.now();
	}

	@JsonInclude(JsonInclude.Include.NON_NULL)
	public Integer getStatus(){
		return this.responseHttpType == null ? null : responseHttpType.getCode();
	}

		public String getType(){
			String returnValue = null;
			if(strType != null){
				returnValue = "_"+this.strType;
			}else if (value != null ){
				returnValue = "_" + value.getClass().getSimpleName().toLowerCase();
			}
			return returnValue;
		}
}
