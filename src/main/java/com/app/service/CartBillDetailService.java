package com.app.service;

import java.util.List;

import com.app.model.entitymodel.CartBillDetail;

public interface CartBillDetailService {
	
	public void add(CartBillDetail n);

	public void update(CartBillDetail n);

	public void delete(int id);

	public CartBillDetail getById(int id);

	public List<CartBillDetail> getAll();
	
	public List<CartBillDetail> find(String username, int page, int size);
	
	public List<CartBillDetail> find(String username, String status, int page, int size);
	
	public List<CartBillDetail> findBillByUsername(String username, int page, int size);
	
	public List<CartBillDetail> findBillByUsernameAndListSimId(String username,List<Integer> listSimId, int page, int size);

	public boolean payByUsernameAndListId(String username,List<Integer> listCartBillDetailId);
	
	public boolean isExist(String username, int simId);
}
