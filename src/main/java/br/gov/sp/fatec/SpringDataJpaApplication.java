package br.gov.sp.fatec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import br.gov.sp.fatec.service.ClassroomService;
import br.gov.sp.fatec.service.HeroService;

@SpringBootApplication
@EnableConfigurationProperties
public class SpringDataJpaApplication implements CommandLineRunner {
	
	@Autowired
	private HeroService heroService;
	

	@Autowired
	private ClassroomService classroomService;

	public static void main(String[] args) {
		SpringApplication.run(SpringDataJpaApplication.class, args);
	}
	
	public void setUsuarioService(HeroService heroService) {
		this.heroService = heroService;
	}

	@Override
	public void run(String... args) throws Exception {
		classroomService.addClassroom("Class A");
//		heroService.deleteHero("Teste JPA");
	}
}
