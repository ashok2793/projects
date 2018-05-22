package com.ibm.ashok;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.entity.Student;
import com.ibm.service.StudentService;

@RestController
@RequestMapping("/display")
public class DisplaySomeContent {
	
	
	@Autowired
	private StudentService studentService;
	
	public DisplaySomeContent(StudentService studentService){
		this.studentService = studentService;
	}


	@ResponseBody
	@PostMapping("/insert")
	public Student createStudent(@RequestBody Student student) {
		return studentService.addStudent(student);
	}
	
	@ResponseBody
	@GetMapping("/getById/{id}")
	public Optional<Student> getStudentById(@PathVariable("id") Long id){
		return studentService.getStudentById(id);
	}
	
	@ResponseBody
	@PostMapping("/update")
	public Student update(@RequestBody Student student) throws Exception{
		 return studentService.update(student);
	}
	
	@ResponseBody
	@PostMapping("/delete")
	public void delete(@PathVariable("id") Long id){
		  studentService.delete(id);;
	}
	
	@ResponseBody
	@GetMapping("/getAll")
	public List<Student> getAllStudent(){
		return studentService.getAllStudents();
	}
	
	
}
