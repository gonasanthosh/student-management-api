package com.studentmanagement.repository;

import com.studentmanagement.model.Student;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentRepository {
    public List<Student> getStudents(){
        return List.of(new Student(1L,"Santhosh", "gonasanthosh666@gmail.com"),
                new Student(2L, "Karthik","karatapukarthik@gmail.com") );
    }

    public Student getStudentById(Long id){
        return getStudents().stream().
                filter(Student -> Student.getId().equals(id)).findFirst().orElse(null);
    }

    public Student addStudent(Student student){
        return student;
    }

    public Student updateStudent(Long id, Student student) {
        student.setId(id);
        return student;
    }

    public void deleteStudent(Long id){
        System.out.println("Deleting student with id: " + id);
    }
}