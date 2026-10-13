package com.example.studentapi.repository;

import com.example.studentapi.Gender;
import com.example.studentapi.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {
    Page<Student> findByNameContainingIgnoreCaseAndGender(String name, Gender gender, Pageable pageable);
    Page<Student> findByNameContainingIgnoreCase(String name, Pageable pageable);
    Page<Student> findByGender(Gender gender, Pageable pageable);
}
