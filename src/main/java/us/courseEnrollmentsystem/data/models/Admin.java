package us.courseEnrollmentsystem.data.models;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Admin {

    private static final String ADMIN_EMAIL = "admin@administration.com";
    private static final String ADMIN_PASSWORD = "Administration1234$$";

    @Id
    private static final String ADMIN_ID = "ADMIN";

    private static final String ADMIN_NAME = "Administrator";
    private static final Role ADMIN_ROLE = Role.ADMIN;
}
