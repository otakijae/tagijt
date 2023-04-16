package com.otakijae.tagit.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.otakijae.tagit.model.vo.ContentVo;

@Repository
public class UserRepository {
	public static List<ContentVo.User> users;

	static {
		users = new ArrayList<>();
		users.add(new ContentVo.User(1,"testName1","33836782", "1234"));
		users.add(new ContentVo.User(2,"testName2","33836783", "1234"));
		users.add(new ContentVo.User(3,"testName3","33836784", "1234"));
		users.add(new ContentVo.User(4,"testName4","33836785", "1234"));
		users.add(new ContentVo.User(5,"testName5","33836786", "1234"));
	}

	// Select all user.
	public List<ContentVo.User> getAllUsers() {
		return users;
	}

	// Select one user by userId
	public ContentVo.User getUserByUserId(String userId) {
		return users
			.stream()
			.filter(user -> user.getUserId().equals(userId))
			.findAny()
			.orElse(new ContentVo.User(-1, "", "", ""));
	}

	// Insert User
	public ContentVo.User insertUser(ContentVo.User user) {
		users.add(user);

		return user;
	}

	// Modify User
	public void updateUser(String userId, ContentVo.User user) {
		users.stream()
			.filter(curUser -> curUser.getUserId().equals(userId))
			.findAny()
			.orElse(new ContentVo.User(-1, "", "", ""))
			.setUserName(user.getUserName());
	}

	// Delete User
	public void deleteUser(String userId) {
		users.removeIf(user -> user.getUserId().equals(userId));
	}
}
