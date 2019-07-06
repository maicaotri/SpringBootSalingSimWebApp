package com.app.model.entitymodel;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * The persistent class for the sim database table.
 * 
 */
@Entity
public class Sim implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private Double dealPrice;

	private byte enabled;

	private byte sold;

	private double price;

	private String realNumber;

	private short score;

	private short sumOfNumbers;

	private String viewNumber;

	// bi-directional many-to-one association to CartBillDetail
	@OneToMany(mappedBy = "sim")
	@JsonIgnore
	private List<CartBillDetail> cartBillDetails;

	// bi-directional many-to-one association to Simtype
	@ManyToOne
	@JoinColumn(name = "simTypeId")
	private SimType simType;

	// bi-directional many-to-one association to Netword
	@ManyToOne
	@JoinColumn(name = "networdId")
	private Netword netword;

	public Sim() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getDealPrice() {
		return this.dealPrice;
	}

	public void setDealPrice(Double dealPrice) {
		this.dealPrice = dealPrice;
	}

	public byte getEnabled() {
		return this.enabled;
	}

	public void setEnabled(byte enabled) {
		this.enabled = enabled;
	}

	public byte getSold() {
		return sold;
	}

	public void setSold(byte sold) {
		this.sold = sold;
	}

	public double getPrice() {
		return this.price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getRealNumber() {
		return this.realNumber;
	}

	public void setRealNumber(String realNumber) {
		this.realNumber = realNumber;
	}

	public short getScore() {
		return this.score;
	}

	public void setScore(short score) {
		this.score = score;
	}

	public short getSumOfNumbers() {
		return this.sumOfNumbers;
	}

	public void setSumOfNumbers(short sumOfNumbers) {
		this.sumOfNumbers = sumOfNumbers;
	}

	public String getViewNumber() {
		return this.viewNumber;
	}

	public void setViewNumber(String viewNumber) {
		this.viewNumber = viewNumber;
	}

	public Netword getNetword() {
		return this.netword;
	}

	public void setNetword(Netword netword) {
		this.netword = netword;
	}

	public SimType getSimType() {
		return simType;
	}

	public void setSimType(SimType simType) {
		this.simType = simType;
	}

	public List<CartBillDetail> getCartBillDetails() {
		return this.cartBillDetails;
	}

	public void setCartBillDetails(List<CartBillDetail> cartBillDetails) {
		this.cartBillDetails = cartBillDetails;
	}

	public CartBillDetail addCartBillDetail(CartBillDetail cartBillDetail) {
		getCartBillDetails().add(cartBillDetail);
		cartBillDetail.setSim(this);

		return cartBillDetail;
	}

	public CartBillDetail removeCartBillDetail(CartBillDetail cartBillDetail) {
		getCartBillDetails().remove(cartBillDetail);
		cartBillDetail.setSim(null);

		return cartBillDetail;
	}
}