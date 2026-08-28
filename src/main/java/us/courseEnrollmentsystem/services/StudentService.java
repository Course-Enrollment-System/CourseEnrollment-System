package us.courseEnrollmentsystem.services;

import us.courseEnrollmentsystem.data.models.Student;
import us.courseEnrollmentsystem.dtos.requests.CreateStudentRequest;
import us.courseEnrollmentsystem.dtos.responses.CreateStudentResponse;

public interface StudentService {

    CreateStudentResponse createStudent(CreateStudentRequest studentRequest);
}
