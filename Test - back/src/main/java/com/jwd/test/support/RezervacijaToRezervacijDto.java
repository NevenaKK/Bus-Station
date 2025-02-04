package com.jwd.test.support;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.jwd.test.model.Rezervacija;
import com.jwd.test.web.dto.RezervacijaDTO;

@Component
public class RezervacijaToRezervacijDto implements Converter<Rezervacija, RezervacijaDTO>{

	@Override
	public RezervacijaDTO convert(Rezervacija source) {
		
			RezervacijaDTO dto =new RezervacijaDTO();
			
			dto.setId(source.getId());
			dto.setLinijaId(source.getLinija().getId());
			dto.setBrojMesta(source.getBrojMesta());
			return dto;
	}

}
