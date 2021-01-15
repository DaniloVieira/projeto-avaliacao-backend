package com.zalpi.avaliacaobackend.rest.endpoint;

import com.zalpi.avaliacaobackend.dto.response.ProjectFilterDTO;
import com.zalpi.avaliacaobackend.dto.response.ResponseObject;
import com.zalpi.avaliacaobackend.model.Project;
import com.zalpi.avaliacaobackend.service.ProjectService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import static com.zalpi.avaliacaobackend.util.misc.ResponseUtil.createResponse;

@RestController
@ResponseBody
@RequestMapping(value = "/project/", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class ProjectEndPoint {

	@NonNull
	private ProjectService projectService;

	@GetMapping("list")
	public ResponseEntity<ResponseObject> listByFilters(@RequestBody ProjectFilterDTO filter){
		return createResponse(projectService.listByFilters(filter));
	}
}
