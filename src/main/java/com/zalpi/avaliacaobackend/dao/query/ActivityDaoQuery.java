package com.zalpi.avaliacaobackend.dao.query;

import java.util.List;

import com.zalpi.avaliacaobackend.dto.filter.ActivityFilterDTO;
import com.zalpi.avaliacaobackend.model.Activity;

public interface ActivityDaoQuery {
	Integer totalRecords(ActivityFilterDTO filter);
	List<Activity> listByFilters(ActivityFilterDTO filter);
}
