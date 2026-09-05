package us.courseEnrollmentsystem.data.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import us.courseEnrollmentsystem.data.models.Student;


import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
public class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;



    @BeforeEach
    public void setUp(){
        studentRepository.deleteAll();
    }

    @Test
    public void createStudentCountIsOneTest(){
        Student student = new Student();
        student.setName("Azeez");
        student.setEmail("az@gmail.com");
        student.setDepartment("Biochemistry");
        student.setPassword("12346");

        studentRepository.save(student);

        assertEquals(1, studentRepository.count());
    }

    @Test
    public void createStudent_findByEmailTest(){
        Student student = new Student();
        student.setName("Azeez");
        student.setEmail("az@gmail.com");
        student.setDepartment("Biochemistry");
        student.setPassword("12346");

        studentRepository.save(student);

        assertEquals(student.getName(), studentRepository.findByEmail(student.getEmail()).getName());

    }

}