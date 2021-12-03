package com.bulamen7.learningapp;

import com.bulamen7.learningapp.model.Course;
import com.bulamen7.learningapp.model.User;
import com.bulamen7.learningapp.model.UserType;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StudentManagementSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudentManagementSystemApplication.class, args);
        User user = new User(1,"Edek","kredek","92012703631", UserType.ADMIN);
        Course course = new Course(1, "asdasdas", "asdasdsa", user);
        user.addCourse(course);
        System.out.println(user.getCourses());
    }
}
