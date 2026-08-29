package us.courseEnrollmentsystem.services;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import us.courseEnrollmentsystem.data.repositories.StudentRepository;
import us.courseEnrollmentsystem.dtos.requests.CreateStudentRequest;
import us.courseEnrollmentsystem.exception.StudentException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class StudentServiceImplTest {

    @Autowired
    private StudentService studentService;

    @Autowired
    private StudentRepository studentRepository;

    private CreateStudentRequest createStudentRequest;

    @BeforeEach
    public void setUp(){
        studentRepository.deleteAll();
        createStudentRequest = new CreateStudentRequest();
    }

    @ParameterizedTest
    @NullSource
    public void registerStudentWithNullRequest(CreateStudentRequest createStudentRequest){
        assertThrows(StudentException.class, () -> studentService.createStudent(createStudentRequest));
    }

    @Test
    public void registerStudentWithEmptyRequest(){
        assertThrows(StudentException.class, () -> studentService.createStudent(createStudentRequest));
    }

    @Test
    public void registerStudentWithIncompleteRequestThrowsStudentException(){
        createStudentRequest.setName("Azeez");
        createStudentRequest.setEmail("az@gmail.com");
        assertThrows(StudentException.class, () -> studentService.createStudent(createStudentRequest));
    }

    @Test
    public void registerStudentWithValidRequestRepositoryCountIsOneTest(){
        createStudentRequest.setName("Azeez");
        createStudentRequest.setEmail("az@gmail.com");
        createStudentRequest.setDepartment("Biochemistry");

        studentService.createStudent(createStudentRequest);
        assertEquals(1, studentRepository.count());
    }

    @Test
    public void registerTwoStudentSWithValidRequestButWithSameEmailThrowsExceptionTest(){
        createStudentRequest.setName("Azeez");
        createStudentRequest.setEmail("az@gmail.com");
        createStudentRequest.setDepartment("Biochemistry");
        studentService.createStudent(createStudentRequest);

        CreateStudentRequest createStudentRequest2 = new CreateStudentRequest();
        createStudentRequest2.setName("Bola");
        createStudentRequest2.setEmail("az@gmail.com");
        createStudentRequest2.setDepartment("chemistry");
        assertThrows(StudentException.class, () -> studentService.createStudent(createStudentRequest2));

    }

}