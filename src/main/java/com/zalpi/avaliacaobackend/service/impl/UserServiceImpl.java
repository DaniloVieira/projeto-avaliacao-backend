package com.zalpi.avaliacaobackend.service.impl;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.zalpi.avaliacaobackend.constant.ResponseHttpType;
import com.zalpi.avaliacaobackend.dao.UserDao;
import com.zalpi.avaliacaobackend.dto.ResponseObject;
import com.zalpi.avaliacaobackend.model.User;
import com.zalpi.avaliacaobackend.service.UserService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
			user.setPassword(encryptPassword(user));
			return createResponse(userDao.save(user), SUCCESS_MESSAGE, null);
		} catch (Exception e) {
			e.printStackTrace();
			return createResponse(user, ERROR_MESSAGE, null, e, ResponseHttpType.BAD_REQUEST);
		}
	}

	private String encryptPassword(User user) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.encode(user.getPassword());
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

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		User user = userDao.findByUsername(userName);
		//TODO implemento i18n
		Optional.ofNullable(user).orElseThrow(() -> new UsernameNotFoundException("No user found with username: " + userName));
		return createUserDetails(user, true, true, true, true);
	}

	private UserDetails createUserDetails(User user, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked) {
		return new org.springframework.security.core.userdetails.User(
			user.getUserName(),
			user.getPassword(),
			enabled,
			accountNonExpired,
			credentialsNonExpired,
			accountNonLocked,
			getAuthorities(user.getRolesList())
		);
	}

	private List<GrantedAuthority> getAuthorities (List<String> roles) {
		return AuthorityUtils.createAuthorityList(roles.toArray(String[]::new));
	}
}
