package com.app.model.entitymodel;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * The persistent class for the netword database table.
 * 
 */
@Entity
public class Netword implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Byte id;

	private String name;

//	// bi-directional many-to-one association to Sim
//	@OneToMany(mappedBy = "netword")
//	@JsonIgnore
//	private List<Sim> sims;

	public Netword() {
	}

	public Byte getId() {
		return this.id;
	}

	public void setId(Byte id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

//	public List<Sim> getSims() {
//		return this.sims;
//	}
//
//	public void setSims(List<Sim> sims) {
//		this.sims = sims;
//	}
//
//	public Sim addSim(Sim sim) {
//		getSims().add(sim);
//		sim.setNetword(this);
//
//		return sim;
//	}
//
//	public Sim removeSim(Sim sim) {
//		getSims().remove(sim);
//		sim.setNetword(null);
//
//		return sim;
//	}

}