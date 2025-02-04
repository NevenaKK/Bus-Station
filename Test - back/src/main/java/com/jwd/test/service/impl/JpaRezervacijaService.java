package com.jwd.test.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.jwd.test.model.Rezervacija;
import com.jwd.test.repository.RezervacijaRepository;
import com.jwd.test.service.LinijaService;
import com.jwd.test.service.RezervacijaService;

@Service
public class JpaRezervacijaService implements RezervacijaService{

	@Autowired
	RezervacijaRepository rezervacijaRepository;
	
	@Autowired
	LinijaService linijaService;
	
	@Override
	public Rezervacija save(Rezervacija rezervacija) {
		
		int trenutniBrojMestaLinije=rezervacija.getLinija().getBrojMesta();
		int noviBrojMestaLinije=trenutniBrojMestaLinije-rezervacija.getBrojMesta();
		
		
		if(noviBrojMestaLinije>=0) {
		
				rezervacija.getLinija().setBrojMesta(noviBrojMestaLinije);
				return rezervacijaRepository.save(rezervacija);
			
		}
		else
			throw new IllegalArgumentException("Nema slobodnih mesta !");
	}

	@Override
	public Rezervacija findOne(Long id) {
		Optional<Rezervacija> rez=rezervacijaRepository.findById(id);
		if(rez.isPresent())
			return rez.get();
		return null;
	}

}
