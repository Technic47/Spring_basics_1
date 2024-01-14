package ru.kuznetsov.homework1;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import ru.kuznetsov.homework1.models.Student;
import ru.kuznetsov.homework1.models.StudentGroup;
import ru.kuznetsov.homework1.services.GroupService;
import ru.kuznetsov.homework1.services.StudentService;

import java.util.Arrays;

@Component
public class ApplicationEventsListener {
    private final StudentService studentService;
    private final GroupService groupService;

    public ApplicationEventsListener(StudentService studentService, GroupService groupService) {
        this.studentService = studentService;
        this.groupService = groupService;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void runAfterStart(){
        System.out.println("Loading demo data");
        studentService.saveAll(Arrays.asList(
                new Student("Ivan"),
                new Student("Andrey"),
                new Student("Svetlana"),
                new Student("Igor"),
                new Student("Anzhela"),
                new Student("Oleg"),
                new Student("Pavel"),
                new Student("Olga"),
                new Student("Ibrahim"),
                new Student("Alla")
        ));

        groupService.save(new StudentGroup("group1"));
        System.out.println("Data loaded successfully!");
    }
}
