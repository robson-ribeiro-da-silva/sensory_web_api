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

import br.edu.ifrn.projetosensoryweb.model.Avaliador;
import br.edu.ifrn.projetosensoryweb.service.AvaliadorService;

@Controller
@RequestMapping("/avaliador")
public class AvaliadorController {
	
	@Autowired
	private AvaliadorService service;
	
	@GetMapping("/add")
	public ModelAndView add(Avaliador avaliador) {
		
		ModelAndView mv = new ModelAndView("avaliador/form");
		mv.addObject("avaliador", avaliador);
		
		return mv;
	}
	
	@PostMapping("/save")
	public ModelAndView save(@Valid Avaliador avaliador, BindingResult result) {
		
		if(result.hasErrors()) {
			return add(avaliador).addObject("error", "Erro ao tentar adiconar avaliador");
		}
		
		service.save(avaliador);
		
		return findAll().addObject("success", "Avaliador adicionado com sucesso!");
	}
	
	
	@GetMapping("/edit/{id}")
	public ModelAndView edit(@PathVariable("id") Long id) {
		
		return add(service.findOne(id));
	}
	
	@GetMapping("/delete/{id}")
	public ModelAndView delete(@PathVariable("id") Long id) {
		
		service.delete(id);
		
		return findAll().addObject("success", "Avaliador removido com sucesso!");
	}
	
	@GetMapping("/listar")
	public ModelAndView findAll() {
		
		ModelAndView mv = new ModelAndView("avaliador/lista");
		mv.addObject("avaliadores", service.findAll());
		
		return mv;
	}

}
