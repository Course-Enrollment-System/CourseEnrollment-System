package us.courseEnrollmentsystem.utils;

import us.courseEnrollmentsystem.dtos.requests.CreateCourseRequest;
import us.courseEnrollmentsystem.dtos.requests.CreateStudentRequest;
import us.courseEnrollmentsystem.exception.CourseException;
import us.courseEnrollmentsystem.exception.StudentException;

public class Validator {

    public static void validateStudentRequest(CreateStudentRequest studentRequest){
        if (studentRequest == null) throw new StudentException("Student request cannot be null");
        if (studentRequest.getName() == null || studentRequest.getName().isEmpty()) throw new StudentException("Student name cannot be empty");
        if (studentRequest.getEmail() == null || studentRequest.getEmail().isEmpty()) throw new StudentException("Student email cannot be empty");
        if(studentRequest.getDepartment() == null || studentRequest.getDepartment().isEmpty()) throw new StudentException("Department name cannot be empty");
    }

    public static void validateCourseRequest(CreateCourseRequest courseRequest){
        if (courseRequest == null) throw new CourseException("Course request cannot be null");
        if (courseRequest.getCourseId() == null || courseRequest.getCourseId().isEmpty()) throw new CourseException("Course id cannot be empty");
        if (courseRequest.getTitle() == null || courseRequest.getTitle().isEmpty()) throw new CourseException("Course title cannot be empty");
        if (courseRequest.getCreditUnit() == 0 ) throw  new CourseException("Credit unit cannot be empty");
        if (courseRequest.getDepartment() == null || courseRequest.getDepartment().isEmpty()) throw  new CourseException("Department name cannot be empty");
    }
}
