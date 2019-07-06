package com.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dao.SimDao;
import com.app.model.entitymodel.Sim;
import com.app.service.SimService;

@Service
@Transactional
public class SimServiceImpl implements SimService {
	@Autowired
	private SimDao simDao;

	public void add(Sim n) {
		simDao.add(n);
	}

	public void update(Sim n) {
		simDao.update(n);
	}

	public void delete(int id) {
		simDao.delete(id);
	}

	public Sim getById(int id) {
		return simDao.getById(id);
	}

	public List<Sim> getAll() {
		return simDao.getAll();
	}

	public List<Sim> findByNetword(int networkId, int page, int size) {
		return simDao.findByNetword(networkId, page, size);
	}

	public List<Sim> findByScore(int score, int page, int size) {
		return simDao.findByScore(score, page, size);
	}

	public List<Sim> findByTotalNumbers(int totalNumbers, int page, int size) {
		return simDao.findByTotalNumbers(totalNumbers, page, size);
	}

	public List<Sim> findByPhoneStart(String number, int page, int size) {
		return simDao.findByPhoneStart(number, page, size);
	}

	public List<Sim> findByPhoneEnd(String number, int page, int size) {
		return simDao.findByPhoneEnd(number, page, size);
	}

	public List<Sim> findByPhoneInside(String number, int page, int size) {
		return simDao.findByPhoneInside(number, page, size);
	}

	public List<Sim> findSim(Integer networkId, double priceFrom, double priceTo, Integer score,
			Integer totalNumbers, String number, List<Integer> notContainNumbers, int page, int size, Integer enabled, Integer sold, Integer simType) {
		return simDao.findSim(networkId, priceFrom, priceTo, score, totalNumbers, number, notContainNumbers, page, size, enabled, sold, simType);
	}

	public int countAll(Integer networkId, double priceFrom, double priceTo, Integer score, Integer totalNumbers,
			String number, List<Integer> notContainNumbers, Integer simType) {
		return simDao.countAll(networkId, priceFrom, priceTo, score, totalNumbers, number, notContainNumbers, simType);
	}

	public boolean simIsExist(String simNumber) {
		return simDao.simIsExist(simNumber);
	}

}
