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

import br.edu.ifrn.projetosensoryweb.model.Amostra;
import br.edu.ifrn.projetosensoryweb.model.AnaliseSensorial;
import br.edu.ifrn.projetosensoryweb.model.AvaliacaoHedonica;
import br.edu.ifrn.projetosensoryweb.model.Avaliador;
import br.edu.ifrn.projetosensoryweb.model.Escala;
import br.edu.ifrn.projetosensoryweb.model.RespostaHedonica;
import br.edu.ifrn.projetosensoryweb.service.AmostraService;
import br.edu.ifrn.projetosensoryweb.service.AnaliseSensorialService;
import br.edu.ifrn.projetosensoryweb.service.AvaliacaoHedonicaService;
import br.edu.ifrn.projetosensoryweb.service.RespostaHedonicaService;

@RestController
@RequestMapping("/api/analisesensorial")
public class AnaliseSensorialResource {
	
	@Autowired
	private AnaliseSensorialService service;
	
	@Autowired
	private RespostaHedonicaService serviceresposta;
	
	@Autowired
	private AmostraService serviceamostra;
	
	@Autowired
	private AvaliacaoHedonicaService serviceavaliacao;
	
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
		
		if(id == null){
			return ResponseEntity.notFound().build();		
		}
		
		AnaliseSensorial analise = service.findByIdAnalise(id);
		
		if(analise == null){
			return ResponseEntity.notFound().build();		
		}

		return  ResponseEntity.ok(analise);
	}
	
	
	@GetMapping(value="/addResposta/{cpf}/{idanalise}/{codamostra}/{pergunta}/{resposta}")
	public ResponseEntity<RespostaHedonica> addResposta(@PathVariable("cpf") String cpf, @PathVariable("idanalise") Long id, 
			@PathVariable("codamostra") int codigoamostra, @PathVariable("pergunta") String pergunta,
			@PathVariable("resposta") String resposta){
		
		Amostra amostra = serviceamostra.findByCodigo(codigoamostra);
		
		if(amostra == null){
			return ResponseEntity.notFound().build();		
		}
		
		AvaliacaoHedonica avaliacao = serviceavaliacao.findByIdAnaliseAndPergunta(id, pergunta);
		
		if(avaliacao == null){
			return ResponseEntity.notFound().build();		
		}
		RespostaHedonica respostaHedonica = new RespostaHedonica();
		respostaHedonica.setAmostra(amostra);
		respostaHedonica.setResposta(resposta);
		respostaHedonica.setAvaliacaohedonica(avaliacao);
		
		serviceresposta.save(respostaHedonica);

		return  ResponseEntity.ok(respostaHedonica);
	}
	
	@GetMapping(value="/findByIdAvaliacao/{id}")
	public ResponseEntity<AvaliacaoHedonica> findByIdAvaliacao(@PathVariable("id") Long id){
		
		if(id == null){
			return ResponseEntity.notFound().build();		
		}
		
		AvaliacaoHedonica avaliacao = serviceavaliacao.findByIdAvaliacao(id);
		
		if(avaliacao == null){
			return ResponseEntity.notFound().build();		
		}

		return  ResponseEntity.ok(avaliacao);
	}

}
