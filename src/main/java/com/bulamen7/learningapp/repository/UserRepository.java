package com.bulamen7.learningapp.repository;


import com.bulamen7.learningapp.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class UserRepository {
    private   Map<Integer, User> db = new HashMap<>();

    public void saveUser(User user) {
        db.put(user.getId(), user);
        //        if (!db.containsKey(user.getId())) {
//            db.put(user.getId(), user);
//        }
    }

    public User findById(int id) {
        return db.get(id);
    }

    public List<User> findAll() {
        List<User> users = new ArrayList<>(db.values());
        return users;
    }

    public void deleteUserById(int id) {
        db.remove(id);
    }

    public Map<Integer, User> getDb() {
        return db;
    }
}