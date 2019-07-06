package com.app.dao.impl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.app.dao.BillDao;
import com.app.dao.CartBillDetailDao;
import com.app.dao.SimDao;
import com.app.model.entitymodel.Bill;
import com.app.model.entitymodel.CartBillDetail;
import com.app.model.entitymodel.MainUser;

@Repository
@Transactional
public class CartBillDetailDaoImpl implements CartBillDetailDao {
	@Autowired
	private BillDao billDao;
	@Autowired
	private SimDao simDao;
	@Autowired
	private SessionFactory sessionFactory;
	private Session session;

	public void add(CartBillDetail n) {
		sessionFactory.getCurrentSession().save(n);
	}

	public void update(CartBillDetail n) {
		sessionFactory.getCurrentSession().merge(n);

	}

	public void delete(int id) {
		sessionFactory.getCurrentSession().delete(getById(id));

	}

	public CartBillDetail getById(int id) {
		return (CartBillDetail) sessionFactory.getCurrentSession().get(CartBillDetail.class, id);
	}

	public List<CartBillDetail> getAll() {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(CartBillDetail.class);
		return criteria.list();
	}

	public List<CartBillDetail> find(String username, int page, int size) {
		session = sessionFactory.getCurrentSession();
		String sql = "SELECT * FROM cart_bill_detail WHERE username = :username";
		SQLQuery query = session.createSQLQuery(sql);
		query.addEntity(CartBillDetail.class);
		query.setParameter("username", username);
		query.setFirstResult((page - 1) * size);
		query.setMaxResults(size);
		List<CartBillDetail> results = query.list();

		return results;
	}

	public List<CartBillDetail> find(String username, String status, int page, int size) {
		session = sessionFactory.getCurrentSession();
		String sql = "SELECT * FROM cart_bill_detail WHERE username = :username AND `status` = :status ";
		SQLQuery query = session.createSQLQuery(sql);
		query.addEntity(CartBillDetail.class);
		query.setParameter("username", username);
		query.setParameter("status", status);
		query.setFirstResult((page - 1) * size);
		query.setMaxResults(size);
		List<CartBillDetail> results = query.list();

		return results;
	}

	public List<CartBillDetail> findBillByUsername(String username, int page, int size) {
		session = sessionFactory.getCurrentSession();
		String sql = "SELECT * FROM cart_bill_detail WHERE username = :username AND `status` = 'ORDERED' ";
		SQLQuery query = session.createSQLQuery(sql);
		query.addEntity(CartBillDetail.class);
		query.setParameter("username", username);
		query.setFirstResult((page - 1) * size);
		query.setMaxResults(size);
		List<CartBillDetail> results = query.list();

		return results;
	}

	public List<CartBillDetail> findCartByUsernameAndListSimId(String username, List<Integer> listId, int page,
			int size) {
		session = sessionFactory.getCurrentSession();
		String sql = "SELECT * FROM cart_bill_detail WHERE username = :username AND `status` = 'READY' ";
		StringBuilder findId = new StringBuilder("");
		if (listId != null) {
			findId.append(" AND id IN ( ");
			for (int i = 0; i < listId.size(); i++) {
				if (i == listId.size() - 1) {
					findId.append(listId.get(i));
					findId.append(" ) ");
				} else {
					findId.append(listId.get(i));
					findId.append(" , ");
				}
			}
		}
		sql += findId.toString();
		SQLQuery query = session.createSQLQuery(sql);
		query.addEntity(CartBillDetail.class);
		query.setParameter("username", username);
		query.setFirstResult((page - 1) * size);
		query.setMaxResults(size);
		List<CartBillDetail> results = query.list();

		return results;
	}

	// ghi 3 phan khac nhau de han che so lan truy suat db va do phai dung lai
	// phuong thuc tim roi dung vong lap, nhu vay mat tg hon
	public boolean payByUsernameAndListId(String username, List<Integer> listId) {
		session = sessionFactory.getCurrentSession();
		List<CartBillDetail> listResult = getByUsernameListId(username, listId);

		List<Integer> listSimId = new ArrayList<Integer>();
		double totalPriceOfBill = 0;
		if (listResult != null) {
			for (int i = 0; i < listResult.size(); i++) {
				if (i == listResult.size() - 1) {
					totalPriceOfBill += listResult.get(i).getPrice();
					listSimId.add(listResult.get(i).getSim().getId());
				} else {
					totalPriceOfBill += listResult.get(i).getPrice();
					listSimId.add(listResult.get(i).getSim().getId());
				}
			}
		}

		// create new bill
		MainUser u = new MainUser();
		u.setUsername(username);
		Bill bill = new Bill();
		bill.setMainuser(u);
		bill.setDateCreate(Date.from(LocalDate.now().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
		bill.setStatus("ORDERED");
		bill.setPrice(totalPriceOfBill);
		billDao.add(bill);

		simDao.setSimSold(listSimId);
		if (listId != null) {
			setStatusOrdered(listId, bill.getId(), username);
		}

		return true;
	}

	public void setStatusOrdered(List<Integer> listId, Integer billId, String username) {
		if (listId != null) {
			session = sessionFactory.getCurrentSession();
			StringBuilder sql = new StringBuilder("");
			sql.append("UPDATE cart_bill_detail SET `status` = 'ORDERED', ").append("billId= '").append(billId)
					.append("' WHERE username = :username AND `status` = 'READY' ");
			sql.append(" AND id IN ( ");
			for (int i = 0; i < listId.size(); i++) {
				if (i == listId.size() - 1) {
					sql.append(listId.get(i));
					sql.append(" ) ");
				} else {
					sql.append(listId.get(i));
					sql.append(" , ");
				}
			}
			SQLQuery query = session.createSQLQuery(sql.toString());
			query.addEntity(CartBillDetail.class);
			query.setParameter("username", username);
			query.executeUpdate();
		}
	}

	public List<CartBillDetail> getByUsernameListId(String username, List<Integer> listId) {
		if (listId != null) {
			session = sessionFactory.getCurrentSession();
			String sqlTotalPrice = "SELECT * FROM cart_bill_detail WHERE username = :username AND `status` = 'READY' ";
			StringBuilder findId = new StringBuilder("");
			if (!listId.isEmpty()) {
				findId.append(" AND id IN (");
				for (int i = 0; i < listId.size(); i++) {
					if (i == listId.size() - 1) {
						findId.append(listId.get(i)).append(") ");
					} else {
						findId.append(listId.get(i)).append(" , ");
					}
				}
			}
			sqlTotalPrice += findId.toString();
			SQLQuery queryTotal = session.createSQLQuery(sqlTotalPrice);
			queryTotal.setParameter("username", username);
			queryTotal.addEntity(CartBillDetail.class);
			List<CartBillDetail> listResult = queryTotal.list();
			return listResult;
		} else {
			return null;
		}
	}

	public boolean isExist(String username, int simId) {
		session = sessionFactory.getCurrentSession();
		String sql = "SELECT * FROM cart_bill_detail WHERE username = :username AND simId = :simId ";
		SQLQuery query = session.createSQLQuery(sql);
		query.addEntity(CartBillDetail.class);
		query.setParameter("username", username);
		query.setParameter("simId", simId);
		List<CartBillDetail> results = query.list();
		if (results.size() == 0 || results == null)
			return false;
		return true;
	}

}
