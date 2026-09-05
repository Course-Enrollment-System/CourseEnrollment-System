package us.courseEnrollmentsystem.utils;

import us.courseEnrollmentsystem.data.models.Course;
import us.courseEnrollmentsystem.data.models.Role;
import us.courseEnrollmentsystem.data.models.Student;
import us.courseEnrollmentsystem.dtos.requests.CreateCourseRequest;
import us.courseEnrollmentsystem.dtos.requests.RegisterStudentRequest;
import us.courseEnrollmentsystem.dtos.responses.CreateCourseResponse;
import us.courseEnrollmentsystem.dtos.responses.RegisterStudentResponse;

public class Mapper {

    public static Student map(RegisterStudentRequest studentRequest){
        Student student = new Student();
        student.setName(studentRequest.getName());
        student.setEmail(studentRequest.getEmail());
        student.setDepartment(studentRequest.getDepartment());
        student.setPassword(studentRequest.getPassword());
        student.setRole(Role.STUDENT);

        return student;
    }

    public static RegisterStudentResponse map(Student student){
        RegisterStudentResponse studentResponse = new RegisterStudentResponse();
        studentResponse.setEmail(student.getEmail());
        studentResponse.setName(student.getName());
        studentResponse.setDepartment(student.getDepartment());
        studentResponse.setStudentId(student.getStudentId());

        return studentResponse;
    }

    public static Course map(CreateCourseRequest courseRequest){
        Course course = new Course();
        course.setCourseId(courseRequest.getCourseId());
        course.setTitle(courseRequest.getTitle());
        course.setCreditUnit(courseRequest.getCreditUnit());
        course.setDepartment(courseRequest.getDepartment());

        return course;
    }

    public static CreateCourseResponse map(Course course){
        CreateCourseResponse courseResponse = new CreateCourseResponse();
        courseResponse.setCourseId(course.getCourseId());
        courseResponse.setTitle(course.getTitle());
        courseResponse.setCreditUnit(course.getCreditUnit());
        courseResponse.setDepartment(course.getDepartment());

        return courseResponse;
    }
}
