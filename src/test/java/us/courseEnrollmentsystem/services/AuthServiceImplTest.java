package us.courseEnrollmentsystem.services;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import us.courseEnrollmentsystem.data.repositories.StudentRepository;
import us.courseEnrollmentsystem.dtos.requests.RegisterStudentRequest;
import us.courseEnrollmentsystem.exception.StudentException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
public class AuthServiceImplTest {


    @Autowired
    private AuthService authService;

    @Autowired
    private StudentRepository studentRepository;

    private RegisterStudentRequest createStudentRequest;

    @BeforeEach
    public void setUp(){
        studentRepository.deleteAll();
        createStudentRequest = new RegisterStudentRequest();
    }

    @ParameterizedTest
    @NullSource
    public void registerStudentWithNullRequest(RegisterStudentRequest createStudentRequest){
        assertThrows(StudentException.class, () -> authService.registerStudent(createStudentRequest));
    }

    @Test
    public void registerStudentWithEmptyRequest(){
        assertThrows(StudentException.class, () -> authService.registerStudent(createStudentRequest));
    }

    @Test
    public void registerStudentWithIncompleteRequestThrowsStudentException(){
        createStudentRequest.setName("Azeez");
        createStudentRequest.setEmail("az@gmail.com");
        assertThrows(StudentException.class, () -> authService.registerStudent(createStudentRequest));
    }

    @ParameterizedTest
    @ValueSource(strings = {"1","12", "123", "1234", "12345"})
    public void registerStudentWithInvalidPasswordThrowsStudentExceptionTest(String password){
        RegisterStudentRequest createStudentRequest = new RegisterStudentRequest();
        createStudentRequest.setName("Azeez");
        createStudentRequest.setEmail("az@gmail.com");
        createStudentRequest.setDepartment("Biochemistry");
        createStudentRequest.setPassword(password);

        assertThrows(StudentException.class, ()-> authService.registerStudent(createStudentRequest));

    }

    @Test
    public void registerStudentWithValidRequestRepositoryCountIsOneTest(){
        createStudentRequest.setName("Azeez");
        createStudentRequest.setEmail("az@gmail.com");
        createStudentRequest.setDepartment("Biochemistry");
        createStudentRequest.setPassword("123456");

        authService.registerStudent(createStudentRequest);
        assertEquals(1, studentRepository.count());
    }

    @Test
    public void registerTwoStudentSWithValidRequestButWithSameEmailThrowsExceptionTest(){
        createStudentRequest.setName("Azeez");
        createStudentRequest.setEmail("az@gmail.com");
        createStudentRequest.setDepartment("Biochemistry");
        createStudentRequest.setPassword("123456");
        authService.registerStudent(createStudentRequest);

        RegisterStudentRequest createStudentRequest2 = new RegisterStudentRequest();
        createStudentRequest2.setName("Bola");
        createStudentRequest2.setEmail("az@gmail.com");
        createStudentRequest2.setDepartment("chemistry");
        createStudentRequest2.setPassword("123456");
        assertThrows(StudentException.class, () -> authService.registerStudent(createStudentRequest2));

    }



}