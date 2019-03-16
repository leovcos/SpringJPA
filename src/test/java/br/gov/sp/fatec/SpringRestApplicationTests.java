package br.gov.sp.fatec;

import java.util.Collections;

import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import br.gov.sp.fatec.model.Hero;
import br.gov.sp.fatec.repository.HeroRepository;
import br.gov.sp.fatec.service.HeroService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringDataJpaApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class SpringRestApplicationTests {

	@LocalServerPort
	private int port;
	
	@Autowired
	private HeroService heroService;
	
	TestRestTemplate restTemplate = new TestRestTemplate();
	
	public void setHeroService(HeroService heroService) {
		this.heroService = heroService;
	}
	
	@Before
	public void createTestHero() {
		Hero hero = heroService.addHero("Midoriya", "1-A", "One for All");
		System.out.println(hero.getName());
	}

	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}
	
	@Test
	public void givenHeros_whenGetHerosList_thenStatus200() throws JSONException {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(
				createURLWithPort("/hero/list"),
				HttpMethod.GET,
				entity,
				String.class
		);

		String expected = "{\"content\":[{\"id\":1,\"name\":\"Midoriya\",\"image\":null,\"quirk\":{\"id\":1,\"name\":\"One for All\"},\"classroom\":{\"id\":1,\"name\":\"1-A\"}}],\"pageable\":{\"sort\":{\"sorted\":false,\"unsorted\":true},\"pageSize\":20,\"pageNumber\":0,\"offset\":0,\"paged\":true,\"unpaged\":false},\"last\":true,\"totalPages\":1,\"totalElements\":1,\"first\":true,\"sort\":{\"sorted\":false,\"unsorted\":true},\"numberOfElements\":1,\"size\":20,\"number\":0}";

		JSONAssert.assertEquals(expected, response.getBody(), false);
	}
}
