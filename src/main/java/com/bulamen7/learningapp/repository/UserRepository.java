package com.bulamen7.learningapp.repository;


import com.bulamen7.learningapp.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Repository
public class UserRepository {
    private Map<Integer, User> idToUser = new HashMap<>();

    public void saveUser(User user) {
        if (idToUser.containsKey(user.getId())) {
            throw new IllegalStateException("Duplicated User");
        }
        idToUser.put(user.getId(), user);
    }

    public User findById(int id) {
        return idToUser.get(id);
    }

    public List<User> findAll() {
        List<User> users = new ArrayList<>(idToUser.values());
        return users;
    }

    public void deleteUserById(int id) {
        idToUser.remove(id);
    }

    public Map<Integer, User> getIdToUser() {
        return idToUser;
    }
}