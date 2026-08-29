package us.courseEnrollmentsystem.utils;

import us.courseEnrollmentsystem.data.models.Student;
import us.courseEnrollmentsystem.dtos.requests.CreateStudentRequest;
import us.courseEnrollmentsystem.dtos.responses.CreateStudentResponse;

public class Mapper {

    public static Student map(CreateStudentRequest studentRequest){
        Student student = new Student();
        student.setName(studentRequest.getName());
        student.setEmail(studentRequest.getEmail());
        student.setDepartment(studentRequest.getDepartment());

        return student;
    }

    public static CreateStudentResponse map(Student student){
        CreateStudentResponse studentResponse = new CreateStudentResponse();
        studentResponse.setEmail(student.getEmail());
        studentResponse.setName(student.getName());
        studentResponse.setDepartment(student.getDepartment());
        studentResponse.setStudentId(student.getStudentId());

        return studentResponse;
    }
}
