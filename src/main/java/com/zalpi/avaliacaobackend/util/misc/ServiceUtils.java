package com.zalpi.avaliacaobackend.util.misc;

import java.util.Collection;
import java.util.Objects;

import com.zalpi.avaliacaobackend.constant.ResponseHttpType;
import com.zalpi.avaliacaobackend.constant.ResponseMessage;
import com.zalpi.avaliacaobackend.dto.response.PagedResponseObject;
import com.zalpi.avaliacaobackend.dto.response.ResponseObject;
import com.zalpi.avaliacaobackend.dto.response.SingleResponseObject;

public class ServiceUtils {

	//TODO add java doc
	public static ResponseObject createResponse(Object response, ResponseMessage responseMessage, String type, Exception e, ResponseHttpType responseHttpType) {
		return new SingleResponseObject(response)
			.responseHttpType(responseHttpType)
			.message(responseMessage.getMsg())
			.type(type)
//			.exception(e)
			.cause(getRootCauseMessage(e));
	}

	public static ResponseObject createResponse(Object response, ResponseMessage responseMessage, Exception e, ResponseHttpType responseHttpType) {
		return createResponse(response, responseMessage, null, e, responseHttpType);
	}

	public static ResponseObject createResponse(Object response, ResponseMessage responseMessage, String type, Exception e) {
		return createResponse(response, responseMessage, type, e, null);
	}

	public static ResponseObject createResponse(Object response, ResponseMessage responseMessage, Exception e) {
		return createResponse(response, responseMessage, null, e, null);
	}

	public static ResponseObject createResponse(Collection response, ResponseMessage responseMessage, String type, Integer pageSize, Integer totalSize, Integer currentPage, Exception e, ResponseHttpType responseHttpType) {
		return new PagedResponseObject(response)
			.currentPage(currentPage)
			.pageSize(pageSize)
			.totalSize(totalSize)
			.responseHttpType(responseHttpType)
			.message(responseMessage.getMsg())
			.type(type)
			//			.exception(e)
			.cause(getRootCauseMessage(e));
	}

	public static ResponseObject createResponse(Collection response, ResponseMessage responseMessage, Integer pageSize, Integer totalSize, Integer currentPage, Exception e, ResponseHttpType responseHttpType) {
		return createResponse(response, responseMessage, null, pageSize, totalSize, currentPage, e, responseHttpType);
	}

	public static ResponseObject createResponse(Collection response, ResponseMessage responseMessage, String type, Integer pageSize, Integer totalSize, Integer currentPage, Exception e) {
		return createResponse(response, responseMessage, type, pageSize, totalSize, currentPage, e, null);
	}

	public static ResponseObject createResponse(Collection response, ResponseMessage responseMessage, Integer pageSize, Integer totalSize, Integer currentPage, Exception e) {
		return createResponse(response, responseMessage, null, pageSize, totalSize, currentPage, e, null);
	}

	public static String getRootCauseMessage(Throwable throwable) {
		if(Objects.nonNull(throwable)){
			Throwable rootCause = throwable;
			while (rootCause.getCause() != null && rootCause.getCause() != rootCause) {
				rootCause = rootCause.getCause();
			}
			return rootCause.getMessage();
		}
		return null;
	}
}