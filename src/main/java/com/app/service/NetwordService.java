package com.app.service;

import java.util.List;

import com.app.model.entitymodel.Netword;

public interface NetwordService {

	public void add(Netword n);

	public void update(Netword n);

	public void delete(Byte id);

	public Netword getById(Byte id);

	public List<Netword> getAll();
}
