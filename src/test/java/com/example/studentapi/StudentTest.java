package com.example.studentapi;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class StudentTest {

    @Test
    @DisplayName("Verify Student builder and getters work correctly")
    void testStudentBuilderAndGetters() {
        LocalDate birthDate = LocalDate.of(2003, 5, 15);
        Student student = Student.builder()
                .studentId("SV001")
                .name("Nguyen Tien Thanh")
                .birthDate(birthDate)
                .gender(Gender.MALE)
                .phone("0912345678")
                .gpa(3.8)
                .build();

        assertNotNull(student);
        assertEquals("SV001", student.getStudentId());
        assertEquals("Nguyen Tien Thanh", student.getName());
        assertEquals(birthDate, student.getBirthDate());
        assertEquals(Gender.MALE, student.getGender());
        assertEquals("0912345678", student.getPhone());
        assertEquals(3.8, student.getGpa());
    }

    @Test
    @DisplayName("Verify Student setters work correctly")
    void testStudentSetters() {
        Student student = new Student();
        student.setStudentId("SV002");
        student.setName("Tran Thi B");
        student.setGender(Gender.FEMALE);
        student.setGpa(3.5);

        assertEquals("SV002", student.getStudentId());
        assertEquals("Tran Thi B", student.getName());
        assertEquals(Gender.FEMALE, student.getGender());
        assertEquals(3.5, student.getGpa());
    }
}
