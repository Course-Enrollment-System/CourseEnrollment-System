package us.courseEnrollmentsystem.dtos.responses;

import lombok.Data;

@Data
public class CreateCourseResponse {
    private String courseId;
    private String title;
    private int creditUnit;
    private String department;
}
