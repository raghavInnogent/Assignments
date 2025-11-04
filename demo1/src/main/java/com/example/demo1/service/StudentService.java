package com.example.demo1.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo1.DTO.StudentDTO;

@Service
public interface StudentService {

	StudentDTO createStudent(StudentDTO studentDto);

	StudentDTO registerStudent(Integer rollNo, String courseName);

	StudentDTO getRegisteredCourses(Integer id);

	StudentDTO deleteCourse(Integer id, String courseName);

	List<StudentDTO> getNamesWithChar(String character);

	List<StudentDTO> getStudentsWithRollNoGreaterThan(Integer val);


}
