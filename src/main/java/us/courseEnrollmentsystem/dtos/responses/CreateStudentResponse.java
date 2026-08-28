package us.courseEnrollmentsystem.dtos.responses;

import lombok.Data;

@Data
public class CreateStudentResponse {
    private String studentId;
    private String name;
    private String email;
    private String department;
}
