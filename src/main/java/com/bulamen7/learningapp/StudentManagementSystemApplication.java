package com.bulamen7.learningapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StudentManagementSystemApplication  {
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
/*A teraz Projekt.
Projekt będzie polegał na napisaniu serwisu restowego w javie do zarządzania studentami na uczelni. Serwis bedzie mial 3 rodzaje uzytkownikow - student, lecturer (wykladowca) oraz admin.
Użytkownik ma identyfikator (autoincrement), login, haslo, imie, nazwisko oraz pesel. Studentci beda przypisywani do przedmiotow (courses) ktore powinny miec identyfikator( autoincrement), nazwe, opis.
Co powinien zawierac system:
- Modele User, Course
- API logowania (na razie pomijamy i wysylamy requesty bez autoryzacji)
- API do tworzenia, usuwania i pobierania uzytkownikow.
- API do tworzenia usuwania i pobierania przedmiotow. Course (przedmiot) ma identyfikator( autoincrement), nazwe, opis
- API do rejestracji studentow na przedmioty, pobierania studentow zarejestrowanych na dany przedmiot
- API do pobrania przedmiotow danego studenta
- API do dodawania ocen dla danego studenta z danego przedmiotu
- API do zwracania wszystkich ocen danego studenta
- API do przypisywania nauczycieli do kursow oraz zwracajace kursy danego nauczyciela

Zatem system powinien zamodelowac relacje miedzy uzytkownikami a kursami i ocenami. */