package br.gov.sp.fatec.repository;

import org.springframework.data.repository.CrudRepository;

import br.gov.sp.fatec.model.Classroom;

public interface ClassroomRepository extends CrudRepository<Classroom, Long> {

}
