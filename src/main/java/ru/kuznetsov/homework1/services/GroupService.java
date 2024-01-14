package ru.kuznetsov.homework1.services;

import org.springframework.stereotype.Service;
import ru.kuznetsov.homework1.exceptions.ResourceNotFoundException;
import ru.kuznetsov.homework1.models.StudentGroup;
import ru.kuznetsov.homework1.models.Student;
import ru.kuznetsov.homework1.repositories.GroupRepository;
import ru.kuznetsov.homework1.services.abstracts.AbstractService;

import java.util.Optional;

@Service
public class GroupService extends AbstractService<StudentGroup, GroupRepository> {
    public GroupService(GroupRepository repository) {
        super(repository);
    }

    public StudentGroup searchByName(String groupName) {
        Optional<StudentGroup> groupByName = repository.findGroupByName(groupName);
        if (groupByName.isEmpty()) {
            throw new ResourceNotFoundException(groupName);
        } else return groupByName.get();
    }

    public StudentGroup addStudent(StudentGroup group, Student student) {
        group.getStudentList().add(student);
        return repository.save(group);
    }
}
