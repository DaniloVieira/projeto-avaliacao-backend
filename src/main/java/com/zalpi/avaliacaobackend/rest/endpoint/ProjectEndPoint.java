package com.zalpi.avaliacaobackend.rest.endpoint;

import java.util.Set;

import com.zalpi.avaliacaobackend.dto.filter.ProjectFilterDTO;
import com.zalpi.avaliacaobackend.dto.response.ResponseObject;
import com.zalpi.avaliacaobackend.service.ProjectService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
		//return createResponse(projectService.listByFilters(filter));
		return createResponse(projectService.listDTOByFilters(filter));
	}

	@GetMapping("domain/{contributorId}")
	public ResponseEntity<Set> listDomain(@PathVariable Long contributorId){
		return new ResponseEntity(projectService.listDomain(contributorId), HttpStatus.OK);
	}
}
