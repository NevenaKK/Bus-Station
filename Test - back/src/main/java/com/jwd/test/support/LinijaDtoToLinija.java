package com.jwd.test.support;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.jwd.test.model.Linija;
import com.jwd.test.service.LinijaService;
import com.jwd.test.service.PrevoznikService;
import com.jwd.test.web.dto.LinijaDTO;

@Component
public class LinijaDtoToLinija implements Converter<LinijaDTO, Linija>{

	@Autowired
	LinijaService linijaService;
	
	@Autowired 
	PrevoznikService prevoznikService;
	
	@Override
	public Linija convert(LinijaDTO source) {
		
		Linija linija;
		
		if(source.getId()==null)
			linija=new Linija();
		else
			linija=linijaService.findOne(source.getId());
		
		if(linija!=null) {
			linija.setBrojMesta(source.getBrojMesta());
			linija.setCenaKarte(source.getCenaKarte());
			linija.setDestinacija(source.getDestinacija());
			linija.setId(source.getId());
			linija.setPrevoznik(prevoznikService.findOne(source.getPrevoznikId()));
			linija.setVremePolaska(getLocalDateTime(source.getVremePolaska()));
		}
		
		return linija;
	}
	
	private LocalTime getLocalDateTime(String dateTime) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        return LocalTime.parse(dateTime, formatter);
    }

}
