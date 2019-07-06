package com.app.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.app.dao.SimTypeDao;
import com.app.model.entitymodel.SimType;

@Repository
@Transactional
public class SimTypeDaoImpl implements SimTypeDao {
	@Autowired
	private SessionFactory sessionFactory;

	public void add(SimType n) {
		sessionFactory.getCurrentSession().save(n);
	}

	public void update(SimType n) {
		sessionFactory.getCurrentSession().merge(n);
	}

	public void delete(short id) {
		sessionFactory.getCurrentSession().delete(getById(id));
	}

	public SimType getById(short id) {
		return (SimType) sessionFactory.getCurrentSession().get(SimType.class, id);
	}

	public List<SimType> getAll() {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(SimType.class);
		return criteria.list();
	}

}
