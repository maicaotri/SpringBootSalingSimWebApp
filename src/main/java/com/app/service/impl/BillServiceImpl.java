package com.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dao.BillDao;
import com.app.model.entitymodel.Bill;
import com.app.service.BillService;

@Service
@Transactional
public class BillServiceImpl implements BillService {
	@Autowired
	private BillDao billDao;

	public void add(Bill n) {
		billDao.add(n);
	}

	public void update(Bill n) {
		billDao.update(n);
	}

	public void delete(int id) {
		billDao.delete(id);
	}

	public Bill getById(int id) {
		return billDao.getById(id);
	}

	public List<Bill> getAll() {
		return billDao.getAll();
	}

	public int countAll() {
		return billDao.countAll();
	}

}
