package us.courseEnrollmentsystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import us.courseEnrollmentsystem.dtos.requests.RegisterStudentRequest;
import us.courseEnrollmentsystem.dtos.responses.RegisterStudentResponse;
import us.courseEnrollmentsystem.services.AuthService;

@RestController
public class AuthController {

    @Autowired
    private AuthService authService;


    @PostMapping("/register")
    public RegisterStudentResponse create(@RequestBody RegisterStudentRequest studentRequest) {
        return authService.registerStudent(studentRequest);
    }
}
