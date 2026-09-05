package us.courseEnrollmentsystem.data.models;


import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Student {

    @Id
    private String studentId;
    private String name;
    private String email;
    private String password;
    private String department;
    private boolean active;
    private Role role =  Role.STUDENT;

}
