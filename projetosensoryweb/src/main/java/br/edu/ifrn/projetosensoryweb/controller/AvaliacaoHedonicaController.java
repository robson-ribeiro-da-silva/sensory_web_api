package br.edu.ifrn.projetosensoryweb.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.edu.ifrn.projetosensoryweb.model.AvaliacaoHedonica;
import br.edu.ifrn.projetosensoryweb.model.StatusAvaliacaoHedonica;
import br.edu.ifrn.projetosensoryweb.service.AvaliacaoHedonicaService;

@Controller
@RequestMapping("/avaliacaohedonica")
public class AvaliacaoHedonicaController {
	
	@Autowired
	private AvaliacaoHedonicaService service;
	
	@GetMapping("/add")
	public ModelAndView add(AvaliacaoHedonica avaliacaohedonica) {
		
		ModelAndView mv = new ModelAndView("avaliacaohedonica/form");
		mv.addObject("avaliacaohedonica", avaliacaohedonica);
		mv.addObject("avaliacaohedonicas", service.findByStatus(StatusAvaliacaoHedonica.ATIVA));
		
		return mv;
	}
	
	@PostMapping("/save")
	public ModelAndView save(@Valid AvaliacaoHedonica avaliacaohedonica, BindingResult result) {
		
		if(result.hasErrors()) {
			return add(avaliacaohedonica);
		}
		
		avaliacaohedonica.setStatus(StatusAvaliacaoHedonica.ATIVA);
		
		service.save(avaliacaohedonica);
		
		AvaliacaoHedonica avaliacaohedonica1 = new AvaliacaoHedonica();
		
		return add(avaliacaohedonica1);
	}
	
	@GetMapping("/listar")
	public ModelAndView findAll() {
		
		ModelAndView mv = new ModelAndView("avaliacaohedonica/lista");
		mv.addObject("avaliacaohedonicas", service.findAll());
		
		return mv;
	}
	
	@GetMapping("/edit/{id}")
	public ModelAndView edit(@PathVariable("id") Long id) {
		
		return add(service.findOne(id));
	}
	
	@GetMapping("/delete/{id}")
	public ModelAndView delete(@PathVariable("id") Long id) {
		
		AvaliacaoHedonica avaliacaohedonica = service.findByIdAvaliacao(id);
		
		avaliacaohedonica.setStatus(StatusAvaliacaoHedonica.INATIVA);
		
		service.save(avaliacaohedonica);
		
		AvaliacaoHedonica avaliacaohedonica1 = new AvaliacaoHedonica();
		
		return add(avaliacaohedonica1);
	}

}
