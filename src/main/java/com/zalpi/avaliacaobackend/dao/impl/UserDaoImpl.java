package com.zalpi.avaliacaobackend.dao.impl;

import javax.persistence.EntityManager;

import com.zalpi.avaliacaobackend.dao.query.UserDaoQuery;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
@Transactional
public class UserDaoImpl implements UserDaoQuery {

	@NonNull
	private EntityManager entityManager;


}
