package br.edu.ifrn.projetosensoryweb.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.edu.ifrn.projetosensoryweb.model.AnaliseSensorial;
import br.edu.ifrn.projetosensoryweb.model.Escala;
import br.edu.ifrn.projetosensoryweb.service.AnaliseSensorialService;
import br.edu.ifrn.projetosensoryweb.service.AvaliacaoHedonicaService;
import br.edu.ifrn.projetosensoryweb.service.EscalaService;
@Controller
@RequestMapping("/analisesensorial")
public class AnaliseSensorialController {
	
	@Autowired
	private AnaliseSensorialService service;
	
	@Autowired
	private EscalaService serviceescala;
	
	@Autowired
	private AvaliacaoHedonicaService serviceavaliacao;
	
	@GetMapping("/add")
	public ModelAndView add(AnaliseSensorial analisesensorial) {
		
		Escala escala = new Escala();
		analisesensorial.setEscala(escala);
		
		ModelAndView mv = new ModelAndView("analisesensorial/form");
		mv.addObject("analisesensorial", analisesensorial);
		mv.addObject("escala", escala);
		
		return mv;
	}
	
	@PostMapping("/save")
	public ModelAndView save(@Valid AnaliseSensorial analisesensorial, BindingResult result) {
		
		if(result.hasErrors()) {
			return add(analisesensorial);
		}
		
		Escala escala = analisesensorial.getEscala();
		serviceescala.save(escala);
		
		service.save(analisesensorial);
		
		if(analisesensorial.getEscala().getNome().equals("Hedonica")){
			escala.setNome("Hedonica");
			serviceescala.save(escala);
			return addEscalaAvaliacaoHedonica(analisesensorial.getEscala().getId());
		}else{
			return findById(analisesensorial.getId());
		}
		
		
	}
	
	@GetMapping("/listar")
	public ModelAndView findAll() {
		
		ModelAndView mv = new ModelAndView("analisesensorial/lista");
		mv.addObject("analisesensoriais", service.findAll());
		
		return mv;
	}
	
	@GetMapping("/details/{id}")
	public ModelAndView findById(@PathVariable("id") Long id) {
		
		ModelAndView mv = new ModelAndView("analisesensorial/details");
		mv.addObject("analisesensorial", service.findOne(id));
		
		return mv;
	}
	
	@GetMapping("/edit/{id}")
	public ModelAndView edit(@PathVariable("id") Long id) {
		
		return add(service.findOne(id));
	}
	
	@GetMapping("/delete/{id}")
	public ModelAndView delete(@PathVariable("id") Long id) {
		
		service.delete(id);
		
		return findAll();
	}
	
	@GetMapping("/addEscalaAvaliacaoHedonica")
	public ModelAndView addEscalaAvaliacaoHedonica(@PathVariable("id") Long id) {
		
		ModelAndView mv = new ModelAndView("escalaavaliacaohedonica/form");
		mv.addObject("escala", serviceescala.findOne(id));
		mv.addObject("avaliacoes", serviceavaliacao.findAll());
		
		return mv;
	}
	
	@PostMapping("/saveescala")
	public ModelAndView saveescala(@Valid Escala escala, BindingResult result) {
		
		if(result.hasErrors()) {
			return addEscalaAvaliacaoHedonica(escala.getId());
		}
		
		
		serviceescala.save(escala);
		
		AnaliseSensorial analise = service.findByIdEscala(escala.getId());
		
		return findById(analise.getId());
		
		
		
	}


}
