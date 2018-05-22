package com.ibm.service;

import java.util.List;
import java.util.Optional;

import com.ibm.entity.Student;

public interface IStudentService {

	Student addStudent(Student student);
	
	Optional<Student> getStudentById(Long id);
	
	Student update(Student student);
	
	 void delete(Long id);
	 List<Student> getAllStudents();
}
