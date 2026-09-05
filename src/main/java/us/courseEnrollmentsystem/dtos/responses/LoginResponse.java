package us.courseEnrollmentsystem.dtos.responses;

import lombok.Data;
import us.courseEnrollmentsystem.data.models.Role;

@Data
public class LoginResponse {


    private String email;
    private String message;
    private Role role;
}
