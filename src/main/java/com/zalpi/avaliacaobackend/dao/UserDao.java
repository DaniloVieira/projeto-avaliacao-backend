package com.zalpi.avaliacaobackend.dao;

import com.zalpi.avaliacaobackend.dao.query.UserDaoQuery;
import com.zalpi.avaliacaobackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserDao extends JpaRepository<User, Long>, UserDaoQuery {

	@Query(value = "SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM User u WHERE u.userName = :userName")
	Boolean isUserNameExistis(@Param("userName") String userName);

	@Query(value = "SELECT u FROM User u WHERE u.userName = :userName")
	User findByUsername(@Param("userName") String userName);

}
