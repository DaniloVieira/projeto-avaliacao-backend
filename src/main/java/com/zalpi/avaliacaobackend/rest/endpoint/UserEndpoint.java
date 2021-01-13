package com.zalpi.avaliacaobackend.rest.endpoint;

import java.time.LocalDateTime;

import com.zalpi.avaliacaobackend.dto.ResponseObject;
import com.zalpi.avaliacaobackend.model.User;
import com.zalpi.avaliacaobackend.service.UserService;
import com.zalpi.avaliacaobackend.util.misc.ServiceUtils;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import static com.zalpi.avaliacaobackend.constant.ResponseMessage.*;
import static com.zalpi.avaliacaobackend.util.misc.ResponseUtil.createResponse;

@RestController
@ResponseBody
@RequestMapping(value = "/user/", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class UserEndpoint {

	@NonNull
	private UserService userService;

	@PostMapping("sign-in")
	public ResponseEntity<ResponseObject>signIn (){

		User helloUser = new User();

		helloUser.setFirstName("hello");
		helloUser.setLastName("world");
		helloUser.setPassword("lalalalalalal");
		helloUser.setDtcreation(LocalDateTime.now());
		return createResponse(ServiceUtils.createResponse(helloUser, SUCCESS_MESSAGE, null));

	}

	@PostMapping("save")
	public ResponseEntity<ResponseObject>save (@RequestBody User user){
		return createResponse(userService.saveUser(user));
	}

	@GetMapping("list-all")
	public ResponseEntity<ResponseObject> listAll (){
		return createResponse(userService.listAll());
	}

	@GetMapping("hello")
	public ResponseEntity<ResponseObject>hello (){
		return createResponse(ServiceUtils.createResponse("authorised", SUCCESS_MESSAGE, null));
	}

}
