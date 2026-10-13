package com.example.studentapi.service;

import com.example.studentapi.Gender;
import com.example.studentapi.Student;
import com.example.studentapi.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    public Optional<Student> getStudent(String id) {
        return studentRepository.findById(id);
    }

    public Student updateStudent(String id, Student updated) {
        return studentRepository.findById(id)
                .map(existing -> {
                    existing.setName(updated.getName());
                    existing.setBirthDate(updated.getBirthDate());
                    existing.setGender(updated.getGender());
                    existing.setPhone(updated.getPhone());
                    existing.setGpa(updated.getGpa());
                    return studentRepository.save(existing);
                })
                .orElseThrow(() -> new IllegalArgumentException("Student not found with id: " + id));
    }

    public void deleteStudent(String id) {
        studentRepository.deleteById(id);
    }

    public Page<Student> searchStudents(Optional<String> name, Optional<Gender> gender, int page, int size, Optional<String> sortBy, Optional<String> direction) {
        Sort sort = Sort.unsorted();
        if (sortBy.isPresent()) {
            Sort.Direction dir = direction.map(String::toUpperCase)
                    .map(Sort.Direction::valueOf)
                    .orElse(Sort.Direction.ASC);
            sort = Sort.by(dir, sortBy.get());
        }
        Pageable pageable = PageRequest.of(page, size, sort);
        if (name.isPresent() && gender.isPresent()) {
            return studentRepository.findByNameContainingIgnoreCaseAndGender(name.get(), gender.get(), pageable);
        } else if (name.isPresent()) {
            return studentRepository.findByNameContainingIgnoreCase(name.get(), pageable);
        } else if (gender.isPresent()) {
            return studentRepository.findByGender(gender.get(), pageable);
        } else {
            return studentRepository.findAll(pageable);
        }
    }
}
