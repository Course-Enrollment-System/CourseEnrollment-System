package us.courseEnrollmentsystem.services;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import us.courseEnrollmentsystem.data.models.Role;
import us.courseEnrollmentsystem.data.models.Student;
import us.courseEnrollmentsystem.data.repositories.StudentRepository;
import us.courseEnrollmentsystem.dtos.requests.LoginRequest;
import us.courseEnrollmentsystem.dtos.requests.RegisterStudentRequest;
import us.courseEnrollmentsystem.dtos.responses.LoginResponse;
import us.courseEnrollmentsystem.exception.StudentException;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
public class AuthServiceImplTest {


    @Autowired
    private AuthService authService;

    @Autowired
    private StudentRepository studentRepository;

    private RegisterStudentRequest createStudentRequest;
    private LoginRequest loginRequest;

    @BeforeEach
    public void setUp(){
        studentRepository.deleteAll();
        createStudentRequest = new RegisterStudentRequest();
        loginRequest = new LoginRequest();
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

    @Test
    public void loginStudentORAdminWithNullRequestThrowsStudentExceptionTest() {
        assertThrows( StudentException.class, () -> authService.login(null) );
    }

    @Test
    public void loginStudentORAdminWithEmptyRequestThrowsStudentExceptionTest() {
        assertThrows( StudentException.class, () -> authService.login(loginRequest) );
    }

    @Test
    public void loginStudentWithIncompleteRequestThrowsStudentExceptionTest() {
        createStudentRequest.setName("Azeez");
        createStudentRequest.setEmail("az@gmail.com");
        createStudentRequest.setDepartment("Biochemistry");
        createStudentRequest.setPassword("123456");
        authService.registerStudent(createStudentRequest);

        loginRequest.setEmail(createStudentRequest.getEmail());
        assertThrows( StudentException.class, () -> authService.login(loginRequest) );
    }

    @Test
    public void loginAdminWithIncompleteRequestThrowsStudentExceptionTest() {
        loginRequest.setEmail("admin@administration.com");
        assertThrows( StudentException.class, () -> authService.login(loginRequest) );
    }

    @ParameterizedTest
    @ValueSource(strings = {"1","12", "123", "1234", "12345"})
    public void loginStudentWithInvalidPasswordThrowsStudentExceptionTest(String password){
        createStudentRequest.setName("Azeez");
        createStudentRequest.setEmail("az@gmail.com");
        createStudentRequest.setDepartment("Biochemistry");
        createStudentRequest.setPassword("123456");
        authService.registerStudent(createStudentRequest);

        loginRequest.setEmail(createStudentRequest.getEmail());
        loginRequest.setPassword(password);


        assertThrows(StudentException.class, ()-> authService.login(loginRequest) );

    }

    @Test
    public void adminCanLoginTest() {
        loginRequest.setEmail("admin@administration.com");
        loginRequest.setPassword("Administration1234$$");

        LoginResponse response = authService.login(loginRequest);
        assertEquals( "admin@administration.com", response.getEmail() );
        assertEquals( Role.ADMIN, response.getRole());
        assertEquals( "Login successful", response.getMessage() );
    }

    @Test
    public void adminWithWrongPasswordCannotLoginThrowsExceptionTest() {
        loginRequest.setEmail("admin@administration.com");
        loginRequest.setPassword("wrongPassword");
        assertThrows( StudentException.class, () -> authService.login(loginRequest) );
    }

    @Test
    public void studentCanLoginTest() {
        createStudentRequest.setName("Azeez");
        createStudentRequest.setEmail("az@gmail.com");
        createStudentRequest.setDepartment("Biochemistry");
        createStudentRequest.setPassword("123456");

        authService.registerStudent(createStudentRequest);

        loginRequest.setEmail("az@gmail.com");
        loginRequest.setPassword("123456");

        LoginResponse response = authService.login(loginRequest);
        assertEquals( "az@gmail.com", response.getEmail() );
        assertEquals( "Login successful", response.getMessage() );
        Student loggedInStudent = studentRepository.findByEmail(loginRequest.getEmail());
        assertTrue(loggedInStudent.isActive());
    }

    @Test
    public void loginStudentWithWrongPasswordThrowsExceptionTest() {
        createStudentRequest.setName("Azeez");
        createStudentRequest.setEmail("az@gmail.com");
        createStudentRequest.setDepartment("Biochemistry");
        createStudentRequest.setPassword("123456");

        authService.registerStudent(createStudentRequest);

        loginRequest.setEmail("az@gmail.com");
        loginRequest.setPassword("12345678");

        assertThrows(StudentException.class, () -> authService.login(loginRequest));

        Student savedStudent = studentRepository.findByEmail(loginRequest.getEmail());
        assertFalse(savedStudent.isActive());


    }

    @Test
    public void LoginStudentNotFoundThrowsExceptionTest() {
        loginRequest.setEmail("askimolo@gmail.com");
        loginRequest.setPassword("123456");
        assertThrows( StudentException.class, () -> authService.login(loginRequest) );
    }
}