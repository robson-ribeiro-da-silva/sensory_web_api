package br.edu.ifrn.projetosensoryweb.jobs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import br.edu.ifrn.projetosensoryweb.model.Role;
import br.edu.ifrn.projetosensoryweb.model.TipoUsuario;
import br.edu.ifrn.projetosensoryweb.model.Usuario;
import br.edu.ifrn.projetosensoryweb.service.RoleService;
import br.edu.ifrn.projetosensoryweb.service.UsuarioService;


@Component
public class Inicializador implements ApplicationListener<ContextRefreshedEvent>{
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private UsuarioService userService;

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
			
			userService.save(admin);
		}
	}
}
