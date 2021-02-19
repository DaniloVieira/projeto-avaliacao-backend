package com.zalpi.avaliacaobackend.rest.endpoint;

import java.time.LocalDateTime;

import com.zalpi.avaliacaobackend.dto.ActivityDTO;
import com.zalpi.avaliacaobackend.dto.filter.ActivityFilterDTO;
import com.zalpi.avaliacaobackend.dto.response.ResponseObject;
import com.zalpi.avaliacaobackend.service.ActivityService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import static com.zalpi.avaliacaobackend.util.misc.ResponseUtil.createResponse;

@RestController
@ResponseBody
@RequestMapping(value = "/activities/", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class ActivityEndPoint {

	@NonNull
	private ActivityService activityService;

	@PostMapping("save")
	public ResponseEntity<ResponseObject> saveActivity(@RequestBody ActivityDTO activity) {
		return createResponse(activityService.saveActivity(activity));
	}

	@GetMapping("list")
	public ResponseEntity<ResponseObject> listByFilters(
		@RequestParam(required = false) Long projectId,
		@RequestParam(required = false) Long contributorId,
		@RequestParam(required = false) String description,
		@RequestParam(required = false) LocalDateTime dtInitialStart,
		@RequestParam(required = false) LocalDateTime dtInitialEnd,
		@RequestParam(required = false) LocalDateTime dtFinalStart,
		@RequestParam(required = false) LocalDateTime dtFinalEnd,
		@RequestParam Integer page,
		@RequestParam Integer pageSize) {
		ActivityFilterDTO filter = new ActivityFilterDTO();
		filter.setProjectId(projectId);
		filter.setContributorId(contributorId);
		filter.setDescription(description);
		filter.setDtInitialStart(dtInitialStart);
		filter.setDtInitialEnd(dtInitialEnd);
		filter.setDtFinalStart(dtFinalStart);
		filter.setDtFinalEnd(dtFinalEnd);
		filter.setPage(page);
		filter.setPageSize(pageSize);
		return createResponse(activityService.listByFilters(filter));
	}

}
