package com.zalpi.avaliacaobackend.service;

import java.util.List;

import com.zalpi.avaliacaobackend.dto.ResponseObject;
import com.zalpi.avaliacaobackend.model.User;

public interface UserService {

	ResponseObject<User> saveUser(User user);

	ResponseObject<List<User>> listAll();
}
