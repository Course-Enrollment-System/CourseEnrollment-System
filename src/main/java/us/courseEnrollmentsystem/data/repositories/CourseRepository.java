package us.courseEnrollmentsystem.data.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import us.courseEnrollmentsystem.data.models.Course;

public interface CourseRepository extends MongoRepository<Course, String> {
}
