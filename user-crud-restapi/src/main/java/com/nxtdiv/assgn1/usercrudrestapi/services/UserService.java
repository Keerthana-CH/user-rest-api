package com.nxtdiv.assgn1.usercrudrestapi.services;


import com.nxtdiv.assgn1.usercrudrestapi.domain.User;

import java.util.Map;

public interface UserService {

    Iterable<User> findAll();

    void saveUser(User user);

    User deleteById(Long id);

    User updateUserById(User user, Long id);

    User updatePartialUserById(Long id, Map<Object, Object> fields);

    User findById(Long id);
}
