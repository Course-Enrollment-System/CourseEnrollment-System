package us.courseEnrollmentsystem.services;


import us.courseEnrollmentsystem.dtos.requests.LoginRequest;
import us.courseEnrollmentsystem.dtos.requests.RegisterStudentRequest;
import us.courseEnrollmentsystem.dtos.responses.LoginResponse;
import us.courseEnrollmentsystem.dtos.responses.RegisterStudentResponse;
public interface AuthService {

    RegisterStudentResponse registerStudent(RegisterStudentRequest studentRequest);
    LoginResponse login(LoginRequest loginRequest);
    String logout(String email);
}
