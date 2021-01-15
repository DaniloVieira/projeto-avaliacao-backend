package com.zalpi.avaliacaobackend.service.impl;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import com.zalpi.avaliacaobackend.constant.ResponseHttpType;
import com.zalpi.avaliacaobackend.dao.UserDao;
import com.zalpi.avaliacaobackend.dto.response.ResponseObject;
import com.zalpi.avaliacaobackend.model.Project;
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
		//User newUser = createUser(user);
		try {
//			user.setId(userDao.save(newUser).getId());;
//			return createResponse(user, SUCCESS_MESSAGE, null);
			return createResponse(userDao.save(user), SUCCESS_MESSAGE, null);
		} catch (Exception e) {
			e.printStackTrace();
			return createResponse(user, ERROR_MESSAGE, null, e, ResponseHttpType.BAD_REQUEST);
		}
	}

	//TODO move to a util class
//	private User createUser(UserDTO dto) {
//		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//		User user = new User();
//		user.setUserName(dto.getUserName());
//		user.setPassword(encoder.encode(dto.getPassword()));
//		user.setFirstName(dto.getFirstName());
//		user.setLastName(dto.getLastName());
//		user.setRoles(String.join(",", dto.getRoles()));
//		user.setDtcreation(LocalDateTime.now());
//		return user;
//	}

	//TODO move to a util class
//	private UserDTO createUserDTO(User user) {
//		UserDTO userDTO = new UserDTO();
//		userDTO.setId(user.getId());
//		userDTO.setUserName(user.getUserName());
//		userDTO.setPassword(user.getPassword());
//		userDTO.setFirstName(user.getFirstName());
//		userDTO.setLastName(user.getLastName());
//		userDTO.setRoles(user.getRolesList());
//		userDTO.setDtcreation(LocalDateTime.now());
//		userDTO.addAllProjects(createProjectsDtoResultList(user.getProjects()));
//		return userDTO;
//	}

//	private Set<ProjectDTO> createProjectsDtoResultList(Set<Project> projects) {
//		return projects.stream()
//			.map(p -> this.createProjectDTO(p))
//			.collect(Collectors.toSet());
//	}

//	private ProjectDTO createProjectDTO(Project project) {
//		ProjectDTO dto = new ProjectDTO();
//		dto.setId(project.getId());
//		dto.setClientName(project.getClientName());
//		dto.setDescription(project.getDescription());
//		dto.setDtstart(project.getDtstart());
//		dto.setDtcreation(project.getDtcreation());
//		dto.setDtExpectedCompletion(project.getDtExpectedCompletion());
//		dto.setDtRealCompletion(project.getDtRealCompletion());
//		//dto.setContributors(users);
//		return dto;
//	}

//	private List<UserDTO> createUserDtoResultList(List<User> users) {
//		return users.stream()
//			.map(u -> this.createUserDTO(u))
//			.collect(Collectors.toList());
//	}

	@Override
	public ResponseObject<List<User>> listAll() {
		try {
			//List<UserDTO> resultList = createUserDtoResultList(userDao.findAll());
			//return createResponse(Collections.singleton(resultList), SUCCESS_MESSAGE, 10, 999, 2, null);
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
