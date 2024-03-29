package com.jwd.test.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.jwd.test.model.Linija;
import com.jwd.test.repository.LinijaRepository;
import com.jwd.test.service.LinijaService;

@Service
public class JpaLinijaService  implements LinijaService{ 

	@Autowired
	LinijaRepository linijaRepository;
	
	@Override
	public Page<Linija> findAll(int pageNo) {
		return linijaRepository.findAll(PageRequest.of(pageNo, 3));
	}

	@Override
	public Linija findOne(Long id) {
		Optional<Linija> linija=linijaRepository.findById(id);
		if(linija.isPresent())
			return linija.get();
		return null;
	}

	@Override
	public Linija save(Linija linija) {
		return linijaRepository.save(linija);
	}

	@Override
	public Linija update(Linija linija) {
		return linijaRepository.save(linija);
	}

	@Override
	public Linija delete(Long id) {
		Optional<Linija> linija=linijaRepository.findById(id);
		if(linija.isPresent()) {
			linijaRepository.delete(linija.get());
			return linija.get();
			}
		return null;
	}

	@Override
	public Page<Linija> search(String destinacija, Long prevoznikId, Double cenaKarteDo, int pageNo) {
		return linijaRepository.search(destinacija, prevoznikId, cenaKarteDo, PageRequest.of(pageNo, 4));
	}

	

}
