package com.example.demo1.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo1.DTO.StudentDTO;
import com.example.demo1.controller.CourseController;
import com.example.demo1.entity.Course;
import com.example.demo1.entity.Student;
import com.example.demo1.repo.CourseRepositry;
import com.example.demo1.repo.StudentRepo;
import com.example.demo1.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	StudentRepo studentRepo;
	
	@Autowired
	CourseRepositry courseRepo;
	
	@Override
	public StudentDTO createStudent(StudentDTO studentDto) {
		Student student = new Student(studentDto.getName());
		studentRepo.save(student);
		return new StudentDTO(student.getName(),student.getRollNo());
	}

	@Override
	public StudentDTO registerStudent(Integer rollNo, String courseName) {

		Student student = studentRepo.findById(rollNo).get();
		Course course=courseRepo.findById(courseName).get();
		
		student.getCourse().add(course);
		course.getEnrolledStudents().add(student);
		studentRepo.save(student);
		courseRepo.save(course);
		System.out.println(course.getEnrolledStudents());
		System.out.println(student.getCourse());

		return new StudentDTO(student.getName(), student.getRollNo());

	}

	@Override
	public StudentDTO getRegisteredCourses(Integer id) {
		
		Student student = studentRepo.findById(id).get();
		
		
		return new StudentDTO(student.getRollNo(), student.getName(), student.getCourse());
	}

	@Override
	public StudentDTO deleteCourse(Integer id, String courseName) {
		Student student = studentRepo.findById(id).get();
		List<Course> courseList = student.getCourse().stream()
													.filter(a->!a.getCourseName().equals(courseName))
													.toList();
		studentRepo.save(new Student(student.getRollNo(), student.getName(), courseList));
		
		return new StudentDTO(student.getRollNo(), student.getName(), courseList);
	}

	@Override
	public List<StudentDTO> getNamesWithChar(String prefix) {
		
		 List<StudentDTO> studentList = studentRepo.findStudentsByNameStartingWith(prefix)
				 											.stream()
				 											.map(a->new StudentDTO(a.getRollNo(), a.getName(), a.getCourse()))
				 											.toList();
		 return studentList;
		 
	}

	@Override
	public List<StudentDTO> getStudentsWithRollNoGreaterThan(Integer val) {
		 List<StudentDTO> studentList = studentRepo.getStudentsWithRollNoGreaterThan(val)
					.stream()
					.map(a->new StudentDTO(a.getRollNo(), a.getName(), a.getCourse()))
					.toList();
		 return studentList;	
		 
	}
	
	
	
	

}
