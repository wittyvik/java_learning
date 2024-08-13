package in.mylearning.springboot_rest_mysql_app.repository;

import in.mylearning.springboot_rest_mysql_app.binding.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * @author Vikas Jadhav
 */

@Repository // Its optional
public interface CourseRepository extends JpaRepository<Course, Serializable> {

}
