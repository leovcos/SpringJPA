package br.gov.sp.fatec.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.gov.sp.fatec.model.Classroom;

public interface ClassroomRepository extends CrudRepository<Classroom, Integer> {

	public Classroom findByName(String name);
	public Optional<Classroom> findById(Integer id);
	
	@Query("select c from Classroom c")
	public List<Classroom> findAllClassrooms();

}
