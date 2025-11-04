package com.example.demo1.service;

import org.springframework.stereotype.Service;
import com.example.demo1.DTO.CourseDTO;

@Service
public interface CourseService {

	CourseDTO createCourse(CourseDTO course);

	CourseDTO getEnrolledStudents(String courseName);
}
