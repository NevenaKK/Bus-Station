package com.jwd.test.model;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Linija {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private int brojMesta;
	@Column
	private double cenaKarte;
	@Column
	private LocalTime vremePolaska;
	@Column(nullable = false)
	private String destinacija;
	@ManyToOne
	private Prevoznik prevoznik;
	
	@OneToMany(mappedBy = "linija" ,cascade = CascadeType.ALL)
	private List<Rezervacija> rezervacije; 
	
	
	public Linija(int brojMesta, double cenaKarte, LocalTime vremePolaska, String destinacija, Prevoznik prevoznik,
			List<Rezervacija> rezervacije) {
		super();
		this.brojMesta = brojMesta;
		this.cenaKarte = cenaKarte;
		this.vremePolaska = vremePolaska;
		this.destinacija = destinacija;
		this.prevoznik = prevoznik;
		this.rezervacije = rezervacije;
	}
	public Linija(Long id, int brojMesta, double cenaKarte, LocalTime vremePolaska, String destinacija,
			Prevoznik prevoznik, List<Rezervacija> rezervacije) {
		super();
		this.id = id;
		this.brojMesta = brojMesta;
		this.cenaKarte = cenaKarte;
		this.vremePolaska = vremePolaska;
		this.destinacija = destinacija;
		this.prevoznik = prevoznik;
		this.rezervacije = rezervacije;
	}
	public Linija() {
		super();
	}
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Linija other = (Linija) obj;
		return Objects.equals(id, other.id);
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getBrojMesta() {
		return brojMesta;
	}
	public void setBrojMesta(int brojMesta) {
		this.brojMesta = brojMesta;
	}
	public double getCenaKarte() {
		return cenaKarte;
	}
	public void setCenaKarte(double cenaKarte) {
		this.cenaKarte = cenaKarte;
	}
	public LocalTime getVremePolaska() {
		return vremePolaska;
	}
	public void setVremePolaska(LocalTime vremePolaska) {
		this.vremePolaska = vremePolaska;
	}
	public String getDestinacija() {
		return destinacija;
	}
	public void setDestinacija(String destinacija) {
		this.destinacija = destinacija;
	}
	public Prevoznik getPrevoznik() {
		return prevoznik;
	}
	public void setPrevoznik(Prevoznik prevoznik) {
		this.prevoznik = prevoznik;
	}
	public List<Rezervacija> getRezervacije() {
		return rezervacije;
	}
	public void setRezervacije(List<Rezervacija> rezervacije) {
		this.rezervacije = rezervacije;
	}
	
	
}
