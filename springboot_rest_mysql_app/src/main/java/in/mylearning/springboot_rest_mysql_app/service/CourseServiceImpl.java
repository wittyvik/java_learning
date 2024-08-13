package in.mylearning.springboot_rest_mysql_app.service;

import in.mylearning.springboot_rest_mysql_app.binding.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import in.mylearning.springboot_rest_mysql_app.repository.CourseRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author Vikas Jadhav
 * Service should communicate with repository
 * Service class method should talk with repository method
 *
 * Repostory should be injected to service using autowired annotation
 */
@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository; // We are injected interface using autowired. There is no implementation as JPA will do that part. JPA will create implementation runtime.

    @Override
    public String upsert(Course course) {
        courseRepository.save(course);
        return "Successfully uploaded course";  // upsert (insert / update based on primary key )
    }

    @Override
    public Course getById(int cid) {
        Optional<Course> findById = courseRepository.findById(cid);
        if (findById.isPresent()) {
            return findById.get();
        }
        return null;
    }

    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public String deleteCourse(int cid) {
        if (courseRepository.existsById(cid)) {
            courseRepository.deleteById(cid);
            return "Successfully deleted course";
        }else{
            return "Course not found";
        }
    }
}
