package ru.maximen.services;

import org.springframework.stereotype.Service;
import ru.maximen.entity.User;


public interface UserService {

    User findByLogin(String login);
}
