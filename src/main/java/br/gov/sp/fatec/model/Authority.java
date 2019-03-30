package br.gov.sp.fatec.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.annotation.JsonView;

import br.gov.sp.fatec.view.View.UserComplete;

@Entity
@Table(name = "authorities")
public class Authority implements GrantedAuthority {

	private static final long serialVersionUID = 3078636239920155012L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	@JsonView(UserComplete.class)
	private Long id;

	@Column(unique = true, length = 20, nullable = false)
	@JsonView(UserComplete.class)
	private String name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void setAuthority(String authority) {
		this.name = authority;
	}

	@Override
	public String getAuthority() {
		return this.name;
	}

}
