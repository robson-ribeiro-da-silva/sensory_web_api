package br.edu.ifrn.projetosensoryweb.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.edu.ifrn.projetosensoryweb.model.AnaliseSensorial;
import br.edu.ifrn.projetosensoryweb.service.AnaliseSensorialService;

@RestController
@RequestMapping("/api/analisesensorial")
public class AnaliseSensorialResource {
	
	@Autowired
	private AnaliseSensorialService service;
	
	@GetMapping(value="/findAll")
	public ResponseEntity<List<AnaliseSensorial>> findAll(){
		
		List<AnaliseSensorial> analises = service.findAll();
		
		if(analises.isEmpty()){
			return ResponseEntity.notFound().build();		
		}

		return  new ResponseEntity<>(analises, HttpStatus.OK);
	}

}
