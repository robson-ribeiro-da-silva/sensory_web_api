package br.edu.ifrn.projetosensoryweb.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifrn.projetosensoryweb.model.Avaliador;
import br.edu.ifrn.projetosensoryweb.service.AvaliadorService;
//import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping("/api/avaliador")
public class AvaliadorResource {
	
	@Autowired
	private AvaliadorService service;
	
	//@ApiOperation(value = "Adiciona um novo Avaliador")
	@PostMapping(value="/addNew/{cpf}/{nome}/{sexo}/{dataNascimento}/{fumante}", produces="application/json", consumes="application/json")
	public ResponseEntity<Avaliador> addNew(@PathVariable("cpf") String cpf, @PathVariable("nome") String nome, 
			@PathVariable("sexo") String sexo, @PathVariable("dataNascimento") String dataNascimento,
			@PathVariable("fumante") boolean fumante){
	
		if(cpf == null || cpf == " "){
			return ResponseEntity.notFound().build();	
		}
		
		Avaliador avaliador = service.findByCpf(cpf);
		
		if(avaliador != null){
			return ResponseEntity.notFound().build();	
		}
		
		Avaliador a = new Avaliador();
		
		a.setNome(nome);
		a.setCpf(cpf);
		a.setSexo(sexo);
		a.setDataNascimento(dataNascimento);
		a.setFumante(fumante);
		
		service.save(a);

		return ResponseEntity.ok(a);
	}
	
	//@ApiOperation(value = "Retorna um Avaliador pelo CPF")
	@GetMapping(value="/findByCpf/{cpf}", produces="application/json")
	public ResponseEntity<Avaliador> findByCpf(@PathVariable("cpf") String cpf){
		
		Avaliador avaliador = service.findByCpf(cpf);
		
		if(avaliador == null){
			return ResponseEntity.notFound().build();		
		}
		
		Avaliador a = new Avaliador();
		
		a.setId(avaliador.getId());
		a.setNome(avaliador.getNome());
		a.setCpf(avaliador.getCpf());
		a.setSexo(avaliador.getSexo());
		a.setDataNascimento(avaliador.getDataNascimento());
		a.setFumante(avaliador.isFumante());

		return ResponseEntity.ok(a);
	}

}
