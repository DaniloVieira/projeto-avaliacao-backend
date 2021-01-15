package com.zalpi.avaliacaobackend.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.zalpi.avaliacaobackend.dao.query.ProjectDaoQuery;
import com.zalpi.avaliacaobackend.dto.response.ProjectFilterDTO;
import com.zalpi.avaliacaobackend.model.Project;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
@Transactional
public class ProjectDAOImpl implements ProjectDaoQuery {

	@NonNull
	private EntityManager entityManager;

	@Override
	public List<Project> listByFilters(ProjectFilterDTO filter) {
		Query query = getCreateQuery(filter, "p", "");
		query.setFirstResult((filter.getPage() - 1) * filter.getPageSize());
		query.setMaxResults(filter.getPageSize());
		return query.getResultList();
	}

	private Query getCreateQuery(ProjectFilterDTO filter, String select, String lastStmt) {
		Query query = entityManager.createQuery(
			"SELECT " +select+ " FROM Project p JOIN p.contributors c "
			+ "WHERE (p.description LIKE '%'||:description||'%' OR :description IS NULL) "
			+ "AND (p.clientName LIKE '%'||:clientName||'%' OR :clientName IS NULL)"
			+ "AND (c.firstName LIKE '%'||:contributorName||'%' OR :contributorName IS NULL)"
			//+ "AND (c.id in :contributorsIds OR :contributorsIds is null)"
			+ "AND (p.dtCreation >= :dtInitialCreation OR :dtInitialCreation IS NULL)"
			+ "AND (p.dtCreation <= :dtFinalCreation OR :dtFinalCreation IS NULL)"
			+ "AND (p.dtStart >= :dtInitialStart OR :dtInitialStart IS NULL)"
			+ "AND (p.dtStart <= :dtFinalStart OR :dtFinalStart IS NULL)"
			+ "AND (p.dtRealCompletion >= :dtInitialRealCompletion OR :dtInitialRealCompletion IS NULL)"
			+ "AND (p.dtRealCompletion <= :dtFinalRealCompletion OR :dtFinalRealCompletion IS NULL) "
			+ lastStmt);
		query.setParameter("description", filter.getDescription());
		query.setParameter("clientName", filter.getClientName());
		query.setParameter("contributorName", filter.getContributorName());
		query.setParameter("dtInitialCreation", filter.getDtInitialRealCompletion());
		query.setParameter("dtFinalCreation", filter.getDtFinalRealCompletion());
		query.setParameter("dtInitialStart", filter.getDtInitialStart());
		query.setParameter("dtFinalStart", filter.getDtFinalStart());
		query.setParameter("dtInitialRealCompletion", filter.getDtInitialRealCompletion());
		query.setParameter("dtFinalRealCompletion", filter.getDtFinalRealCompletion());
		return query;
	}

	@Override
	public Integer totalRecords(ProjectFilterDTO filter) {
		String select = "count(p)";
		Query query = getCreateQuery(filter, "count(p)", "GROUP BY p");
		return ((Long) query.getSingleResult()).intValue();
	}
}
