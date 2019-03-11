package br.gov.sp.fatec.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.gov.sp.fatec.model.Classroom;
import br.gov.sp.fatec.model.Quirk;

public interface ClassroomRepository extends CrudRepository<Classroom, Long> {

	public Classroom findByName(String name);
	
	@Query("select c from Classroom c")
	public List<Classroom> findAllClassrooms();

}
