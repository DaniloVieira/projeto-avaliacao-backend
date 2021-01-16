package com.zalpi.avaliacaobackend.dao.query;

import java.util.List;

import com.zalpi.avaliacaobackend.dto.filter.ProjectFilterDTO;
import com.zalpi.avaliacaobackend.model.Project;

public interface ProjectDaoQuery {

	List<Project> listByFilters(ProjectFilterDTO filter);

	Integer totalRecords(ProjectFilterDTO filter);
}
