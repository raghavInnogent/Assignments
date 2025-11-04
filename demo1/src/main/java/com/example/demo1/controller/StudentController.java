package com.example.demo1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo1.DTO.StudentDTO;
import com.example.demo1.service.StudentService;

@RestController
public class StudentController {
	
	@Autowired
	StudentService studentService;
	
	@PostMapping("/createStudent")
	public StudentDTO createStudent(@RequestBody StudentDTO studentDto)
	{
		return studentService.createStudent(studentDto);
	}
	
	@PostMapping("/registerStudentInCourse")
	public StudentDTO registerStudentInCourse(Integer rollNo, String courseName)
	{
		return studentService.registerStudent(rollNo, courseName);
	}
	
	@GetMapping("/getRegisteredCourses/{id}")
	public StudentDTO getRegisteredCourses(@PathVariable Integer id) {
		return studentService.getRegisteredCourses(id);
	}
	
	@DeleteMapping("/deleteCourse")
	public StudentDTO deleteCourse( Integer id,  String courseName) {
		
		return studentService.deleteCourse(id,courseName);
		
	}
	
	@GetMapping("/getNamesWithChar/{prefix}")
	public List<StudentDTO> getNamesWithChar(@PathVariable String prefix)
	{
		return studentService.getNamesWithChar(prefix);
	}
	
	@GetMapping("/getStudentsWithRollNoGreaterThan/{val}")
	public List<StudentDTO> getStudentsWithRollNoGreaterThan(@PathVariable Integer val)
	{
		return studentService.getStudentsWithRollNoGreaterThan(val);
	}
	
	
	
}
