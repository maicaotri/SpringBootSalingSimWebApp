package com.app.service;

import java.util.List;

import com.app.model.entitymodel.MainUser;

public interface UserService {

	public void add(MainUser user);

	public void update(MainUser user);

	public void delete(String username);

	public List<MainUser> getAll();
	
	public List<MainUser> getAll(int page, int size);
	
	public MainUser getByUsername(String username);
	
	public int countAll();
	
	public int usernameIsExist(String username);
	
	public int emailIsExist(String email);
	
	public List<MainUser> findUsers(String keyword, int page, int size);
	
	public int countUsers(String keyword);
}
