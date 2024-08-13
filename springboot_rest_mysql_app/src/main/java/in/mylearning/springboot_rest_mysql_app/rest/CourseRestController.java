package in.mylearning.springboot_rest_mysql_app.rest;

import in.mylearning.springboot_rest_mysql_app.binding.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import in.mylearning.springboot_rest_mysql_app.service.CourseService;

import java.util.List;

/**
 * @author Vikas Jadhav
 * Restcontroller methods should talk to Service *, hence  * Service should be injected into restcontroller
 * Service will talk to repository, hence repository is injected into service
 *
 */
@RestController
public class CourseRestController {

    @Autowired
    private CourseService courseService; // Using courseService interface variable. IOC container will find implementation class for this interface and inject to the rest controller.

    @PostMapping("/course")
    public ResponseEntity<String> addCourse(@RequestBody Course course) {
        String status =  courseService.upsert(course);
        return new ResponseEntity<>(status, HttpStatus.CREATED);
    }
    @GetMapping("/course/{cid}")
    public ResponseEntity<Course> getCourseById(@PathVariable int cid) {
       Course course = courseService.getById(cid);
       return new ResponseEntity<>(course, HttpStatus.OK);
    }

    @GetMapping("/courses")
    public ResponseEntity<List<Course>> getAllCourses() {
        List<Course> courses = courseService.getAllCourses();
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

    @PutMapping("/course")
    public ResponseEntity<String> updateCourse(@RequestBody Course course) {
        String status = courseService.upsert(course);
        return new ResponseEntity<>(status, HttpStatus.OK);

    }

    @DeleteMapping("/course/{cid}")
    public ResponseEntity<String> deleteCourse(@PathVariable int cid) {
        courseService.deleteCourse(cid);
        return new ResponseEntity<>("Course deleted", HttpStatus.OK);
    }

}
