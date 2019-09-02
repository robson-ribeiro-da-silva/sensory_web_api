package br.edu.ifrn.projetosensoryweb;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ProjetosensorywebApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjetosensorywebApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner commandLineRunner() {
		return agrs -> {
			System.out.println("**** PROJETO SENSORYIFRN-WEB *****");
			System.out.println("Autor: Robson Ribeiro da Silva ");
		};
		
	}
}
