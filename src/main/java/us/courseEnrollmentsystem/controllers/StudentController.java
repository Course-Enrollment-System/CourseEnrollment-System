package us.courseEnrollmentsystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import us.courseEnrollmentsystem.dtos.requests.CreateStudentRequest;
import us.courseEnrollmentsystem.dtos.responses.CreateStudentResponse;
import us.courseEnrollmentsystem.services.StudentService;

@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/create")
    public CreateStudentResponse create(@RequestBody CreateStudentRequest studentRequest) {
        return studentService.createStudent(studentRequest);
    }
}
