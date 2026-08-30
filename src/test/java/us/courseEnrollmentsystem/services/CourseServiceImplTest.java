package us.courseEnrollmentsystem.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import us.courseEnrollmentsystem.data.repositories.CourseRepository;
import us.courseEnrollmentsystem.dtos.requests.CreateCourseRequest;
import us.courseEnrollmentsystem.exception.CourseException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class CourseServiceImplTest {

    @Autowired
    private CourseService courseService;

    @Autowired
    private CourseRepository courseRepository;

    private CreateCourseRequest createCourseRequest;

    @BeforeEach
    public void setUp(){
        createCourseRequest = new CreateCourseRequest();
        courseRepository.deleteAll();
    }

    @Test
    public void createCourseWithNullRequestTest(){
        assertThrows(CourseException.class, () -> courseService.createCourse(null));
    }

    @Test
    public void createCourseWithEmptyRequestTest(){
        assertThrows(CourseException.class, () -> courseService.createCourse(createCourseRequest));
    }

    @Test
    public void createCourseWithIncompleteRequestExceptionThrownTest(){
        createCourseRequest.setCourseId("VMPY311");
        createCourseRequest.setDepartment("veterinary medicine");
        createCourseRequest.setCreditUnit(6);
//        createCourseRequest.setTitle("Psychology");

        assertThrows(CourseException.class, () -> courseService.createCourse(createCourseRequest));

    }

    @Test
    public void createCourseWithcompleteRequestTest(){
        createCourseRequest.setCourseId("VMPY311");
        createCourseRequest.setDepartment("veterinary medicine");
        createCourseRequest.setCreditUnit(6);
        createCourseRequest.setTitle("Psychology");

        courseService.createCourse(createCourseRequest);

        assertEquals(1, courseRepository.count());

    }

    @Test
    public void createCourseThatAlreadyExistsExceptionThrownTest(){
        createCourseRequest.setCourseId("VMPY311");
        createCourseRequest.setDepartment("veterinary medicine");
        createCourseRequest.setCreditUnit(6);
        createCourseRequest.setTitle("Psychology");

        courseService.createCourse(createCourseRequest);

        CreateCourseRequest createCourseRequest2 = new CreateCourseRequest();
        createCourseRequest2.setCourseId("VMPY311");
        createCourseRequest2.setDepartment("veterinary medicine");
        createCourseRequest2.setCreditUnit(6);
        createCourseRequest2.setTitle("Psychology");
        assertThrows(CourseException.class, () -> courseService.createCourse(createCourseRequest2));

    }



}