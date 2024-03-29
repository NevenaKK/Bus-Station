package com.jwd.test.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.jwd.test.model.Rezervacija;
import com.jwd.test.service.LinijaService;
import com.jwd.test.service.RezervacijaService;
import com.jwd.test.web.dto.RezervacijaDTO;

@Component
public class RezervacijaDtoToRezervacija implements Converter<RezervacijaDTO, Rezervacija>{

	@Autowired
	RezervacijaService rezervacijaService;
	
	@Autowired
	LinijaService linijaService;
	
	@Override
	public Rezervacija convert(RezervacijaDTO source) {
		Rezervacija rez=null;
		
		if(source.getId()==null)
			rez=new Rezervacija();
		else
			rez=rezervacijaService.findOne(source.getId());
		
		if(rez!=null) {
			rez.setId(source.getId());
			rez.setLinija(linijaService.findOne(source.getLinijaId()));
			rez.setBrojMesta(source.getBrojMesta());
		}
		
		return rez;
		
		
	}

}
