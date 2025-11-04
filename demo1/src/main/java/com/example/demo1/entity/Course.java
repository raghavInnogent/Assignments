package com.example.demo1.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Course {


	
	@Id
	String courseName;
	
	@Column
	String guide;
	
	@Column
	@ManyToMany
	List<Student> enrolledStudents;

	public Course(String courseName, String guide) {
		super();
		this.courseName = courseName;
		this.guide = guide;
	}

	public Course() {
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getGuide() {
		return guide;
	}

	public void setGuide(String guide) {
		this.guide = guide;
	}

	public List<Student> getEnrolledStudents() {
		return enrolledStudents;
	}

	public void setEnrolledStudents(List<Student> enrolledStudents) {
		this.enrolledStudents = enrolledStudents;
	}
	
	
	
}
