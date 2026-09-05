package us.courseEnrollmentsystem.data.models;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Admin {

    private final String ADMIN_EMAIL = "admin@administration.com";
    private final String ADMIN_PASSWORD = "Administration1234$$";

    @Id
    private final String ADMIN_ID = "ADMIN";

    private final String ADMIN_NAME = "Administrator";
    private final Role ADMIN_ROLE = Role.ADMIN;
}
