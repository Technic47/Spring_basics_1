package ru.kuznetsov.homework1.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kuznetsov.homework1.dto.StudentDto;
import ru.kuznetsov.homework1.models.Student;
import ru.kuznetsov.homework1.models.StudentGroup;
import ru.kuznetsov.homework1.services.GroupService;
import ru.kuznetsov.homework1.services.StudentService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;
    private final GroupService groupService;

    public StudentController(StudentService service, GroupService groupService) {
        this.studentService = service;
        this.groupService = groupService;
    }

    @Operation(summary = "Get student by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Student is found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = StudentDto.class))}),
            @ApiResponse(responseCode = "404", description = "Student not found",
                    content = @Content)})
    @GetMapping("/{id}")
    public ResponseEntity<StudentDto> getById(@PathVariable long id) {
        Student student = studentService.getById(id);
        return ResponseEntity.ok(StudentDto.fromStudent(student));
    }

    @Operation(summary = "Get all students")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Index is ok",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = List.class))})})
    @GetMapping()
    public ResponseEntity getAll() {
        List<StudentDto> students = new ArrayList<>();
        studentService.getAll()
                .forEach(student -> students.add(StudentDto.fromStudent(student)));
        return ResponseEntity.ok(students);
    }

    @Operation(summary = "Get student by name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Student is found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = StudentDto.class))}),
            @ApiResponse(responseCode = "404", description = "Student not found",
                    content = @Content)})
    @GetMapping("/search")
    public ResponseEntity searchStudentByName(@RequestParam(name = "studentName") String studentName) {
        Student student = studentService
                .searchByName(studentName);
        return ResponseEntity.ok(StudentDto.fromStudent(student));
    }

    @Operation(summary = "Create a new student")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Student is created",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = StudentDto.class))}),
            @ApiResponse(responseCode = "400", description = "Student request Body",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Student already exists",
                    content = @Content)})
    @PostMapping()
    public ResponseEntity<StudentDto> createStudent(@RequestBody StudentDto studentDto) {
        Student toSave = new Student(studentDto.getName());
        Student student = studentService.save(toSave);
        if (studentDto.getGroupName() != null) {
            StudentGroup group = groupService.searchByName(studentDto.getGroupName());
            studentService.setGroup(student, group);
            groupService.addStudent(group, student);
        }
        return ResponseEntity.ok(StudentDto.fromStudent(student));
    }

    @Operation(summary = "Add student to a group")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Student added to group",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = StudentDto.class))}),
            @ApiResponse(responseCode = "404", description = "Student/group not found",
                    content = @Content)})
    @PostMapping("/toGroup")
    public ResponseEntity<StudentDto> setGroup(
            @RequestParam(name = "studentName") String studentName,
            @RequestParam(name = "groupName") String groupName
    ) {
        Student student = studentService.searchByName(studentName);
        StudentGroup group = groupService.searchByName(groupName);
        studentService.setGroup(student, group);
        groupService.addStudent(group, student);
        return ResponseEntity.ok(StudentDto.fromStudent(student));
    }

    @Operation(summary = "Delete student")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Student is deleted",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Student not found",
                    content = @Content)})
    @DeleteMapping("/{id}")
    public boolean deleteById(@PathVariable long id) {
        studentService.deleteById(id);
        return true;
    }

}
