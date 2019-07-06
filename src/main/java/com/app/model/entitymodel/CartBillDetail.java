package com.app.model.entitymodel;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * The persistent class for the cart_bill_detail database table.
 * 
 */
@Entity
@Table(name = "cart_bill_detail")
public class CartBillDetail implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final String READY = "READY";
	public static final String ORDERED = "ORDERED";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private Integer billId;

	private double price;

	private String status;

	// bi-directional many-to-one association to Sim
	@ManyToOne
	@JoinColumn(name = "simId")
	@JsonIgnore
	private Sim sim;

	// bi-directional many-to-one association to Mainuser
	@ManyToOne
	@JoinColumn(name = "username")
	@JsonIgnore
	private MainUser mainuser;

	public CartBillDetail() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getBillId() {
		return this.billId;
	}

	public void setBillId(Integer billId) {
		this.billId = billId;
	}

	public double getPrice() {
		return this.price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Sim getSim() {
		return this.sim;
	}

	public void setSim(Sim sim) {
		this.sim = sim;
	}

	public MainUser getMainuser() {
		return this.mainuser;
	}

	public void setMainuser(MainUser mainuser) {
		this.mainuser = mainuser;
	}
}