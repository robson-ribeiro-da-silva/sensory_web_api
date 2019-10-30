package br.edu.ifrn.projetosensoryweb.jobs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import br.edu.ifrn.projetosensoryweb.model.AvaliacaoHedonica;
import br.edu.ifrn.projetosensoryweb.model.Escala;
import br.edu.ifrn.projetosensoryweb.model.Role;
import br.edu.ifrn.projetosensoryweb.model.TipoUsuario;
import br.edu.ifrn.projetosensoryweb.model.Usuario;
import br.edu.ifrn.projetosensoryweb.service.AvaliacaoHedonicaService;
import br.edu.ifrn.projetosensoryweb.service.EscalaService;
import br.edu.ifrn.projetosensoryweb.service.RoleService;
import br.edu.ifrn.projetosensoryweb.service.UsuarioService;


@Component
public class Inicializador implements ApplicationListener<ContextRefreshedEvent>{
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private UsuarioService userService;
	
	
	@Autowired
	private AvaliacaoHedonicaService serviceavaliacao;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		
		String username = "admin";
		Usuario user = userService.findByUsername(username);
		
		if(user == null){
		
			Role roleA = new Role();
			roleA.setNome("ADM");
			roleService.save(roleA);
			
			Role roleC = new Role();
			roleC.setNome("COR");
			roleService.save(roleC);
			
			Usuario admin = new Usuario();
			admin.setNome("admin");
			admin.setCpf("11111111111");
			admin.setSexo("Masculino");
			admin.setTipousuario(TipoUsuario.ADMINISTRADOR);
			admin.setDataNascimento("1111-11-11");
			admin.setEmail("robsonrds72@gmail.com");
			admin.setUsername("admin");
			admin.setPassword(new BCryptPasswordEncoder().encode("12345"));
			
			Role roleadm = roleService.findByUsername("ADM");
			admin.getRole().add(roleadm);
			
			Role rolecor = roleService.findByUsername("COR");
			admin.getRole().add(rolecor);
			
			userService.save(admin);
			
			AvaliacaoHedonica a1 = new AvaliacaoHedonica();
			a1.setPergunta("COR");
			
			serviceavaliacao.save(a1);
			
			AvaliacaoHedonica a2 = new AvaliacaoHedonica();
			a2.setPergunta("SABOR");
			
			serviceavaliacao.save(a2);
			
			AvaliacaoHedonica a3 = new AvaliacaoHedonica();
			a3.setPergunta("TEXTURA");
			
			serviceavaliacao.save(a3);
			
			AvaliacaoHedonica a4 = new AvaliacaoHedonica();
			a4.setPergunta("APARENCIA");
			
			serviceavaliacao.save(a4);
			
			AvaliacaoHedonica a5 = new AvaliacaoHedonica();
			a5.setPergunta("CHEIRO");
			
			serviceavaliacao.save(a5);

		}
	}
}
