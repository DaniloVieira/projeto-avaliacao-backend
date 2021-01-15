package com.zalpi.avaliacaobackend.dto.response;

import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PagedResponseObject<T extends Collection> extends ResponseObject<T> {

	//private T value;
	private Integer currentPage;
	private Integer pageSize;
	private Integer totalSize;

	public PagedResponseObject(T value) {
		super();
		super.value = value;
	}

	public PagedResponseObject<T> currentPage(Integer currentPage) {
		this.currentPage = currentPage;
		return this;
	}

	public PagedResponseObject<T> pageSize(Integer pageSize) {
		this.pageSize = pageSize;
		return this;
	}

	public PagedResponseObject<T> totalSize(Integer totalSize) {
		this.totalSize = totalSize;
		return this;
	}

	public Boolean getHasPrevious() {
		if (currentPage == null)
			return false;
		return currentPage.intValue() > 1;
	}

	public Boolean getHasNext() {
		if (currentPage == null || pageSize == null || totalSize == null)
			return false;
		return currentPage.intValue() * pageSize.intValue() < totalSize.intValue();
	}

//	public String getType() {
//		return value != null ? "_" + value.getClass().getSimpleName().toLowerCase() : null;
//	}

}
