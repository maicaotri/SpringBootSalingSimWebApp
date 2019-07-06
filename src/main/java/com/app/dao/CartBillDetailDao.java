package com.app.dao;

import java.util.List;

import com.app.model.entitymodel.CartBillDetail;

public interface CartBillDetailDao {
	
	public void add(CartBillDetail n);

	public void update(CartBillDetail n);

	public void delete(int id);

	public CartBillDetail getById(int id);

	public List<CartBillDetail> getAll();

	public List<CartBillDetail> find(String username, int page, int size);
	
	public List<CartBillDetail> find(String username, String status, int page, int size);
	
	public List<CartBillDetail> findBillByUsername(String username, int page, int size);
	
	public List<CartBillDetail> findCartByUsernameAndListSimId(String username,List<Integer> listSimId, int page, int size);
	
	public boolean payByUsernameAndListId(String username,List<Integer> listId);
	
	public void setStatusOrdered(List<Integer> listId, Integer billId, String username);
	
	public List<CartBillDetail> getByUsernameListId(String username, List<Integer> listId);
	
	public boolean isExist(String username, int simId);
}
