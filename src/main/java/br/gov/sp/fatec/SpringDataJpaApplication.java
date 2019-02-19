package br.gov.sp.fatec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.gov.sp.fatec.service.UsuarioService;

@SpringBootApplication
public class SpringDataJpaApplication implements CommandLineRunner {
	
	@Autowired
	private UsuarioService usuarioService;

	public static void main(String[] args) {
		SpringApplication.run(SpringDataJpaApplication.class, args);
	}
	
	public void setUsuarioService(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	@Override
	public void run(String... args) throws Exception {
		usuarioService.incluirUsuario("Teste JPA", "alguma senha", "Autorização nenhuma");
		usuarioService.excluirUsuario("Teste JPA");
	}
}
