package com.example.demo1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo1.DTO.CourseDTO;
import com.example.demo1.service.CourseService;

@RestController
public class CourseController {

	@Autowired
	CourseService courseService;
	
	@PostMapping("/createCourse")
	public CourseDTO createCourse(@RequestBody CourseDTO course)
	{
	
		return courseService.createCourse(course);
	}
	
	@GetMapping("/getEnrolledStudents/{courseName}")
	public CourseDTO getEnrolledStudents(@PathVariable String courseName){
		
		return courseService.getEnrolledStudents(courseName);
	}
	
	
}
