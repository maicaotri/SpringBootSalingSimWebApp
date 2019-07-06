package com.app.model;

import java.util.List;

import com.app.model.entitymodel.Sim;

public class SimView {
	List<Sim> listSim;
	List<Integer> listPage;
	
	public SimView(List<Sim> listSim, List<Integer> listPage) {
		this.listSim = listSim;
		this.listPage = listPage;
	}

	public List<Sim> getListSim() {
		return listSim;
	}

	public void setListSim(List<Sim> listSim) {
		this.listSim = listSim;
	}

	public List<Integer> getListPage() {
		return listPage;
	}

	public void setListPage(List<Integer> listPage) {
		this.listPage = listPage;
	}
	
}
