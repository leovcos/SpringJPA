package br.gov.sp.fatec.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gov.sp.fatec.model.Classroom;
import br.gov.sp.fatec.repository.ClassroomRepository;


@Service("classroomService")
public class ClassroomServiceImpl implements ClassroomService {

	@Autowired
	private ClassroomRepository classroomRepo;
	
	@Override
	public Classroom addClassroom(String name) {
		Classroom c = new Classroom();
		c.setName(name);
		classroomRepo.save(c);
		return c;
	}

}
