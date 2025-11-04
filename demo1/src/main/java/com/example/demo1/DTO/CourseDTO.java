package com.example.demo1.DTO;

import java.util.List;
import com.example.demo1.entity.Student;
		
public class CourseDTO {
	private String courseName;
    private String guide;
    private List<StudentSummary> enrolledStudents;

    public CourseDTO() {}
    
    public CourseDTO( String courseName, String guide) {
		super();
		this.courseName = courseName;
		this.guide = guide;
	}


    public CourseDTO(String courseName, String guide, List<Student> enrolledStudents) {
		super();
		this.courseName = courseName;
		this.guide = guide;
		this.enrolledStudents = enrolledStudents
									.stream()	
									.map(a->new StudentSummary(a.getRollNo(),a.getName())
									).toList();
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

    public List<StudentSummary> getEnrolledStudents() {
        return enrolledStudents;
    }

    public void setEnrolledStudents(List<StudentSummary> enrolledStudents) {
        this.enrolledStudents = enrolledStudents;
    }

    public static class StudentSummary {
        private Integer rollNo;
        private String name;

        public StudentSummary() {}
        public StudentSummary(Integer rollNo, String name)
        {
        	this.rollNo=rollNo;
        	this.name=name;
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
    }

}
