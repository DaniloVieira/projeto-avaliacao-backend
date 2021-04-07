package com.zalpi.avaliacaobackend.rest.endpoint;

import java.time.LocalDateTime;

import com.zalpi.avaliacaobackend.dto.response.ResponseObject;
import com.zalpi.avaliacaobackend.model.User;
import com.zalpi.avaliacaobackend.service.UserService;
import com.zalpi.avaliacaobackend.util.misc.ServiceUtils;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;

import static com.zalpi.avaliacaobackend.constant.ResponseMessage.*;
import static com.zalpi.avaliacaobackend.util.misc.ResponseUtil.createResponse;

@RestController
@ResponseBody
@RequestMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class UserEndpoint {

	@NonNull
	private UserService userService;

	@PostMapping("/save")
	public ResponseEntity<ResponseObject>save (@RequestBody User user){
		return createResponse(userService.saveUser(user));
	}

	@GetMapping("/list-all")
	public ResponseEntity<ResponseObject> listAll (){
		return createResponse(userService.listAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<ResponseObject> getById (@PathVariable Long id) {
		return createResponse(userService.getUserById(id));
	}

	@GetMapping("/hello")
	public ResponseEntity<ResponseObject>hello (){
		return createResponse(ServiceUtils.createResponse("You have the authorization", SUCCESS_MESSAGE, null));
	}

	@GetMapping("/hello-unauthorized")
	public ResponseEntity<ResponseObject>helloAuthorized (){
		return createResponse(ServiceUtils.createResponse("You don't need authorization", SUCCESS_MESSAGE, null));
	}

	@RolesAllowed("ROLE_ADMIN")
	@GetMapping("/hello-role-admin")
	public ResponseEntity<ResponseObject>helloRoleAdmin (){
		return createResponse(ServiceUtils.createResponse("You need role admin", SUCCESS_MESSAGE, null));
	}

}
