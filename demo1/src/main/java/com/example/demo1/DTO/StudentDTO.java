package com.example.demo1.DTO;

import java.util.List;

import com.example.demo1.entity.Course;

public class StudentDTO {

	private Integer rollNo;
    private String name;
    private List<CourseSummary> courses;

    public StudentDTO(){}
    
    public StudentDTO(String name, Integer rollNo) {
    	
    	this.name=name;
    	this.rollNo=rollNo;
    }
    

    public StudentDTO(Integer rollNo, String name, List<Course> courses) {
		super();
		this.rollNo = rollNo;
		this.name = name;
		this.courses = courses.stream().map(a->new CourseSummary(a.getCourseName())).toList();
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

    public List<CourseSummary> getCourses() {
        return courses;
    }

    public void setCourses(List<CourseSummary> courses) {
        this.courses = courses;
    }

    public static class CourseSummary {
        private String courseName;

        public CourseSummary() {}
        public CourseSummary(String courseName) {
        	this.courseName=courseName;
        }
        


        public String getCourseName() {
            return courseName;
        }

        public void setCourseName(String courseName) {
            this.courseName = courseName;
        }
    }

}
