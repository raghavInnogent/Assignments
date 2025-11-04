package com.example.demo1.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo1.entity.Student;

@Repository
public interface StudentRepo extends JpaRepository<Student,Integer> {
	 
	 @Query("SELECT s FROM Student s WHERE LOWER(s.name) LIKE LOWER(CONCAT(:prefix, '%'))")
	 List<Student> findStudentsByNameStartingWith(@Param("prefix") String prefix);
	 
	 @Query(value = "SELECT * FROM student WHERE roll_no > :rollNo", nativeQuery = true)
	 List<Student> getStudentsWithRollNoGreaterThan(@Param("rollNo") int rollNo);


}
