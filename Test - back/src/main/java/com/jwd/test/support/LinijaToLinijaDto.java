package com.jwd.test.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.jwd.test.model.Linija;
import com.jwd.test.model.Prevoznik;
import com.jwd.test.web.dto.LinijaDTO;
import com.jwd.test.web.dto.PrevoznikDTO;

@Component
public class LinijaToLinijaDto implements Converter<Linija, LinijaDTO>{

	@Override
	public LinijaDTO convert(Linija source) {
		LinijaDTO dto=new LinijaDTO();
		
		dto.setBrojMesta(source.getBrojMesta());
		dto.setCenaKarte(source.getCenaKarte());
		dto.setDestinacija(source.getDestinacija());
		dto.setId(source.getId());
		dto.setPrevoznikId(source.getPrevoznik().getId());
		dto.setPrevoznikNaziv(source.getPrevoznik().getNaziv());
		dto.setVremePolaska(source.getVremePolaska().toString());
		
		return dto;
	}
	
public List<LinijaDTO> convert(List<Linija> source) {
		
		List<LinijaDTO> dto=new ArrayList<LinijaDTO>();
		
		for(Linija l :source)
			dto.add(convert(l));

		
		return dto;
	}
	

}
