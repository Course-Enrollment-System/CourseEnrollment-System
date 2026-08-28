package us.courseEnrollmentsystem.utils;

import us.courseEnrollmentsystem.dtos.requests.CreateStudentRequest;
import us.courseEnrollmentsystem.exception.StudentException;

public class Validator {

    public static void validateStudentRequest(CreateStudentRequest studentRequest){
        if (studentRequest == null) throw new StudentException("Student request cannot be null");
        if (studentRequest.getName() == null || studentRequest.getName().isEmpty()) throw new StudentException("Student name cannot be empty");
        if (studentRequest.getEmail() == null || studentRequest.getEmail().isEmpty()) throw new StudentException("Student email cannot be empty");
        if(studentRequest.getDepartment() == null || studentRequest.getDepartment().isEmpty()) throw new StudentException("Department name cannot be empty");
    }
}
