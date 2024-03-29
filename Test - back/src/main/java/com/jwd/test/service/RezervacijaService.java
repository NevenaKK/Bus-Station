package com.jwd.test.service;

import com.jwd.test.model.Rezervacija;

public interface RezervacijaService {
	
	Rezervacija findOne(Long id);
	Rezervacija save(Rezervacija rezervacija);

}
