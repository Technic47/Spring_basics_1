package ru.kuznetsov.homework1.repositories;

import org.springframework.stereotype.Repository;
import ru.kuznetsov.homework1.models.Student;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends AbstractRepository<Student> {
    Optional<Student> findStudentByName(String name);
}
