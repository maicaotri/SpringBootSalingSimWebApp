package com.app.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.app.dao.BillDao;
import com.app.model.entitymodel.Bill;

@Repository
@Transactional
public class BillDaoImpl implements BillDao{
	@Autowired
	private SessionFactory sessionFactory;
	private Session session;

	public void add(Bill n) {
		sessionFactory.getCurrentSession().save(n);
	}

	public void update(Bill n) {
		sessionFactory.getCurrentSession().merge(n);
	}

	public void delete(int id) {
		sessionFactory.getCurrentSession().delete(getById(id));
	}

	public Bill getById(int id) {
		return (Bill) sessionFactory.getCurrentSession().get(Bill.class, id);
	}

	public List<Bill> getAll() {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Bill.class);
		return criteria.list();
	}

	public int countAll() {
		session = sessionFactory.getCurrentSession();
		String sql = "SELECT COUNT(*) FROM bill";
		SQLQuery query = session.createSQLQuery(sql);
		return Integer.parseInt(query.list().get(0).toString());
	}
}
