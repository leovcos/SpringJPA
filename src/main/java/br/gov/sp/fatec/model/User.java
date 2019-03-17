package br.gov.sp.fatec.model;

import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

import br.gov.sp.fatec.view.View.UserComplete;

@Entity
@Table(name = "user")
public class User implements UserDetails {

	private static final long serialVersionUID = 1507218635788384719L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	@JsonView({ UserComplete.class })
	private Long id;

	@Column(unique = true, length = 20, nullable = false)
	@JsonView({ UserComplete.class })
	private String name;

	@Column(length = 50, nullable = false)
	@JsonView({ UserComplete.class })
	private String pass;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = {
			@JoinColumn(name = "authority_id") })
	@JsonView({ UserComplete.class })
	@XmlElement(name = "autorizacao")
	private List<Authority> authority;

	@Override
	@JsonIgnore
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	@JsonIgnore
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	@JsonIgnore
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	@JsonIgnore
	public boolean isEnabled() {
		return true;
	}

	@Override
	@JsonIgnore
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authority;
	}

	@Override
	@JsonIgnore
	public String getPassword() {
		return this.pass;
	}

	@Override
	@JsonIgnore
	public String getUsername() {
		return this.name;
	}

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

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public List<Authority> getAuthority() {
		return authority;
	}

	public void setAuthority(List<Authority> authority) {
		this.authority = authority;
	}

}
