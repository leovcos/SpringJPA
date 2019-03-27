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
import br.gov.sp.fatec.security.Login;
import br.gov.sp.fatec.service.HeroService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringDataJpaApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class SpringRestApplicationAuthTests {

	@LocalServerPort
	private int port;
	
	@Autowired
	private HeroService heroService;
	
	TestRestTemplate restTemplate = new TestRestTemplate();
	
	public void setHeroService(HeroService heroService) {
		this.heroService = heroService;
	}

	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}
	
	@Test
	public void givenAuth_whenPostAuthRegister_thenStatus200() throws JSONException {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		
		HttpEntity<String> entity = new HttpEntity<String>("{\"username\": \"filipe\", \"password\": \"123456\"}", headers);

		ResponseEntity<String> response = restTemplate.exchange(
				createURLWithPort("/auth/register"),
				HttpMethod.POST,
				entity,
				String.class
		);

		String expected = "{\"username\":\"filipe\"}";
		System.out.println(response.getBody());
		JSONAssert.assertEquals(expected, response.getBody(), true);
	}
	
	@Test
	public void givenAuth_whenPostAuthLogin_thenStatus200() throws JSONException {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		
		HttpEntity<String> entity = new HttpEntity<String>("{\"username\": \"filipe\", \"password\": \"123456\"}", headers);

		ResponseEntity<String> response = restTemplate.exchange(
				createURLWithPort("/auth/login"),
				HttpMethod.POST,
				entity,
				String.class
		);

		String expected = "{\"username\":\"filipe\"}";
		System.out.println(response.getBody());
		JSONAssert.assertEquals(expected, response.getBody(), true);
	}
}
