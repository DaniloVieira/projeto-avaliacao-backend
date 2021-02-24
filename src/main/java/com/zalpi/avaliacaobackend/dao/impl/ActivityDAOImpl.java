package com.zalpi.avaliacaobackend.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.zalpi.avaliacaobackend.dao.query.ActivityDaoQuery;
import com.zalpi.avaliacaobackend.dto.filter.ActivityFilterDTO;
import com.zalpi.avaliacaobackend.model.Activity;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
@Transactional
public class ActivityDAOImpl implements ActivityDaoQuery {

	@NonNull
	private EntityManager entityManager;

	@Override public Integer totalRecords(ActivityFilterDTO filter) {
		//TODO refactor this, repeated code
		Query query = getCreateQuery(filter, "count(a)", "");
		return ((Long) query.getSingleResult()).intValue();
	}

	@Override
	public List<Activity> listByFilters(ActivityFilterDTO filter) {
		Query query = getCreateQuery(filter, "a", "");
		query.setFirstResult((filter.getPage() - 1) * filter.getPageSize());
		query.setMaxResults(filter.getPageSize());
		return query.getResultList();
	}

	private Query getCreateQuery(ActivityFilterDTO filter, String select, String lastStmt) {
		Query query = entityManager.createQuery(
			"SELECT " +select+ " FROM Activity a "
				+ "JOIN a.user c "
				+ "JOIN a.project p "
				+ "WHERE (UPPER(CONCAT(a.description, ' ', a.details)) LIKE UPPER('%'||:description||'%') OR :description IS NULL) "
				+ "AND (c.id = :contributorId OR :contributorId is null) "
				+ "AND (p.id = :projectId OR :projectId is null) "
				+ "AND (a.dtStart >= :dtInitialStart OR :dtInitialStart IS NULL) "
				+ "AND (a.dtStart <= :dtFinalStart OR :dtFinalStart IS NULL) "
				+ "AND (a.dtEnd >= :dtInitialEnd OR :dtInitialEnd IS NULL) "
				+ "AND (a.dtEnd <= :dtFinalEnd OR :dtFinalEnd IS NULL) "
				+ lastStmt);
		query.setParameter("description", filter.getDescription());
		query.setParameter("contributorId", filter.getContributorId());
		query.setParameter("projectId", filter.getProjectId());
		query.setParameter("dtInitialStart", filter.getDtInitialStart());
		query.setParameter("dtFinalStart", filter.getDtFinalStart());
		query.setParameter("dtInitialEnd", filter.getDtInitialEnd());
		query.setParameter("dtFinalEnd", filter.getDtFinalEnd());
		return query;
	}
}
