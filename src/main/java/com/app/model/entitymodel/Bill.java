package com.app.model.entitymodel;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the bill database table.
 * 
 */
@Entity
public class Bill implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@Temporal(TemporalType.DATE)
	private Date dateCreate;

	private double price;

	private String status;

	//bi-directional many-to-one association to Mainuser
	@ManyToOne
	@JoinColumn(name="username")
	private MainUser mainuser;

	public Bill() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDateCreate() {
		return this.dateCreate;
	}

	public void setDateCreate(Date dateCreate) {
		this.dateCreate = dateCreate;
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

	public MainUser getMainuser() {
		return this.mainuser;
	}

	public void setMainuser(MainUser mainuser) {
		this.mainuser = mainuser;
	}

}