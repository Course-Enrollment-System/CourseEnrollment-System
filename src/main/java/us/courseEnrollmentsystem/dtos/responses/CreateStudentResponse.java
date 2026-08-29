package us.courseEnrollmentsystem.dtos.responses;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class CreateStudentResponse {

    private String studentId;
    private String name;
    private String email;
    private String department;
}
