package com.jwd.test.web.controller;


import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.function.ServerRequest.Headers;

import com.jwd.test.model.Linija;
import com.jwd.test.model.Prevoznik;
import com.jwd.test.service.LinijaService;
import com.jwd.test.support.LinijaDtoToLinija;
import com.jwd.test.support.LinijaToLinijaDto;
import com.jwd.test.web.dto.LinijaDTO;
import com.jwd.test.web.dto.PrevoznikDTO;

@RestController
@RequestMapping(value = "/api/linije" ,produces = MediaType.APPLICATION_JSON_VALUE)
public class LinijaController {
	
	@Autowired
	LinijaService linijaService;
	
	@Autowired
	LinijaToLinijaDto toLinijaDto;
	
	@Autowired 
	LinijaDtoToLinija toLinija;
	
	@GetMapping
	public ResponseEntity<List<LinijaDTO>> getAll(
			@RequestParam(required = false) String destinacija,
			@RequestParam(required = false) Long prevoznikId,
			@RequestParam(required = false) Double cenaKarteDo,
			@RequestParam (required = false ,defaultValue = "0") int pageNo){
		
		Page<Linija> linije =linijaService.search(destinacija, prevoznikId, cenaKarteDo, pageNo);
		
		
		HttpHeaders headers=new HttpHeaders();
		headers.add("Total-Pages", Integer.toString(linije.getTotalPages()));
		
		
		return new ResponseEntity<List<LinijaDTO>>(toLinijaDto.convert(linije.getContent()),headers,HttpStatus.OK); 
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<LinijaDTO> getOne(@PathVariable Long id){
		
		Linija linija=linijaService.findOne(id);
		
		if(linija!=null)
			return new ResponseEntity<LinijaDTO>(toLinijaDto.convert(linija),HttpStatus.OK);
		else
			return new ResponseEntity<LinijaDTO>(HttpStatus.NOT_FOUND);
		
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LinijaDTO>create(@Valid @RequestBody LinijaDTO linijaDTO){
		
		Linija linija=toLinija.convert(linijaDTO); 
		Linija sacuvani = linijaService.save(linija);
			
		return new ResponseEntity<LinijaDTO>(toLinijaDto.convert(sacuvani),HttpStatus.CREATED);
		
	}
	
	@PutMapping(value = "/{id}" ,consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LinijaDTO> update(@PathVariable Long id , @Valid @RequestBody LinijaDTO linijaDTO){
		
		Linija linija=toLinija.convert(linijaDTO);
		Linija izmena =linijaService.save(linija);
		
		if(!id.equals(linijaDTO.getId()))
			return new ResponseEntity<LinijaDTO>(HttpStatus.BAD_REQUEST);
			
		else
			return new ResponseEntity<LinijaDTO>(toLinijaDto.convert(izmena),HttpStatus.OK);
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<LinijaDTO> delete(@PathVariable Long id ){
		
		Linija linija=linijaService.delete(id);
		
		if(linija!=null)
			return new ResponseEntity<LinijaDTO>(HttpStatus.NO_CONTENT);
		else
			return new ResponseEntity<LinijaDTO>(HttpStatus.NOT_FOUND);
		
	}
	
	 @ExceptionHandler(value = DataIntegrityViolationException.class)
	    public ResponseEntity<Void> handle() {
	        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	    }
	
	
}
	
	
	
	
	
	
	
	
	
	
	


