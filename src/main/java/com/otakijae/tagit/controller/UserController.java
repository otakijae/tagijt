package com.otakijae.tagit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.otakijae.tagit.model.vo.ContentVo;
import com.otakijae.tagit.service.UserService;

// @Controller 어노테이션을 이용하면 기본적으로 view 페이지를 찾아서 띄어주는 역할을 한다.
// 하지만 REST API를 개발해야하는 상황이라면 각 메소드마다 @ResponseBody를 붙여서 데이터를 그대로 반환하도록 할 수 있다.

// @RestController를 사용하면, @ResponseBody 어노테이션을 붙이지 않아도 된다.
// 메소드가 여러개인 경우 @Controller와 @ResponseBody를 이용할때보다 훨씬 간편

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("")
	public List<ContentVo.User> getAllUsers() {
		return userService.getAllUsers();
	}

	@GetMapping("/{userid}")
	public ContentVo.User getUserByUserId(@PathVariable String userid) {
		return userService.getUserByUserId(userid);
	}

	@PostMapping("")
	public ContentVo.User registerUser(@RequestBody ContentVo.User user) {
		return userService.registerUser(user);
	}

	@PutMapping("/{userid}")
	public void modifyUser(@PathVariable String userid, @RequestBody ContentVo.User user) {

		userService.modifyUser(userid, user);
	}

	@DeleteMapping("/{userid}")
	public void removeUser(@PathVariable String userid) {
		userService.removeUser(userid);
	}

}
