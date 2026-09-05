package us.courseEnrollmentsystem.services;


import us.courseEnrollmentsystem.dtos.requests.RegisterStudentRequest;
import us.courseEnrollmentsystem.dtos.responses.RegisterStudentResponse;
public interface AuthService {

    RegisterStudentResponse registerStudent(RegisterStudentRequest studentRequest);
}
