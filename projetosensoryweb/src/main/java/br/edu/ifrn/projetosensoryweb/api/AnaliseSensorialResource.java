package br.edu.ifrn.projetosensoryweb.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.edu.ifrn.projetosensoryweb.model.AnaliseSensorial;
import br.edu.ifrn.projetosensoryweb.model.AvaliacaoHedonica;
import br.edu.ifrn.projetosensoryweb.model.Avaliador;
import br.edu.ifrn.projetosensoryweb.model.Escala;
import br.edu.ifrn.projetosensoryweb.model.RespostaHedonica;
import br.edu.ifrn.projetosensoryweb.service.AnaliseSensorialService;
import br.edu.ifrn.projetosensoryweb.service.RespostaHedonicaService;

@RestController
@RequestMapping("/api/analisesensorial")
public class AnaliseSensorialResource {
	
	@Autowired
	private AnaliseSensorialService service;
	
	@Autowired
	private RespostaHedonicaService serviceresposta;
	
	@GetMapping(value="/findAll")
	public ResponseEntity<List<AnaliseSensorial>> findAll(){
		
		List<AnaliseSensorial> analises = service.findAll();
		
		if(analises.isEmpty()){
			return ResponseEntity.notFound().build();		
		}

		return  new ResponseEntity<>(analises, HttpStatus.OK);
	}
	
	@GetMapping(value="/findById/{id}")
	public ResponseEntity<AnaliseSensorial> findById(@PathVariable("id") Long id){
		
		AnaliseSensorial analise = service.findOne(id);
		
		//System.out.println("analise ----->>>  "+analise.getDescricao());
		
		if(analise == null){
			return ResponseEntity.notFound().build();		
		}

		return  ResponseEntity.ok(analise);
	}
	
	
	@PostMapping(value="/addResposta/{cpf}/{idanalise}/{codamostra}/{pergunta}/{resposta}")
	public ResponseEntity<RespostaHedonica> addResposta(@PathVariable("cpf") String cpf, @PathVariable("idanalise") Long id, 
			@PathVariable("codamostra") int codigoamostra, @PathVariable("pergunta") String pergunta,
			@PathVariable("resposta") String resposta){
		
		AnaliseSensorial analise = service.findOne(id);
		Escala escala = analise.getEscala();
		//AvaliacaoHedonica avaliacaoHedonica = (AvaliacaoHedonica) escala.getAvaliacaohedonica();
		//RespostaHedonica resposta = avaliacaoHedonica.getRespostahedonica();
		

		return ResponseEntity.ok(null);
	}

}
