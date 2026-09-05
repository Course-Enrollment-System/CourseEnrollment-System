package us.courseEnrollmentsystem.dtos.responses;

import lombok.Data;
import us.courseEnrollmentsystem.data.models.Role;

@Data
public class LoginResponse {

    private String id;
    private String name;
    private String email;
    private Role role;
    private String message;
}
