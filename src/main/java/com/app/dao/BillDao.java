package com.app.dao;

import java.util.List;

import com.app.model.entitymodel.Bill;

public interface BillDao {
	
	public void add(Bill n);

	public void update(Bill n);

	public void delete(int id);

	public Bill getById(int id);

	public List<Bill> getAll();
	
	public int countAll();
}
