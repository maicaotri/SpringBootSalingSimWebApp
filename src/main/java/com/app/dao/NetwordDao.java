package com.app.dao;

import java.util.List;

import com.app.model.entitymodel.Netword;

public interface NetwordDao {
	
	public void add(Netword n);

	public void update(Netword n);

	public void delete(Byte id);

	public Netword getById(Byte id);

	public List<Netword> getAll();
}
