package in.mylearning.springboot_rest_mysql_app.service;

import in.mylearning.springboot_rest_mysql_app.binding.Course;

import java.util.List;

/**
 * @author Vikas Jadhav
 */
public interface CourseService {

    public String upsert(Course course); // Method to insert and update

    public Course getById(int cid);

    public List<Course> getAllCourses();

    public String deleteCourse(int cid);




}
