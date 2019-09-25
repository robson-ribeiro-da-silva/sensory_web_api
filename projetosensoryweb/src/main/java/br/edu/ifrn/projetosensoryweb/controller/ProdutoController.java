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

import br.edu.ifrn.projetosensoryweb.model.Produto;
import br.edu.ifrn.projetosensoryweb.service.AnaliseSensorialService;
import br.edu.ifrn.projetosensoryweb.service.ProdutoService;


@Controller
@RequestMapping("/produto")
public class ProdutoController {
	
	@Autowired
	private ProdutoService service;
	
	@Autowired
	private AnaliseSensorialService serviceanalise;
	
	
	@GetMapping("/add/{id}")
	public ModelAndView add(@PathVariable("id") Long id, Produto produto) {
		
		ModelAndView mv = new ModelAndView("produto/form");
		mv.addObject("produto", produto);
		mv.addObject("analisesensorial", serviceanalise.findOne(id));
		
		return mv;
	}
	
	@PostMapping("/save")
	public ModelAndView save(@Valid Produto produto, BindingResult result) {
		
//		if(result.hasErrors()) {
//			return add(analisesensorial);
//		}
		
		service.save(produto);
		
		return findAnaliseById(produto.getAnalisesensorial().getId());
	}
	
	@GetMapping("/listar")
	public ModelAndView findAll() {
		
		ModelAndView mv = new ModelAndView("produto/lista");
		mv.addObject("produtos", service.findAll());
		
		return mv;
	}
	
	@GetMapping("/details/{id}")
	public ModelAndView findAnaliseById(@PathVariable("id") Long id) {
		
		ModelAndView mv = new ModelAndView("analisesensorial/details");
		mv.addObject("analisesensorial", serviceanalise.findOne(id));
		
		return mv;
	}
	
	@GetMapping("/edit/{id}")
	public ModelAndView edit(@PathVariable("id") Long id) {
		
		Produto produto = service.findOne(id);
		
		return add(produto.getAnalisesensorial().getId(), produto);
	}
	
	@GetMapping("/delete/{id}")
	public ModelAndView delete(@PathVariable("id") Long id) {
		
		Produto produto = service.findOne(id);
		
		service.delete(id);
		
		return findAnaliseById(produto.getAnalisesensorial().getId());
	}

}
