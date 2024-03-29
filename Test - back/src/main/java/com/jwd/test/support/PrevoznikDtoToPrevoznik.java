package com.jwd.test.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.jwd.test.model.Prevoznik;
import com.jwd.test.service.PrevoznikService;
import com.jwd.test.web.dto.PrevoznikDTO;

@Component
public class PrevoznikDtoToPrevoznik implements Converter<PrevoznikDTO, Prevoznik>{

	
	
	@Override
	public Prevoznik convert(PrevoznikDTO source) {
		
		Prevoznik prevoznik=new Prevoznik();
		
		prevoznik.setAdresa(source.getAdresa());
		prevoznik.setId(source.getId());
		prevoznik.setNaziv(source.getNaziv());
		prevoznik.setPib(source.getPib());
		
		return prevoznik;
	}
	
}
