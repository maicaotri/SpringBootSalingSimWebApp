package com.app.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.app.dao.UserDao;
import com.app.model.entitymodel.MainUser;

@Repository
@Transactional
public class UserDaoImpl implements UserDao {
	@Autowired
	private SessionFactory sessionFactory;
	private Session session;

	public void add(MainUser user) {
		sessionFactory.getCurrentSession().save(user);
	}

	public void update(MainUser user) {
		sessionFactory.getCurrentSession().merge(user);
	}

	public void delete(String username) {
		sessionFactory.getCurrentSession().delete(getByUsername(username));
	}

	public List<MainUser> getAll() {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(MainUser.class);
		return criteria.list();
	}

	public MainUser getByUsername(String username) {
		return (MainUser) sessionFactory.getCurrentSession().get(MainUser.class, username);
	}

	public int countAll() {
		session = sessionFactory.getCurrentSession();
		String sql = "SELECT COUNT(*) FROM mainuser";
		SQLQuery query = session.createSQLQuery(sql);
		return Integer.parseInt(query.list().get(0).toString());
	}

	public int usernameIsExist(String username) {
		session = sessionFactory.getCurrentSession();
		String sql = "SELECT COUNT(*) FROM mainuser WHERE username =:username";
		SQLQuery query = session.createSQLQuery(sql);
		query.setParameter("username", username);
		return Integer.parseInt(query.list().get(0).toString());
	}

	public int emailIsExist(String email) {
		session = sessionFactory.getCurrentSession();
		String sql = "SELECT COUNT(*) FROM mainuser WHERE email =:email";
		SQLQuery query = session.createSQLQuery(sql);
		query.setParameter("email", email);
		return Integer.parseInt(query.list().get(0).toString());
	}

	public List<MainUser> findUsers(String keyword, int page, int size) {
		session = sessionFactory.getCurrentSession();
		String sql = "SELECT * FROM mainuser WHERE (username LIKE :keyword) OR (email LIKE :keyword) OR (fName LIKE :keyword) OR (lName LIKE :keyword)";
		SQLQuery query = session.createSQLQuery(sql);
		query.addEntity(MainUser.class);
		query.setParameter("keyword", "%" + keyword + "%");
		query.setFirstResult((page - 1) * size);
		query.setMaxResults(size);
		List<MainUser> results = query.list();
		return results;
	}
	
	public int countUsers(String keyword) {
		session = sessionFactory.getCurrentSession();
		String sql = "SELECT count(*) FROM mainuser WHERE username LIKE :keyword OR email LIKE :keyword OR fName LIKE :keyword OR lName LIKE :keyword";
		SQLQuery query = session.createSQLQuery(sql);
		query.setParameter("keyword", "%" + keyword + "%");
		return Integer.parseInt(query.list().get(0).toString());
	}

	public List<MainUser> getAll(int page, int size) {
		session = sessionFactory.getCurrentSession();
		String sql = "SELECT * FROM mainuser";
		SQLQuery query = session.createSQLQuery(sql);
		query.addEntity(MainUser.class);
		query.setFirstResult((page - 1) * size);
		query.setMaxResults(size);
		List<MainUser> results = query.list();
		return results;
	}

}