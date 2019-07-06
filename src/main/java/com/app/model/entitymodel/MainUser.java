package com.app.model.entitymodel;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

/**
 * The persistent class for the mainuser database table.
 * 
 */
@Entity
public class MainUser implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static final String ROLE_USER = "ROLE_USER";
	public static final String ROLE_ADMIN = "ROLE_ADMIN";

	@Id
	private String username;

	private String adress;

	private String email;

	private String fName;

	private String lName;

	private String password;

	private String phone;

	private String role;

	private byte enabled;

	private String sex;

	// bi-directional many-to-one association to Bill
//	@OneToMany(mappedBy = "mainuser")
//	private List<Bill> bills;

	// bi-directional many-to-one association to CartBillDetail
//	@OneToMany(mappedBy = "mainuser")
//	private List<CartBillDetail> cartBillDetails;

	public MainUser() {
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAdress() {
		return this.adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getfName() {
		return this.fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return this.lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public byte getEnabled() {
		return this.enabled;
	}

	public void setEnabled(byte enabled) {
		this.enabled = enabled;
	}

//	public List<Bill> getBills() {
//		return this.bills;
//	}
//
//	public void setBills(List<Bill> bills) {
//		this.bills = bills;
//	}

//	public Bill addBill(Bill bill) {
//		getBills().add(bill);
//		bill.setMainuser(this);
//
//		return bill;
//	}
//
//	public Bill removeBill(Bill bill) {
//		getBills().remove(bill);
//		bill.setMainuser(null);
//
//		return bill;
//	}

//	public List<CartBillDetail> getCartBillDetails() {
//		return this.cartBillDetails;
//	}
//
//	public void setCartBillDetails(List<CartBillDetail> cartBillDetails) {
//		this.cartBillDetails = cartBillDetails;
//	}
//
//	public CartBillDetail addCartBillDetail(CartBillDetail cartBillDetail) {
//		getCartBillDetails().add(cartBillDetail);
//		cartBillDetail.setMainuser(this);
//
//		return cartBillDetail;
//	}
//
//	public CartBillDetail removeCartBillDetail(CartBillDetail cartBillDetail) {
//		getCartBillDetails().remove(cartBillDetail);
//		cartBillDetail.setMainuser(null);
//
//		return cartBillDetail;
//	}

}