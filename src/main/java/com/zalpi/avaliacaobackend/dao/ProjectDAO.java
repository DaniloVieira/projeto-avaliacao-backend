package com.zalpi.avaliacaobackend.dao;

import com.zalpi.avaliacaobackend.dao.query.ProjectDaoQuery;
import com.zalpi.avaliacaobackend.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectDAO extends JpaRepository<Project, Long>, ProjectDaoQuery {
}
