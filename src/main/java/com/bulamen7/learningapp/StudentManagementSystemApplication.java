package com.bulamen7.learningapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StudentManagementSystemApplication {
//    @Autowired
//    UserService userService;
//    @Autowired
//    UserRepository userRepository;
//    @Autowired
//    CourseRepository courseRepository;
//    @Autowired
//    CourseService courseService;

    public static void main(String[] args) {
        SpringApplication.run(StudentManagementSystemApplication.class, args);

    }

//    @Override
//    public void run(String... args) throws Exception {
//        User user2 = new User(1, "Marek", "Swiok", "90012703631", UserType.LECTURER);
//        userRepository.save(user2);
//        Course course = new Course();
//        courseRepository.save(course);
//        userRepository.save(user2.subscribeTo(course));
//    }
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

