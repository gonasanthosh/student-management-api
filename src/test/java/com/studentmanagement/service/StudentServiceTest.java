package com.studentmanagement.service;

import com.studentmanagement.dto.StudentResponse;
import com.studentmanagement.exception.StudentNotFoundException;
import com.studentmanagement.repository.StudentRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class StudentServiceTest {

    @Test
    void shouldReturnStudentById() {

        StudentRepository studentRepository = new StudentRepository();
        StudentService studentService = new StudentService(studentRepository);

        StudentResponse response = studentService.getStudentById(1L);

        assertEquals(1L, response.getId());
        assertEquals("Santhosh", response.getName());
        assertEquals("gonasanthosh666@gmail.com", response.getEmail());
    }

    @Test
    void shouldThrowExceptionWhenStudentDoesNotExist() {

        StudentRepository studentRepository = new StudentRepository();
        StudentService studentService = new StudentService(studentRepository);

        assertThrows(
                StudentNotFoundException.class,
                () -> studentService.getStudentById(999L)
        );
    }

}