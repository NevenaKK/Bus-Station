package com.jwd.test.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jwd.test.model.Linija;
import com.jwd.test.model.Prevoznik;
import com.jwd.test.repository.PrevoznikRepository;
import com.jwd.test.service.PrevoznikService;

@Service
public class JpaPrevoznikService implements PrevoznikService {

	@Autowired
	PrevoznikRepository prevoznikRepository;
	
	@Override
	public List<Prevoznik> findAll() {
		return prevoznikRepository.findAll();
	}

	@Override
	public Prevoznik save(Prevoznik prevoznik) {
		return prevoznikRepository.save(prevoznik);
	}

	@Override
	public Prevoznik findOne(Long id) {
		Optional<Prevoznik> prevoznik=prevoznikRepository.findById(id);
		if(prevoznik.isPresent())
			return prevoznik.get();
		return null;
	}

}
