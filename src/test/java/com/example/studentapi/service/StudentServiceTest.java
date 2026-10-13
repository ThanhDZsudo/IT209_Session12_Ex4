package com.example.studentapi.service;

import com.example.studentapi.Gender;
import com.example.studentapi.Student;
import com.example.studentapi.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentService studentService;

    private Student sampleStudent;

    @BeforeEach
    void setUp() {
        sampleStudent = Student.builder()
                .studentId("SV001")
                .name("Nguyen Tien Thanh")
                .birthDate(LocalDate.of(2003, 1, 1))
                .gender(Gender.MALE)
                .phone("0987654321")
                .gpa(3.9)
                .build();
    }

    @Test
    @DisplayName("Test createStudent successfully")
    void testCreateStudent() {
        when(studentRepository.save(any(Student.class))).thenReturn(sampleStudent);

        Student created = studentService.createStudent(sampleStudent);

        assertNotNull(created);
        assertEquals("SV001", created.getStudentId());
        verify(studentRepository, times(1)).save(sampleStudent);
    }

    @Test
    @DisplayName("Test getStudent found")
    void testGetStudentFound() {
        when(studentRepository.findById("SV001")).thenReturn(Optional.of(sampleStudent));

        Optional<Student> found = studentService.getStudent("SV001");

        assertTrue(found.isPresent());
        assertEquals("Nguyen Tien Thanh", found.get().getName());
        verify(studentRepository, times(1)).findById("SV001");
    }

    @Test
    @DisplayName("Test getStudent not found")
    void testGetStudentNotFound() {
        when(studentRepository.findById("UNKNOWN")).thenReturn(Optional.empty());

        Optional<Student> found = studentService.getStudent("UNKNOWN");

        assertFalse(found.isPresent());
        verify(studentRepository, times(1)).findById("UNKNOWN");
    }

    @Test
    @DisplayName("Test deleteStudent successfully")
    void testDeleteStudent() {
        doNothing().when(studentRepository).deleteById("SV001");

        studentService.deleteStudent("SV001");

        verify(studentRepository, times(1)).deleteById("SV001");
    }
}
