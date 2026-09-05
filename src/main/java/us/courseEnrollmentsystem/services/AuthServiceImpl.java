package us.courseEnrollmentsystem.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.courseEnrollmentsystem.data.models.Admin;
import us.courseEnrollmentsystem.data.models.Role;
import us.courseEnrollmentsystem.data.models.Student;
import us.courseEnrollmentsystem.data.repositories.StudentRepository;
import us.courseEnrollmentsystem.dtos.requests.LoginRequest;
import us.courseEnrollmentsystem.dtos.requests.RegisterStudentRequest;
import us.courseEnrollmentsystem.dtos.responses.LoginResponse;
import us.courseEnrollmentsystem.dtos.responses.RegisterStudentResponse;
import us.courseEnrollmentsystem.exception.StudentException;

import static us.courseEnrollmentsystem.utils.Mapper.map;
import static us.courseEnrollmentsystem.utils.Mapper.mapLogin;
import static us.courseEnrollmentsystem.utils.Validator.validateLoginRequest;
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

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        validateLoginRequest(loginRequest);

        Admin admin = new Admin();
        if (admin.getADMIN_EMAIL().equals(loginRequest.getEmail())) {
            if (!admin.getADMIN_PASSWORD().equals(loginRequest.getPassword())) throw new StudentException("Invalid password");
            return map(admin);
        }

        Student student = studentRepository.findByEmail(loginRequest.getEmail());
        if (student == null) throw new StudentException("Student not found");
        if (!student.getPassword().equals(loginRequest.getPassword())) throw new StudentException("Invalid password");
        student.setActive(true);
        studentRepository.save(student);

        return mapLogin(student);
    }

    @Override
    public String logout(String email) {
        Student student = studentRepository.findByEmail(email);

        if (student == null) throw new StudentException("Student not found");
        student.setActive(false);
        studentRepository.save(student);

        return "Logged out successfully";
    }

}
