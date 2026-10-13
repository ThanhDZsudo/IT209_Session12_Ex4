/**
 * Entity đại diện cho sinh viên.
 *
 * Sử dụng các annotation của JPA để ánh xạ vào bảng `students` và các
 * annotation của Jakarta Validation để thực hiện kiểm tra dữ liệu đầu vào.
 */
package com.example.studentapi;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "students")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Student {
    /**
     * Mã sinh viên – là khóa chính, không được trùng và không được để trống.
     */
    @Id
    @Column(name = "student_id", nullable = false, unique = true)
    @NotBlank(message = "Mã sinh viên không được để trống")
    private String studentId;

    /**
     * Họ và tên sinh viên – bắt buộc.
     */
    @Column(name = "name", nullable = false)
    @NotBlank(message = "Tên sinh viên không được để trống")
    @Size(max = 100, message = "Tên sinh viên tối đa 100 ký tự")
    private String name;

    /**
     * Ngày sinh – phải là ngày trong quá khứ hoặc hiện tại.
     */
    @Column(name = "birth_date")
    @PastOrPresent(message = "Ngày sinh không được ở tương lai")
    private LocalDate birthDate;

    /**
     * Giới tính – là enum Gender.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;

    /**
     * Số điện thoại – dạng chuỗi 10‑11 số, cho phép bắt đầu bằng 0.
     */
    @Column(name = "phone")
    @Pattern(regexp = "^0[0-9]{9,10}$", message = "Số điện thoại không hợp lệ")
    private String phone;

    /**
     * Điểm trung bình (GPA) – nằm trong khoảng 0.0 – 4.0.
     */
    @Column(name = "gpa")
    @DecimalMin(value = "0.0", inclusive = true, message = "GPA tối thiểu là 0.0")
    @DecimalMax(value = "4.0", inclusive = true, message = "GPA tối đa là 4.0")
    private Double gpa;
}
