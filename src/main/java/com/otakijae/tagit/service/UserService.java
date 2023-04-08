package com.otakijae.tagit.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.otakijae.tagit.model.vo.ContentVo;
import com.otakijae.tagit.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Validated
public class UserService {
	@Autowired
	UserRepository userDao;

	public List<ContentVo.User> getAllUsers() {
		log.debug("[getAllUsers]: " + userDao.getAllUsers());
		return userDao.getAllUsers();
	}

	public ContentVo.User getUserByUserId(String userId) {
		log.debug("[getUserByUserId]: " + userDao.getUserByUserId(userId));
		return userDao.getUserByUserId(userId);
	}

	public ContentVo.User registerUser(@Valid ContentVo.User user) {
		log.debug("[registerUser]: " + userDao.insertUser(user));
		return userDao.insertUser(user);
	}

	public void modifyUser(String userId, ContentVo.User user) {
		log.debug("[modifyUser]");
		userDao.updateUser(userId, user);
	}

	public void removeUser(String userId) {
		log.debug("[removeUser]");
		userDao.deleteUser(userId);
	}

}
