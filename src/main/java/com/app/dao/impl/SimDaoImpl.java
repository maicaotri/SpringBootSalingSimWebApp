package com.app.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.app.dao.SimDao;
import com.app.model.entitymodel.Sim;

@Repository
@Transactional
public class SimDaoImpl implements SimDao {
	@Autowired
	private SessionFactory sessionFactory;
	private Session session;

	public void add(Sim n) {
		sessionFactory.getCurrentSession().save(n);
	}

	public void update(Sim n) {
		sessionFactory.getCurrentSession().merge(n);

	}

	public void delete(int id) {
		sessionFactory.getCurrentSession().delete(getById(id));

	}

	public Sim getById(int id) {
		return (Sim) sessionFactory.getCurrentSession().get(Sim.class, id);
	}

	public List<Sim> getAll() {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Sim.class);
		return criteria.list();
	}

	public List<Sim> findByNetword(int networdId, int page, int size) {
		session = sessionFactory.getCurrentSession();
		String sql = "SELECT * FROM sim WHERE sold ='0' AND networdId = :networdId";
		SQLQuery query = session.createSQLQuery(sql);
		query.addEntity(Sim.class);
		query.setParameter("networdId", networdId);
		query.setFirstResult((page - 1) * size);
		query.setMaxResults(size);
		List<Sim> results = query.list();

		return results;
	}

	public List<Sim> findByScore(int score, int page, int size) {
		session = sessionFactory.getCurrentSession();
		String sql = "SELECT * FROM sim WHERE score = :score";
		SQLQuery query = session.createSQLQuery(sql);
		query.addEntity(Sim.class);
		query.setParameter("score", score);
		query.setFirstResult((page - 1) * size);
		query.setMaxResults(size);
		List<Sim> results = query.list();

		return results;
	}

	public List<Sim> findByTotalNumbers(int totalNumbers, int page, int size) {
		session = sessionFactory.getCurrentSession();
		String sql = "SELECT * FROM sim WHERE sumOfNumbers = :totalNumbers";
		SQLQuery query = session.createSQLQuery(sql);
		query.addEntity(Sim.class);
		query.setParameter("totalNumbers", totalNumbers);
		query.setFirstResult((page - 1) * size);
		query.setMaxResults(size);
		List<Sim> results = query.list();

		return results;
	}

	public List<Sim> findByPhoneStart(String number, int page, int size) {
		session = sessionFactory.getCurrentSession();
		String sql = "SELECT * FROM sim WHERE realNumber LIKE :number";
		SQLQuery query = session.createSQLQuery(sql);
		query.addEntity(Sim.class);
		String findString = number + "%";
		query.setParameter("number", findString);
		query.setFirstResult((page - 1) * size);
		query.setMaxResults(size);
		List<Sim> results = query.list();

		return results;
	}

	public List<Sim> findByPhoneEnd(String number, int page, int size) {
		session = sessionFactory.getCurrentSession();
		String sql = "SELECT * FROM sim WHERE realNumber LIKE :number";
		SQLQuery query = session.createSQLQuery(sql);
		query.addEntity(Sim.class);
		String findString = "%" + number;
		query.setParameter("number", findString);
		query.setFirstResult((page - 1) * size);
		query.setMaxResults(size);
		List<Sim> results = query.list();

		return results;
	}

	public List<Sim> findByPhoneInside(String number, int page, int size) {
		session = sessionFactory.getCurrentSession();
		String sql = "SELECT * FROM sim WHERE realNumber LIKE :number";
		SQLQuery query = session.createSQLQuery(sql);
		query.addEntity(Sim.class);
		String findString = "%" + number + "%";
		query.setParameter("number", findString);
		query.setFirstResult((page - 1) * size);
		query.setMaxResults(size);
		List<Sim> results = query.list();

		return results;
	}

	public boolean updateSoldSim(List<Integer> list) {
		session = sessionFactory.getCurrentSession();
		String sql = "UPDATE sim SET sold = '1' WHERE ";
		StringBuilder findSimId = new StringBuilder("");
		if (list != null) {
			findSimId.append(" id IN ( ");
			for (int i = 0; i < list.size(); i++) {
				if (i == list.size() - 1) {
					findSimId.append(list.get(i)).append(" ) ");
				} else {
					findSimId.append(list.get(i)).append(" , ");
				}
			}
		}
		sql += findSimId.toString();
		SQLQuery query = session.createSQLQuery(sql);
		query.addEntity(Sim.class);
		query.executeUpdate();
		return true;
	}

	public List<Sim> findSim(Integer networkId, double priceFrom, double priceTo, Integer score, Integer totalNumbers,
			String number, List<Integer> notContainNumbers, int page, int size, Integer enabled, Integer sold,
			Integer simType) {
		session = sessionFactory.getCurrentSession();
		String sql = "SELECT * FROM sim WHERE (price >= :priceFrom) AND (price <= :priceTo) ";
		StringBuilder str = new StringBuilder("");
		if (networkId != null && networkId > 0)
			str.append(" AND (networdId ='").append(networkId).append("') ");
		if (simType != null && simType > 0)
			str.append(" AND (simTypeId ='").append(simType).append("') ");
		if (score != null && score > -1 && score < 10)
			str.append(" AND (score ='").append(score).append("') ");
		if (totalNumbers != null && totalNumbers > 20 && totalNumbers < 81)
			str.append(" AND (sumOfNumbers ='").append(totalNumbers).append("') ");
		if (notContainNumbers != null) {
			str.append(" AND (realNumber ");
			for (int i = 0; i < notContainNumbers.size(); i++) {
				if (i == notContainNumbers.size() - 1) {
					str.append(" NOT LIKE '%").append(notContainNumbers.get(i)).append("%' )");
				} else {
					str.append(" NOT LIKE '%").append(notContainNumbers.get(i)).append("%' )")
							.append(" AND (realNumber ");
				}
			}
		}
		if (number != null && number.length() > 0) {
			String[] words = number.split("\\*");
			if (words.length == 1)
				str.append(" AND (realNumber LIKE '%").append(words[0]).append("%' )");
			if (words.length == 2)
				str.append(" AND (realNumber LIKE '").append(words[0]).append("%").append(words[1]).append("' )");
		}
		if (enabled != null) {
			str.append(" AND (`enabled` ='").append(enabled).append("') ");
		}
		if (sold != null) {
			str.append(" AND (`sold` ='").append(sold).append("') ");
		}
		sql += str.toString();
		SQLQuery query = session.createSQLQuery(sql);
		query.addEntity(Sim.class);
		query.setParameter("priceFrom", priceFrom);
		query.setParameter("priceTo", priceTo);
		query.setFirstResult((page - 1) * size);
		query.setMaxResults(size);
		List<Sim> results = query.list();

		return results;
	}

	public void setSimSold(List<Integer> listSimId) {
		if (listSimId != null) {
			session = sessionFactory.getCurrentSession();
			StringBuilder sqlSim = new StringBuilder("UPDATE sim SET `sold` = '1' WHERE id in (");
			for (int i = 0; i < listSimId.size(); i++) {
				if (i == listSimId.size() - 1) {
					sqlSim.append(listSimId.get(i)).append(")");
				} else {
					sqlSim.append(listSimId.get(i)).append(",");
				}
			}
			SQLQuery query = session.createSQLQuery(sqlSim.toString());
			query.executeUpdate();
		}
	}

	public int countAll(Integer networkId, double priceFrom, double priceTo, Integer score, Integer totalNumbers,
			String number, List<Integer> notContainNumbers, Integer simType) {
		session = sessionFactory.getCurrentSession();
		String sql = "SELECT COUNT(*) FROM sim WHERE (`enabled` = '1') AND (sold = '0') AND (price >= :priceFrom) AND (price <= :priceTo) ";
		StringBuilder str = new StringBuilder("");
		if (networkId != null && networkId > 0)
			str.append(" AND (networdId ='").append(networkId).append("') ");
		if (simType != null && simType > 0)
			str.append(" AND (simTypeId ='").append(simType).append("') ");
		if (score != null && score > -1 && score < 10)
			str.append(" AND (score ='").append(score).append("') ");
		if (totalNumbers != null && totalNumbers > 20 && totalNumbers < 81)
			str.append(" AND (sumOfNumbers ='").append(totalNumbers).append("') ");
		if (notContainNumbers != null) {
			str.append(" AND (realNumber ");
			for (int i = 0; i < notContainNumbers.size(); i++) {
				if (i == notContainNumbers.size() - 1) {
					str.append(" NOT LIKE '%").append(notContainNumbers.get(i)).append("%' )");
				} else {
					str.append(" NOT LIKE '%").append(notContainNumbers.get(i)).append("%' )")
							.append(" AND (realNumber ");
				}
			}
		}
		if (number != null && number.length() > 0) {
			String[] words = number.split("\\*");
			if (words.length == 1)
				str.append(" AND (realNumber LIKE '%").append(words[0]).append("%' )");
			if (words.length == 2)
				str.append(" AND (realNumber LIKE '").append(words[0]).append("%").append(words[1]).append("' )");
		}

		sql += str.toString();
		SQLQuery query = session.createSQLQuery(sql);
		query.setParameter("priceFrom", priceFrom);
		query.setParameter("priceTo", priceTo);

		return Integer.parseInt(query.list().get(0).toString());
	}

	public boolean simIsExist(String simNumber) {
		session = sessionFactory.getCurrentSession();
		String sql = "SELECT COUNT(*) FROM sim WHERE realNumber =:simNumber";
		SQLQuery query = session.createSQLQuery(sql);
		query.setParameter("simNumber", simNumber);
		int count = Integer.parseInt(query.list().get(0).toString());
		if (count > 0)
			return true;
		return false;
	}
}
