package us.courseEnrollmentsystem.dtos.requests;

import lombok.Data;

@Data
public class CreateStudentRequest {
    private String name;
    private String email;
    private String department;
}
