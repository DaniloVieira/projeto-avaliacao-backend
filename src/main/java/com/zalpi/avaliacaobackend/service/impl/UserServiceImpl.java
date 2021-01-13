package com.zalpi.avaliacaobackend.service.impl;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import com.zalpi.avaliacaobackend.constant.ResponseHttpType;
import com.zalpi.avaliacaobackend.dao.UserDao;
import com.zalpi.avaliacaobackend.dto.ResponseObject;
import com.zalpi.avaliacaobackend.model.User;
import com.zalpi.avaliacaobackend.service.UserService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import static com.zalpi.avaliacaobackend.constant.ResponseMessage.*;
import static com.zalpi.avaliacaobackend.util.misc.ServiceUtils.createResponse;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	@NonNull
	private UserDao userDao;

	@Override
	public ResponseObject<User> saveUser(User user) {
		try {
			user.setDtcreation(LocalDateTime.now());
			return createResponse(userDao.save(user), SUCCESS_MESSAGE, null);
		} catch (Exception e) {
			e.printStackTrace();
			return createResponse(user, ERROR_MESSAGE, null, e, ResponseHttpType.BAD_REQUEST);
		}
	}

	@Override
	public ResponseObject<List<User>> listAll() {
		try {
			return createResponse(Collections.singleton(userDao.findAll()), SUCCESS_MESSAGE, 10, 999, 2, null);
		} catch (Exception e) {
			e.printStackTrace();
			return createResponse(null, ERROR_MESSAGE, e);
		}
	}
}
