package us.courseEnrollmentsystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import us.courseEnrollmentsystem.dtos.requests.CreateCourseRequest;
import us.courseEnrollmentsystem.dtos.responses.CreateCourseResponse;
import us.courseEnrollmentsystem.services.CourseService;

@RestController
public class CourseController {

    @Autowired
    private CourseService courseService;

    @PostMapping("/create-course")
    public CreateCourseResponse createCourse (@RequestBody CreateCourseRequest createCourseRequest){
        return courseService.createCourse(createCourseRequest);
    }
}
