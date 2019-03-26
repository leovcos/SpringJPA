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
import br.gov.sp.fatec.view.View.UserShort;

@Entity
@Table(name = "users")
public class User implements UserDetails {

	private static final long serialVersionUID = 1507218635788384719L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	@JsonView({ UserComplete.class })
	private Integer id;

	@Column(unique = true, length = 20, nullable = false)
	@JsonView({ UserShort.class })
	private String username;

	@Column(length = 50, nullable = false)
	@JsonView({ UserShort.class })
	private String password;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "user_authorities", 
	joinColumns = { @JoinColumn(name = "user_id") }, 
	inverseJoinColumns = { @JoinColumn(name = "authority_id") })
	@JsonView({ UserComplete.class })
	private List<Authority> authorities;
	
	public void addAuthority(Authority authority) {
		this.authorities.add(authority);
	}

	public void setAuthorities(List<Authority> authorities) {
		this.authorities = authorities;
	}


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
		return this.authorities;
	}

	@Override
	@JsonIgnore
	public String getPassword() {
		return this.password;
	}

	@Override
	@JsonIgnore
	public String getUsername() {
		return this.username;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password= password;
	}
}
