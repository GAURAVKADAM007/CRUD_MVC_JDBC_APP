package com.stackninjas.crudapp.dao;

import com.stackninjas.crudapp.model.Student;

import java.util.List;

public interface StudentDAO {
    void insert(Student student);
    void delete(int id);
    Student getStudentById(int id);
    void update(Student student);
    List<Student> getAllStudents();
}
