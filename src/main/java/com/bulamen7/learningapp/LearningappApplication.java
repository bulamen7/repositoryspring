package com.bulamen7.learningapp;

import com.bulamen7.learningapp.model.User;
import com.bulamen7.learningapp.model.UserType;
import com.bulamen7.learningapp.repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LearningappApplication {

    public static void main(String[] args) {
        SpringApplication.run(LearningappApplication.class, args);
        UserRepository userService = new UserRepository();
        User user = new User(1,"al", "al", "sadsadsa", UserType.TEACHER);
        User user1 = new User(2,"eeeeeal", "al", "sadsadsa", UserType.TEACHER);
        User user2 = new User(3,"sssss", "al", "sadsadsa", UserType.TEACHER);
        User user3 = new User(4,"eeeezzzzeal", "al", "sadsadsa", UserType.TEACHER);
        User user4 = new User(55,"zzzzzz", "al", "sadsadsa", UserType.TEACHER);
        userService.saveUser(user);
        userService.saveUser(user1);
        userService.saveUser(user2);
        userService.saveUser(user3);
        userService.saveUser(user4);

        System.out.println(userService.findAll());
        userService.deleteUserById(1);
        System.out.println(userService.findAll());
        userService.deleteUserById(4);

        System.out.println(userService.findAll());


    }


}
