package ru.kuznetsov.homework1.services;

import org.springframework.stereotype.Service;
import ru.kuznetsov.homework1.exceptions.ResourceNotFoundException;
import ru.kuznetsov.homework1.models.StudentGroup;
import ru.kuznetsov.homework1.models.Student;
import ru.kuznetsov.homework1.repositories.StudentRepository;
import ru.kuznetsov.homework1.services.abstracts.AbstractService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService extends AbstractService<Student, StudentRepository> {
    public StudentService(StudentRepository repository) {
        super(repository);
    }

    public Student searchByName(String name){
        Optional<Student> studentByName = repository.findStudentByName(name);
        if (studentByName.isEmpty()) {
            throw new ResourceNotFoundException(name);
        } else return studentByName.get();
    }

    public Student setGroup(Student student, StudentGroup group){
        student.setGroup(group);
        return save(student);
    }
}
