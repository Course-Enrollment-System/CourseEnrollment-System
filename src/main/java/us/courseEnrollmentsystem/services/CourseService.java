package us.courseEnrollmentsystem.services;

import us.courseEnrollmentsystem.dtos.requests.CreateCourseRequest;
import us.courseEnrollmentsystem.dtos.responses.CreateCourseResponse;

public interface CourseService {

    CreateCourseResponse createCourse(CreateCourseRequest courseRequest);
}
