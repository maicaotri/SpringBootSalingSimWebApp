package com.app.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.app.dao.NetwordDao;
import com.app.model.entitymodel.Netword;

@Repository
@Transactional
public class NetwordDaoImpl implements NetwordDao{
	@Autowired
	private SessionFactory sessionFactory;
	
	public void add(Netword n) {
		sessionFactory.getCurrentSession().save(n);		
	}

	public void update(Netword n) {
		sessionFactory.getCurrentSession().merge(n);
		
	}

	public void delete(Byte id) {
		sessionFactory.getCurrentSession().delete(getById(id));
		
	}

	public Netword getById(Byte id) {
		return (Netword) sessionFactory.getCurrentSession().get(Netword.class, id);
	}

	public List<Netword> getAll() {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Netword.class);
		return criteria.list();
	}

}
