package com.app.dao;

import java.util.List;

import com.app.model.entitymodel.MainUser;

public interface UserDao {
	
	public void add(MainUser user);

	public void update(MainUser user);

	public void delete(String username);

	public MainUser getByUsername(String username);

	public List<MainUser> getAll();
	
	public int countAll();
	
	public int usernameIsExist(String username);
	
	public int emailIsExist(String email);
	
	public List<MainUser> findUsers(String keyword, int page, int size);
	
	public int countUsers(String keyword);
	
	public List<MainUser> getAll(int page, int size);
}