package com.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dao.UserDao;
import com.app.model.entitymodel.MainUser;
import com.app.service.UserService;

@Transactional
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	public void add(MainUser user) {
		userDao.add(user);
	}

	public void update(MainUser user) {
		userDao.update(user);
	}

	public void delete(String username) {
		userDao.delete(username);
	}

	public List<MainUser> getAll() {
		return userDao.getAll();
	}

	public MainUser getByUsername(String username) {
		return userDao.getByUsername(username);
	}

	public int countAll() {
		return userDao.countAll();
	}

	public int usernameIsExist(String username) {
		return userDao.usernameIsExist(username);
	}

	public int emailIsExist(String email) {
		return userDao.emailIsExist(email);
	}

	public List<MainUser> findUsers(String keyword, int page, int size) {
		return userDao.findUsers(keyword, page, size);
	}

	public int countUsers(String keyword) {
		if (keyword == null || keyword.length() == 0) {
			return countAll();
		}
		return userDao.countUsers(keyword);
	}

	public List<MainUser> getAll(int page, int size) {
		return userDao.getAll(page, size);
	}

}
