package br.edu.ifrn.projetosensoryweb.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.edu.ifrn.projetosensoryweb.model.Amostra;
import br.edu.ifrn.projetosensoryweb.model.AnaliseSensorial;
import br.edu.ifrn.projetosensoryweb.model.AvaliacaoHedonica;
import br.edu.ifrn.projetosensoryweb.model.Avaliador;
import br.edu.ifrn.projetosensoryweb.model.Escala;
import br.edu.ifrn.projetosensoryweb.model.Produto;
import br.edu.ifrn.projetosensoryweb.model.RespostaHedonica;
import br.edu.ifrn.projetosensoryweb.model.StatusAnalise;
import br.edu.ifrn.projetosensoryweb.service.AmostraService;
import br.edu.ifrn.projetosensoryweb.service.AnaliseSensorialService;
import br.edu.ifrn.projetosensoryweb.service.AvaliacaoHedonicaService;
import br.edu.ifrn.projetosensoryweb.service.AvaliadorService;
import br.edu.ifrn.projetosensoryweb.service.ProdutoService;
import br.edu.ifrn.projetosensoryweb.service.RespostaHedonicaService;
//import io.swagger.annotations.ApiOperation;

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

	@Autowired
	private AvaliadorService serviceavaliador;

	@Autowired
	private ProdutoService serviceproduto;
	
	//@ApiOperation(value = "Retorna uma lista de Análises")
	@GetMapping(value = "/findAll", produces="application/json")
	public ResponseEntity<List<AnaliseSensorial>> findAll() {

		List<AnaliseSensorial> analises = service.findByStatus(StatusAnalise.DISPONIVEL);

		if (analises.isEmpty()) {
			return ResponseEntity.notFound().build();
		}

		return new ResponseEntity<>(analises, HttpStatus.OK);
	}

	//@ApiOperation(value = "Retorna uma Análise pelo ID")
	@GetMapping(value = "/findById/{id}", produces="application/json")
	public ResponseEntity<AnaliseSensorial> findById(@PathVariable("id") Long id) {

		if (id == null) {
			return ResponseEntity.notFound().build();
		}

		AnaliseSensorial analise = service.findByIdAnalise(id);

		if (analise == null) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(analise);
	}

	//@ApiOperation(value = "Adiciona a resposta do avaliador")
	@GetMapping(value = "/addResposta/{cpf}/{idanalise}/{codamostra}/{pergunta}/{resposta}", produces="application/json")
	public ResponseEntity<RespostaHedonica> addResposta(@PathVariable("cpf") String cpf,
			@PathVariable("idanalise") Long id, @PathVariable("codamostra") int codigoamostra,
			@PathVariable("pergunta") String pergunta, @PathVariable("resposta") String resposta) {
		/*
		 * if(cpf == null || cpf == " "){ return
		 * ResponseEntity.notFound().build(); }
		 * 
		 * Avaliador avaliador = serviceavaliador.findByCpf(cpf);
		 * 
		 * if(avaliador == null){ return ResponseEntity.notFound().build(); }
		 */
		
		Amostra amostraok = null;
		
		int idpro = id.intValue();
		
		//System.out.println("aqui em cima "+idpro);
		List<Produto> produtos = serviceproduto.findByCodigoAnalise(service.findByIdAnalise(id));
		//System.out.println("aqui em baixo "+idpro);
		for(Produto p: produtos){
			for(Amostra a: p.getAmostras()){
				
				if(a.getCodigo() == codigoamostra){
					amostraok = a;
				}
			}
		}
		//System.out.println("aqui");
		int total = 0;
		//Amostra amostra = serviceamostra.findByIdAnaliseAndCodAmostra(id, codigoamostra);

		if (amostraok == null) {
			return ResponseEntity.notFound().build();
		}

		List<RespostaHedonica> respostas = amostraok.getRespostahedonica();

		if (respostas != null) {
			for (int i = 0; i < respostas.size(); i++) {
				// System.out.println("aqui --- >");
				if (respostas.get(i).getAvaliacaohedonica().getPergunta().equals(pergunta)) {
					// System.out.println("Pergunta- ---- >" +
					// respostas.get(i).getAvaliacaohedonica().getPergunta());
					return ResponseEntity.notFound().build();
				}
			}
		}

		AvaliacaoHedonica avaliacao = serviceavaliacao.findByIdAnaliseAndPergunta(id, pergunta);

		if (avaliacao == null) {
			return ResponseEntity.notFound().build();
		}
		RespostaHedonica respostaHedonica = new RespostaHedonica();
		respostaHedonica.setAmostra(amostraok);
		respostaHedonica.setResposta(resposta);
		respostaHedonica.setAvaliacaohedonica(avaliacao);

		serviceresposta.save(respostaHedonica);

		AnaliseSensorial analise = service.findByIdAnalise(id);

		if (analise.getPerguntaatual() == null) {
			analise.setPerguntaatual(pergunta);
		}

		if (analise.getPerguntaatual().equals(pergunta)) {

			total = analise.getQtdAmostrasDisponiveis() - 1;
			analise.setQtdAmostrasDisponiveis(total);

			if (total == 0) {
				analise.setStatus(StatusAnalise.ENCERRADA);
			}

			service.save(analise);
		}

		return ResponseEntity.ok(respostaHedonica);
	}

	/*
	@GetMapping(value = "/findByIdAvaliacao/{id}", produces="application/json")
	public ResponseEntity<AvaliacaoHedonica> findByIdAvaliacao(@PathVariable("id") Long id) {

		if (id == null) {
			return ResponseEntity.notFound().build();
		}

		AvaliacaoHedonica avaliacao = serviceavaliacao.findByIdAvaliacao(id);

		if (avaliacao == null) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(avaliacao);
	}
	*/

}
