package ru.kuznetsov.homework1.repositories;

import org.springframework.stereotype.Repository;
import ru.kuznetsov.homework1.models.StudentGroup;

import java.util.Optional;

@Repository
public interface GroupRepository extends AbstractRepository<StudentGroup> {
    Optional<StudentGroup> findGroupByName(String name);
}
