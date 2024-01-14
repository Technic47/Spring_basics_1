package ru.kuznetsov.homework1.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class StudentGroup extends BaseEntity{
    private String name;
    @OneToMany
    private List<Student> studentList;

    public StudentGroup() {
    }

    public StudentGroup(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }
}
