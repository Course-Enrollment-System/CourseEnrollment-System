package us.courseEnrollmentsystem.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.courseEnrollmentsystem.data.models.Student;
import us.courseEnrollmentsystem.data.repositories.StudentRepository;
import us.courseEnrollmentsystem.dtos.requests.RegisterStudentRequest;
import us.courseEnrollmentsystem.dtos.responses.RegisterStudentResponse;
import us.courseEnrollmentsystem.exception.StudentException;

import static us.courseEnrollmentsystem.utils.Mapper.map;
import static us.courseEnrollmentsystem.utils.Validator.validateStudentRequest;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    StudentRepository studentRepository;

    @Override
    public RegisterStudentResponse registerStudent(RegisterStudentRequest studentRequest) {
        validateStudentRequest(studentRequest);
        if(studentRepository.findByEmail(studentRequest.getEmail()) != null) throw new StudentException("Student already exists");
        Student student = map(studentRequest);
        studentRepository.save(student);

        return map(student);
    }
}
