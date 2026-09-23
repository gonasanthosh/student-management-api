package com.studentmanagement.service;

import com.studentmanagement.dto.StudentRequest;
import com.studentmanagement.dto.StudentResponse;
import com.studentmanagement.exception.StudentNotFoundException;
import com.studentmanagement.model.Student;
import com.studentmanagement.repository.StudentRepository;
import org.slf4j.ILoggerFactory;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Service
public class StudentService {

    private static final Logger logger = LoggerFactory.getLogger(StudentService.class);

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    public List<StudentResponse> getStudents() {

        logger.info("Fetching all students");

        return studentRepository.getStudents()
                .stream()
                .map(student -> new StudentResponse(
                        student.getId(),
                        student.getName(),
                        student.getEmail()
                ))
                .toList();
    }

    public StudentResponse getStudentById(Long id){
        Student student = studentRepository.getStudentById(id);

        if(student == null){
            throw new StudentNotFoundException("Student with id " + id + " not found");
        }

        return new StudentResponse(student.getId(), student.getName(), student.getEmail());
    }

    public Student addStudent(StudentRequest request) {

        Student student = new Student();

        student.setName(request.getName());
        student.setEmail(request.getEmail());

        return studentRepository.addStudent(student);
    }

    public StudentResponse updateStudent(
            Long id,
            StudentRequest request) {

        Student student = studentRepository.getStudentById(id);

        if (student == null) {
            throw new StudentNotFoundException(
                    "Student with id " + id + " not found"
            );
        }

        student.setName(request.getName());
        student.setEmail(request.getEmail());

        Student updatedStudent =
                studentRepository.updateStudent(id, student);

        return new StudentResponse(
                updatedStudent.getId(),
                updatedStudent.getName(),
                updatedStudent.getEmail()
        );
    }

    public void deleteStudent(Long id){
        logger.info("Deleting Student with id {} ", id);
        studentRepository.deleteStudent(id);
    }
}