package ru.kuznetsov.homework1.dto;

import ru.kuznetsov.homework1.models.Student;
import ru.kuznetsov.homework1.models.StudentGroup;

import java.util.List;
import java.util.stream.Collectors;

public class StudentGroupDto {
    private String name;
    private List<String> students;

    public static StudentGroupDto fromStudentGroup(StudentGroup group) {
        StudentGroupDto dto = new StudentGroupDto();
        dto.setName(group.getName());
        List<Student> studentList = group.getStudentList();
        if (studentList != null && !studentList.isEmpty()) {
            dto.setStudents(group.getStudentList()
                    .stream()
                    .map(Student::getName)
                    .collect(Collectors.toList()));
        }
        return dto;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getStudents() {
        return students;
    }

    public void setStudents(List<String> students) {
        this.students = students;
    }
}
