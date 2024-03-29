package com.jwd.test.web.dto;

import javax.persistence.ManyToOne;

import com.jwd.test.model.Linija;

public class RezervacijaDTO {

	private Long id;

	private Long linijaId;
	
	private int brojMesta;

	public RezervacijaDTO() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getLinijaId() {
		return linijaId;
	}

	public void setLinijaId(Long linijaId) {
		this.linijaId = linijaId;
	}

	public int getBrojMesta() {
		return brojMesta;
	}

	public void setBrojMesta(int brojMesta) {
		this.brojMesta = brojMesta;
	}
	
	
	
	
}
