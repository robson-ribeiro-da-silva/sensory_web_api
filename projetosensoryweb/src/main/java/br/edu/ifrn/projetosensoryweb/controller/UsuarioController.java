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

import br.edu.ifrn.projetosensoryweb.model.Role;
import br.edu.ifrn.projetosensoryweb.model.Usuario;
import br.edu.ifrn.projetosensoryweb.service.RoleService;
import br.edu.ifrn.projetosensoryweb.service.UsuarioService;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
	
	@Autowired
	private UsuarioService service;
	
	@Autowired
	private RoleService servicerole;
	
	@GetMapping("/add")
	public ModelAndView add(Usuario usuario) {
		
		ModelAndView mv = new ModelAndView("usuario/form");
		mv.addObject("usuario", usuario);
		mv.addObject("role", servicerole.findByUsername("ADM"));
		
		return mv;
	}
	
	@PostMapping("/save")
	public ModelAndView save(@Valid Usuario usuario, BindingResult result) {
		
		if(result.hasErrors()) {
			return add(usuario).addObject("error", "Erro ao tentar adiconar usuário");
		}
		
		Usuario u = service.findByUsername(usuario.getUsername());
		
		if(u != null){
			return add(usuario).addObject("error", "Esse nome de usuário já existe! tente outro por favor");
		}
		
		
		Role role = servicerole.findByUsername("COR");
		usuario.getRole().add(role);
		
		String senha = usuario.getPassword();
		usuario.setPassword(new BCryptPasswordEncoder().encode(senha));
		
		service.save(usuario);
		
		return findAll().addObject("success", "Usuário adicionado com sucesso!");
	}
	
	@GetMapping("/listar")
	public ModelAndView findAll() {
		
		ModelAndView mv = new ModelAndView("usuario/lista");
		mv.addObject("usuarios", service.findAll());
		
		return mv;
	}
	
	@GetMapping("/edit/{id}")
	public ModelAndView edit(@PathVariable("id") Long id) {
		
		return add(service.findOne(id));
	}
	
	@GetMapping("/delete/{id}")
	public ModelAndView delete(@PathVariable("id") Long id) {
		
		service.delete(id);
		
		return findAll().addObject("success", "Usuário removido com sucesso!");
	}

}
