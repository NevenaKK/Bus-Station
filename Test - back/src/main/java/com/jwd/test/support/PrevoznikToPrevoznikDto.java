package com.jwd.test.support;

import java.util.ArrayList;

import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.jwd.test.model.Prevoznik;
import com.jwd.test.web.dto.PrevoznikDTO;

@Component
public class PrevoznikToPrevoznikDto implements Converter<Prevoznik, PrevoznikDTO> {

	@Override
	public PrevoznikDTO convert(Prevoznik source) {
		
		PrevoznikDTO dto=new PrevoznikDTO();
		
		dto.setAdresa(source.getAdresa());
		dto.setId(source.getId());
		dto.setNaziv(source.getNaziv());
		dto.setPib(source.getPib());
		
		return dto;
	}
	
	public List<PrevoznikDTO> convert(List<Prevoznik> source) {
		
		List<PrevoznikDTO> dto=new ArrayList<PrevoznikDTO>();
		
		for(Prevoznik p :source)
			dto.add(convert(p));

		
		return dto;
	}
	
	

	
}
