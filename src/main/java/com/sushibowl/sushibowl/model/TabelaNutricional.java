package com.sushibowl.sushibowl.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="tb_tabelaNutricional")
public class TabelaNutricional extends AbstractEntity {

	private static final long serialVersionUID = 1L;
	
	@Column(name="qt_cals", length = 6)
	private float cals;
	@Column(name="qt_gord", length = 6)
	private float gord;
	@Column(name="qt_carbs", length = 6)
	private float carbs;
	@Column(name="qt_prot", length = 6)
	private float prot;
	
	public TabelaNutricional() {}

	public float getCals() {
		return cals;
	}

	public void setCals(float cals) {
		this.cals = cals;
	}

	public float getGord() {
		return gord;
	}

	public void setGord(float gord) {
		this.gord = gord;
	}

	public float getCarbs() {
		return carbs;
	}

	public void setCarbs(float carbs) {
		this.carbs = carbs;
	}

	public float getProt() {
		return prot;
	}

	public void setProt(float prot) {
		this.prot = prot;
	}	

}
