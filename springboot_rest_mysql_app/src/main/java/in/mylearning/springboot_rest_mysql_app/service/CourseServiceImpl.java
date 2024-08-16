package in.mylearning.springboot_rest_mysql_app.service;

import in.mylearning.springboot_rest_mysql_app.binding.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
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
@CacheConfig(cacheNames = "courses")
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository; // We are injected interface using autowired. There is no implementation as JPA will do that part. JPA will create implementation runtime.

    @Override
    @CacheEvict(value = "courses", key = "#course.cid") // his annotation is used to evict the cache entry for the specific course ID after an upsert operation. This is important because when you update a course, you want to ensure that the cache is invalidated so that the next time the course is fetched, the updated data is retrieved from the database and stored in the cache
    public String upsert(Course course) {
        courseRepository.save(course);
        return "Successfully uploaded course";  // upsert (insert / update based on primary key )
    }

    @Override
    @Cacheable(value = "courses", key = "#cid") //This annotation caches the result of this method based on the course ID (cid). If the course is already cached, it will be returned from the cache instead of hitting the database. This improves performance by reducing the number of database queries for the same data.
    public Course getById(int cid) {
        Optional<Course> findById = courseRepository.findById(cid);
        if (findById.isPresent()) {
            return findById.get();
        }
        return null;
    }

    @Override
    @Cacheable(value = "courses") //This annotation caches the result of this method without specifying a key because the entire list of courses is cached. If this method is called multiple times, the result will be fetched from the cache rather than the database, which can significantly improve performance.
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    @CacheEvict(value = "courses", key = "#cid") // This annotation ensures that when a course is deleted, its corresponding cache entry is also removed. This prevents returning stale data if the deleted course is requested again.
    public String deleteCourse(int cid) {
        if (courseRepository.existsById(cid)) {
            courseRepository.deleteById(cid);
            return "Successfully deleted course";
        }else{
            return "Course not found";
        }
    }
}
