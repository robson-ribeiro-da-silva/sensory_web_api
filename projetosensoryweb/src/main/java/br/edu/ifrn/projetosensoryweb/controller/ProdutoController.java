package br.edu.ifrn.projetosensoryweb.controller;

import java.util.Random;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.edu.ifrn.projetosensoryweb.model.Amostra;
import br.edu.ifrn.projetosensoryweb.model.AnaliseSensorial;
import br.edu.ifrn.projetosensoryweb.model.Produto;
import br.edu.ifrn.projetosensoryweb.service.AmostraService;
import br.edu.ifrn.projetosensoryweb.service.AnaliseSensorialService;
import br.edu.ifrn.projetosensoryweb.service.ProdutoService;


@Controller
@RequestMapping("/produto")
public class ProdutoController {
	
	@Autowired
	private ProdutoService service;
	
	@Autowired
	private AnaliseSensorialService serviceanalise;
	
	@Autowired
	private AmostraService serviceamostra;
	
	@GetMapping("/add/{id}")
	public ModelAndView add(@PathVariable("id") Long id, Produto produto) {
		
		produto = new Produto();
		
		produto.setAnalisesensorial(serviceanalise.findOne(id));
		
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
		AnaliseSensorial analise = produto.getAnalisesensorial();
		
		int totalProdutos = analise.getTotalProdutos();
		
		analise.setTotalProdutos(totalProdutos+1);
		
		serviceanalise.save(analise);
		
		
		int qtd = produto.getAnalisesensorial().getQtdAmostras();
		int n = 0;
		for(int i = 0; i < qtd; i++){
			n = aleatorios(100, 999, totalProdutos+1);
			
			Amostra amostra = new Amostra();
			amostra.setCodigo(n);
			amostra.setProduto(produto);
			serviceamostra.save(amostra);
			
			System.out.println("Numero "+ i +"° = " +n);
		}
		
		service.save(produto);
		//serviceanalise.save(analise);
		
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
		
		AnaliseSensorial analise = produto.getAnalisesensorial();
		
		int totalProdutos = analise.getTotalProdutos();
		
		analise.setTotalProdutos(totalProdutos-1);
		
		serviceanalise.save(analise);
		
		return editadd(produto.getAnalisesensorial().getId(), produto);
	}
	
	@GetMapping("/editadd/{id}")
	public ModelAndView editadd(@PathVariable("id") Long id, Produto produto) {
		
		produto.setAnalisesensorial(serviceanalise.findOne(id));
		
		ModelAndView mv = new ModelAndView("produto/formedit");
		mv.addObject("produto", produto);
		mv.addObject("analisesensorial", serviceanalise.findOne(id));
		
		return mv;
	}
	
	@PostMapping("/saveedit")
	public ModelAndView saveedit(@Valid Produto produto, BindingResult result) {
		
//		if(result.hasErrors()) {
//			return add(analisesensorial);
//		}
		
		service.save(produto);
		
		
		return findAnaliseById(produto.getAnalisesensorial().getId());
	}
	
	@GetMapping("/delete/{id}")
	public ModelAndView delete(@PathVariable("id") Long id) {
		
		
		Produto produto = service.findOne(id);
		
//		AnaliseSensorial analise = produto.getAnalisesensorial();
//		
//		int totalProdutos = analise.getTotalProdutos();
//		
//		analise.setTotalProdutos(totalProdutos-1);
//		
//		serviceanalise.save(analise);
		
		Long idanalise = produto.getAnalisesensorial().getId();
		
		for(int i = 0; i<produto.getAmostras().size(); i++){
			Amostra a = produto.getAmostras().get(i);
			serviceamostra.delete(a.getId());			
		}
		//service.delete(id);
		
		return findAnaliseById(idanalise);
	}

	public static int aleatorios(int minimo, int maximo, int codigo) {
		int rand = 0;
        Random random = new Random();
        rand = random.nextInt((maximo - minimo) + 1) + minimo;
        return codigo * 1000 + rand;
    }
}
