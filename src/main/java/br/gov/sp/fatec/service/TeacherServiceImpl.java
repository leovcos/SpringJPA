package br.gov.sp.fatec.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.sp.fatec.model.Teacher;
import br.gov.sp.fatec.repository.TeacherRepository;

@Service("teacherService")
public class TeacherServiceImpl implements TeacherService {

	public void setTeacherRepo(TeacherRepository teacherRepo) {
	}

	@Override
	@Transactional
	public Teacher addTeacher(String name) {
		// TODO Auto-generated method stub
		return null;
	}
}
