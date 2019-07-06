package com.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dao.NetwordDao;
import com.app.model.entitymodel.Netword;
import com.app.service.NetwordService;

@Service
@Transactional
public class NetworServiceImpl implements NetwordService {
	@Autowired
	private NetwordDao networdDao;

	public void add(Netword n) {
		networdDao.add(n);
	}

	public void update(Netword n) {
		networdDao.update(n);
	}

	public void delete(Byte id) {
		networdDao.delete(id);
	}

	public Netword getById(Byte id) {
		return networdDao.getById(id);
	}

	public List<Netword> getAll() {
		return networdDao.getAll();
	}

}
