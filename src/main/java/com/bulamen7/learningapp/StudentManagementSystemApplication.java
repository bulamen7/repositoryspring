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
        User user = new User("asdasdsa", "sadascxsacas", "92012703631", UserType.STUDENT);
        User user2 = new User("asdasdsadsadas", "sadascxsacas", "92012703631", UserType.ADMIN);
        User user3 = new User("asdazxzzzzzzsdsa", "sadascxsacas", "92012703631", UserType.ADMIN);
        Course course = new Course("Niemiecki", "ruski");
        Course course2 = new Course("Angielski", "ruski");
        course.subscribeTo(user);
        course2.subscribeTo(user2);
        course2.subscribeTo(user3);
        user.subscribeTo(course);
        user.subscribeTo(course2);
        System.out.println(course.getUsers());
        System.out.println(user.getCourses());
    }

}
//TODO API do rejestracji studentow na przedmioty, pobierania studentow zarejestrowanych na dany przedmiot
//TODO  API do pobrania przedmiotow danego studenta
//TODO  API do dodawania ocen dla danego studenta z danego przedmiotu
//TODO  API do zwracania wszystkich ocen danego studenta
//TODO  API do przypisywania nauczycieli do kursow oraz zwracajace kursy danego nauczyciela

//TODO atem system powinien zamodelowac relacje miedzy uzytkownikami a kursami i ocenami.

//TODO  zapisywania studentow na zajecia

//TODO  zwracajace wszystkie przedmioty studenta,

//TODO  zwracajace wszystkich studentow przedmiotu

