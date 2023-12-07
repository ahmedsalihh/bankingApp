package org.salih.banking.service;

import org.salih.banking.model.User;

import java.util.List;

public interface UserService {
    User addUser(User user);

    List<User> listUsers();
}
