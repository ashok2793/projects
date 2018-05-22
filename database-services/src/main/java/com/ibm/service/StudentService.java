package com.ibm.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.entity.Student;
import com.ibm.repository.StudentRepository;

@Service
public class StudentService implements IStudentService {


	@Autowired
	private StudentRepository studentRepository;

	

	public StudentService(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}



	@Override
	public Student addStudent(Student student) {
		Student s = studentRepository.save(student);
		return s;
	}



	@Override
	public Optional<Student> getStudentById(Long id) {
		Optional<Student> s = studentRepository.findById(id);
		return s;
	}


	@Override
	public Student update(Student student) {
		Student s= studentRepository.saveAndFlush(student);
		return s;
	}
	
	@Override
	public void delete(Long id) {
		studentRepository.deleteById(id);
	}
	
	@Override
	public List<Student> getAllStudents() {
		List<Student> list = studentRepository.findAll();
		
		/*List<Student> students = list.stream()
				.map(i -> i)
				.collect(Collectors.toList());*/
		return list;
	}
	
}
