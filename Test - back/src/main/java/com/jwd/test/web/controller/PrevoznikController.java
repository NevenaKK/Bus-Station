package com.jwd.test.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jwd.test.model.Prevoznik;
import com.jwd.test.service.PrevoznikService;
import com.jwd.test.support.PrevoznikDtoToPrevoznik;
import com.jwd.test.support.PrevoznikToPrevoznikDto;
import com.jwd.test.web.dto.PrevoznikDTO;

@RestController
@RequestMapping(value = "/api/prevoznici" ,produces = MediaType.APPLICATION_JSON_VALUE)
public class PrevoznikController {

	@Autowired
	PrevoznikService prevoznikService;
	
	@Autowired
	PrevoznikDtoToPrevoznik toPrevoznik;
	
	@Autowired
	PrevoznikToPrevoznikDto toPrevoznikDto;
	
	@GetMapping
	public ResponseEntity<List<PrevoznikDTO>> getAll(){
		
		List<Prevoznik> prevoznici =prevoznikService.findAll();
		
		return new ResponseEntity<List<PrevoznikDTO>>(toPrevoznikDto.convert(prevoznici),HttpStatus.OK);
		
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PrevoznikDTO>create(@Valid @RequestBody PrevoznikDTO prevoznikDTO){
		
		Prevoznik prevoznik=toPrevoznik.convert(prevoznikDTO); 
		Prevoznik sacuvani = prevoznikService.save(prevoznik);
		
		
		return new ResponseEntity<PrevoznikDTO>(toPrevoznikDto.convert(sacuvani),HttpStatus.CREATED);
	 
		
	}
	
	 @ExceptionHandler(value = DataIntegrityViolationException.class)
	    public ResponseEntity<Void> handle() {
	        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	    }
	
	
}
