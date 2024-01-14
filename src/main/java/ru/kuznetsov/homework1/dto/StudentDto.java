package ru.kuznetsov.homework1.dto;

import ru.kuznetsov.homework1.models.Student;

public class StudentDto {
    private long id;
    private String name;
    private String groupName;

    public StudentDto() {
    }

    public StudentDto(long id, String name, String groupName) {
        this.id = id;
        this.name = name;
        this.groupName = groupName;
    }

    public static StudentDto fromStudent(Student student) {
        return new StudentDto(
                student.getId(),
                student.getName(),
                student.getGroup() == null ? null : student.getGroup().getName()
        );
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
