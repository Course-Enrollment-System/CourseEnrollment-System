package us.courseEnrollmentsystem.dtos.requests;

import lombok.Data;

@Data
public class RegisterStudentRequest {
    private String name;
    private String email;
    private String password;
    private String department;
}
