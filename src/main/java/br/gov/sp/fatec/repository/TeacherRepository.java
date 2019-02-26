package br.gov.sp.fatec.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.gov.sp.fatec.model.Teacher;

public interface TeacherRepository extends CrudRepository<Teacher, Long> {

	public Teacher findByName(String name);

	@Query("select t from Teacher t where t.name like %?1%")
	public List<Teacher> findTeacher(String name);
}
