package com.app.dao;

import java.util.List;

import com.app.model.entitymodel.SimType;

public interface SimTypeDao {
	
	public void add(SimType n);

	public void update(SimType n);

	public void delete(short id);

	public SimType getById(short id);

	public List<SimType> getAll();
}
