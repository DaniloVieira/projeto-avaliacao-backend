package com.zalpi.avaliacaobackend.service;

import java.util.List;
import java.util.Set;

import com.zalpi.avaliacaobackend.dto.ProjectDTO;
import com.zalpi.avaliacaobackend.dto.filter.ProjectFilterDTO;
import com.zalpi.avaliacaobackend.dto.response.ResponseObject;
import com.zalpi.avaliacaobackend.model.Project;

public interface ProjectService {

	ResponseObject<List<Project>> listByFilters(ProjectFilterDTO filter);

	ResponseObject<List<ProjectDTO>> listDTOByFilters(ProjectFilterDTO filter);

	Set listDomain(Long contributorId);
}
