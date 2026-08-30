package us.courseEnrollmentsystem.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.courseEnrollmentsystem.data.models.Course;
import us.courseEnrollmentsystem.data.repositories.CourseRepository;
import us.courseEnrollmentsystem.data.repositories.StudentRepository;
import us.courseEnrollmentsystem.dtos.requests.CreateCourseRequest;
import us.courseEnrollmentsystem.dtos.responses.CreateCourseResponse;
import us.courseEnrollmentsystem.exception.CourseException;

import static us.courseEnrollmentsystem.utils.Mapper.map;
import static us.courseEnrollmentsystem.utils.Validator.validateCourseRequest;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private StudentRepository studentRepository;


    @Override
    public CreateCourseResponse createCourse(CreateCourseRequest courseRequest) {
        validateCourseRequest(courseRequest);

        if (courseRepository.existsById(courseRequest.getCourseId())) throw new CourseException("Course already exists");
        Course course = map(courseRequest);
        courseRepository.save(course);

        return map(course);
    }
}
