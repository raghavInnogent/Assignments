package com.example.demo1.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

@Entity
public class Student {

	@Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer rollNo;
	
	@Column
	String name;
	
	@Column
	@ManyToMany
	List<Course> course;

	public Student(String name) {
		super();
		this.name = name;
	}

	public Integer getRollNo() {
		return rollNo;
	}

	public void setRollNo(Integer rollNo) {
		this.rollNo = rollNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Course> getCourse() {
		return course;
	}

	public void setCourse(List<Course> course) {
		this.course = course;
	}
	public Student() {}

	public Student(Integer rollNo, String name, List<Course> course) {
		super();
		this.rollNo = rollNo;
		this.name = name;
		this.course = course;
	}
	
		
}
