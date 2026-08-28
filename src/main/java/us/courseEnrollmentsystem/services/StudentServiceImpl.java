package us.courseEnrollmentsystem.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.courseEnrollmentsystem.data.models.Student;
import us.courseEnrollmentsystem.data.repositories.StudentRepository;
import us.courseEnrollmentsystem.dtos.requests.CreateStudentRequest;
import us.courseEnrollmentsystem.dtos.responses.CreateStudentResponse;
import us.courseEnrollmentsystem.exception.StudentException;


import static us.courseEnrollmentsystem.utils.Mapper.map;
import static us.courseEnrollmentsystem.utils.Validator.validateStudentRequest;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;


    @Override
    public CreateStudentResponse createStudent(CreateStudentRequest studentRequest) {
        validateStudentRequest(studentRequest);
        if(studentRepository.findByEmail(studentRequest.getEmail()) != null) throw new StudentException("Student already exists");
        Student student = map(studentRequest);
        studentRepository.save(student);

        return map(student);
    }
}
