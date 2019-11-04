package br.edu.ifrn.projetosensoryweb.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.edu.ifrn.projetosensoryweb.model.AnaliseSensorial;
import br.edu.ifrn.projetosensoryweb.model.Escala;
import br.edu.ifrn.projetosensoryweb.model.Usuario;
import br.edu.ifrn.projetosensoryweb.service.AnaliseSensorialService;
import br.edu.ifrn.projetosensoryweb.service.AvaliacaoHedonicaService;
import br.edu.ifrn.projetosensoryweb.service.EscalaService;
import br.edu.ifrn.projetosensoryweb.service.UsuarioService;
@Controller
@RequestMapping("/analisesensorial")
public class AnaliseSensorialController {
	
	@Autowired
	private AnaliseSensorialService service;
	
	@Autowired
	private UsuarioService serviceusuario;
	
	@Autowired
	private EscalaService serviceescala;
	
	@Autowired
	private AvaliacaoHedonicaService serviceavaliacao;
	
	public Usuario usuario;
	
	@GetMapping("/add")
	public ModelAndView add(AnaliseSensorial analisesensorial, @AuthenticationPrincipal UserDetails userDetails) {
		
		String user = userDetails.getUsername();
		//System.out.println("usuario  ---->>> "+user);
		usuario = serviceusuario.findByUsername(user);
		
		Escala escala = new Escala();
		analisesensorial.setEscala(escala);
		analisesensorial.setUsuario(usuario);
		
		ModelAndView mv = new ModelAndView("analisesensorial/form");
		mv.addObject("analisesensorial", analisesensorial);
		mv.addObject("escala", escala);
		mv.addObject("usuario", usuario);
		
		return mv;
	}
	
	@PostMapping("/save")
	public ModelAndView save(@Valid AnaliseSensorial analisesensorial, BindingResult result) {
		
		if(result.hasErrors()) {
			return add(analisesensorial, usuario);
		}
		
		Escala escala = analisesensorial.getEscala();
		serviceescala.save(escala);
		
		service.save(analisesensorial);
		
		if(escala.getNome().equals("Hedonica")){
			escala.setNome("Hedonica");
			serviceescala.save(escala);
			//System.out.println("------ > entrou");
			return addEscalaAvaliacaoHedonica(analisesensorial.getEscala().getId());
		}else{
			//System.out.println("------ > não entrou");
			return findById(analisesensorial.getId());
		}
		
		
	}
	
	@GetMapping("/listar")
	public ModelAndView findAll(@AuthenticationPrincipal UserDetails userDetails) {
		
		String user = userDetails.getUsername();
		
		Usuario usuario = serviceusuario.findByUsername(user);
		
		
		ModelAndView mv = new ModelAndView("analisesensorial/lista");
		mv.addObject("analisesensoriais", service.findByIdUsuario(usuario.getId()));
		
		return mv;
	}
	
	@GetMapping("/details/{id}")
	public ModelAndView findById(@PathVariable("id") Long id) {
		
		ModelAndView mv = new ModelAndView("analisesensorial/details");
		mv.addObject("analisesensorial", service.findOne(id));
		
		return mv;
	}
	
	@GetMapping("/detailsamostras/{id}")
	public ModelAndView findByIdAnalise(@PathVariable("id") Long id) {
		
		ModelAndView mv = new ModelAndView("analisesensorial/detailsamostras");
		mv.addObject("analisesensorial", service.findOne(id));
		
		return mv;
	}
	
	@GetMapping("/respostasanalise/{id}")
	public ModelAndView findByRespostasAnalise(@PathVariable("id") Long id) {
		
		ModelAndView mv = new ModelAndView("analisesensorial/respostas");
		mv.addObject("analisesensorial", service.findOne(id));
		
		return mv;
	}
	
	@GetMapping("/edit/{id}")
	public ModelAndView edit(@PathVariable("id") Long id) {
		
		return add(service.findOne(id), usuario);
	}
	
	@GetMapping("/delete/{id}")
	public ModelAndView delete(@PathVariable("id") Long id, @AuthenticationPrincipal UserDetails userDetails) {
		
		String user = userDetails.getUsername();
		
		Usuario usuario = serviceusuario.findByUsername(user);
		
		service.delete(id);
		
		return findAll(usuario);
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
