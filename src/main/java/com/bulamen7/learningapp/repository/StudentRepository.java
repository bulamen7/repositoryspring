package com.bulamen7.learningapp.repository;

import com.bulamen7.learningapp.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {
    List<Student> findAllByName(String name);
}
