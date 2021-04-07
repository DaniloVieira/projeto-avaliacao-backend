package com.zalpi.avaliacaobackend.service;

import java.util.List;

import com.zalpi.avaliacaobackend.dto.response.ResponseObject;

import com.zalpi.avaliacaobackend.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService {

	ResponseObject<User> saveUser(User user);

	ResponseObject<List<User>> listAll();

	ResponseObject<User> getUserById(Long id);

    User findByUsernanme(String username);
}
