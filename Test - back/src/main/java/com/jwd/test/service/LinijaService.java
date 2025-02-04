package com.jwd.test.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.jwd.test.model.Linija;

public interface LinijaService {

	Page<Linija> findAll(int pageNo);
	Linija findOne(Long id);
	Linija save(Linija linija);
	Linija update(Linija linija);
	Linija delete(Long id);
	
	Page<Linija> search(String destinacija ,Long prevoznikId,Double cenaKarteDo,int pageNo);
	

	
}
