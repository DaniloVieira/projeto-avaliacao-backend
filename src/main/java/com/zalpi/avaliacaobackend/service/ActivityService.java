package com.zalpi.avaliacaobackend.service;

import java.util.List;

import com.zalpi.avaliacaobackend.dto.ActivityDTO;
import com.zalpi.avaliacaobackend.dto.filter.ActivityFilterDTO;
import com.zalpi.avaliacaobackend.dto.response.ResponseObject;
import com.zalpi.avaliacaobackend.model.Activity;

public interface ActivityService {
	ResponseObject<List<Activity>> listByFilters(ActivityFilterDTO filter);
	ResponseObject<Activity> saveActivity(ActivityDTO activityDTO);
	ResponseObject<ActivityDTO> getById(Long id);
	ResponseObject deleteById(Long id);
}
