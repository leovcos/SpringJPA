package br.gov.sp.fatec.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.gov.sp.fatec.model.User;

public interface UserRepository extends CrudRepository<User, Integer> {

	public User findByUsername(String name);
	
	public List<User> findByUsernameContainsIgnoreCase(String name);
	
	public User findTop1ByUsernameContains(String name);
	
	public List<User> findByIdGreaterThan(Integer id);
	
	@Query("select u from User u where u.username like %?1%")
	public List<User> searchUser(String name);
}
