package com.app.model;

import java.util.List;

import com.app.model.entitymodel.MainUser;

public class UserView {
	List<MainUser> listUser;
	List<Integer> listPage;

	public UserView(List<MainUser> listUser, List<Integer> listPage) {
		this.listUser = listUser;
		this.listPage = listPage;
	}

	public List<MainUser> getListUser() {
		return listUser;
	}

	public void setListUser(List<MainUser> listUser) {
		this.listUser = listUser;
	}

	public List<Integer> getListPage() {
		return listPage;
	}

	public void setListPage(List<Integer> listPage) {
		this.listPage = listPage;
	}
}
