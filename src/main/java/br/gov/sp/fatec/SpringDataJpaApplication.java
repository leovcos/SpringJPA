package br.gov.sp.fatec;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import br.gov.sp.fatec.model.Classroom;
import br.gov.sp.fatec.repository.ClassroomRepository;
import br.gov.sp.fatec.service.ClassroomService;
import br.gov.sp.fatec.service.HeroService;

@SpringBootApplication
@EnableConfigurationProperties
public class SpringDataJpaApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringDataJpaApplication.class, args);
	}
}
