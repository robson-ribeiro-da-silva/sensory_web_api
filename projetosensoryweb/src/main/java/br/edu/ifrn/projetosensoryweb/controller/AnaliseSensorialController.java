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
import br.edu.ifrn.projetosensoryweb.service.AnaliseSensorialService;
@Controller
@RequestMapping("/analisesensorial")
public class AnaliseSensorialController {
	
	@Autowired
	private AnaliseSensorialService service;
	
	
	@GetMapping("/add")
	public ModelAndView add(AnaliseSensorial analisesensorial) {
		
		ModelAndView mv = new ModelAndView("analisesensorial/form");
		mv.addObject("analisesensorial", analisesensorial);
		
		return mv;
	}
	
	@PostMapping("/save")
	public ModelAndView save(@Valid AnaliseSensorial analisesensorial, BindingResult result) {
		
		if(result.hasErrors()) {
			return add(analisesensorial);
		}
		
		service.save(analisesensorial);
		
		return findById(analisesensorial.getId());
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


}
