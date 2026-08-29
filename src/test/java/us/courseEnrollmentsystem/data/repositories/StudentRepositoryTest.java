package us.courseEnrollmentsystem.data.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import us.courseEnrollmentsystem.data.models.Student;
import us.courseEnrollmentsystem.dtos.requests.CreateStudentRequest;
import us.courseEnrollmentsystem.services.StudentService;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
public class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    StudentService studentService;

    private CreateStudentRequest createStudentRequest;

    @BeforeEach
    public void setUp(){
        createStudentRequest = new CreateStudentRequest();
        studentRepository.deleteAll();
    }

    @Test
    public void createStudentCountIsOneTest(){
        createStudentRequest.setName("Azeez");
        createStudentRequest.setEmail("az@gmail.com");
        createStudentRequest.setDepartment("Biochemistry");

        studentService.createStudent(createStudentRequest);

        assertEquals(1, studentRepository.count());
    }

    @Test
    public void createStudent_findByEmailTest(){
        createStudentRequest.setName("Azeez");
        createStudentRequest.setEmail("az@gmail.com");
        createStudentRequest.setDepartment("Biochemistry");
        studentService.createStudent(createStudentRequest);

        Student student = studentRepository.findByEmail(createStudentRequest.getEmail());

        assertEquals(student.getName(), createStudentRequest.getName());

    }

}