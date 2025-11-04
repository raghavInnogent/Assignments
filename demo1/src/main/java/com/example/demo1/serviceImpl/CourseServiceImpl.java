package com.example.demo1.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo1.DTO.CourseDTO;
import com.example.demo1.entity.Course;
import com.example.demo1.repo.CourseRepositry;
import com.example.demo1.service.CourseService;

@Service
public class CourseServiceImpl implements CourseService {

	@Autowired
	CourseRepositry courseRepo;
	
	@Override
	public CourseDTO createCourse(CourseDTO courseReq) {
		
		Course course = new Course(courseReq.getCourseName(),courseReq.getGuide());
		course = courseRepo.save(course);
		return new CourseDTO(course.getCourseName(),course.getGuide());
	}

	@Override
	public CourseDTO getEnrolledStudents(String courseName) {
		
		Course course = courseRepo.findById(courseName).get();
		return new CourseDTO(course.getCourseName(),course.getGuide(),course.getEnrolledStudents());
	}

}
