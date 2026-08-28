package us.courseEnrollmentsystem.data.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import us.courseEnrollmentsystem.data.models.Student;

public interface StudentRepository extends MongoRepository<Student,String> {

    Student findByEmail(String email);
}
