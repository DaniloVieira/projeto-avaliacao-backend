package com.zalpi.avaliacaobackend.dao;

import java.util.Set;

import com.zalpi.avaliacaobackend.dao.query.ProjectDaoQuery;
import com.zalpi.avaliacaobackend.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProjectDAO extends JpaRepository<Project, Long>, ProjectDaoQuery {

	@Query("SELECT new Project(p.id, p.description)  FROM Project p join p.contributors u WHERE u.id = :id ORDER BY p.description")
	Set listByContributorId(@Param("id") Long contributorId);
}
