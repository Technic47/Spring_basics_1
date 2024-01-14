package ru.kuznetsov.homework1.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kuznetsov.homework1.dto.StudentDto;
import ru.kuznetsov.homework1.dto.StudentGroupDto;
import ru.kuznetsov.homework1.models.StudentGroup;
import ru.kuznetsov.homework1.services.GroupService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/group")
public class GroupController {
    private final GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping("/{groupName}/students")
    public ResponseEntity getStudents(@PathVariable String groupName) {
        List<StudentDto> students = groupService
                .searchByName(groupName)
                .getStudentList()
                .stream()
                .map(StudentDto::fromStudent)
                .collect(Collectors.toList());
        return ResponseEntity.ok(students);
    }

    @Operation(summary = "Create a new group")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Group is created",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = StudentGroupDto.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid request Body",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Group already exists",
                    content = @Content)})
    @PostMapping()
    public ResponseEntity<StudentGroupDto> createStudentGroup(
            @RequestParam(name = "groupName") String groupName
    ) {
        StudentGroup group = groupService.save(new StudentGroup(groupName));
        return ResponseEntity.ok(StudentGroupDto.fromStudentGroup(group));
    }
}
