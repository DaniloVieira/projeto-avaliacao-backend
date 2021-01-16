package com.zalpi.avaliacaobackend.dao;

import com.zalpi.avaliacaobackend.dao.query.ActivityDaoQuery;
import com.zalpi.avaliacaobackend.model.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityDAO extends JpaRepository<Activity, Long>, ActivityDaoQuery {
}
