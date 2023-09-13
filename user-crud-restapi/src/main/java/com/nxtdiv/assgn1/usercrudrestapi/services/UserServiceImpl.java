package com.nxtdiv.assgn1.usercrudrestapi.services;

import com.nxtdiv.assgn1.usercrudrestapi.domain.User;
import com.nxtdiv.assgn1.usercrudrestapi.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public User deleteById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()){
            return null;
        }
        userRepository.delete(user.get());
        return user.get();
    }

    @Override
    public User updateUserById(User user, Long id) {
        User save = userRepository.save(user);
        return save;
    }

    @Override
    public User updatePartialUserById(Long id, Map<Object, Object> fields) {
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            User user1 = user.get();
            fields.forEach((key,value)->{
                Field field = ReflectionUtils.findField(User.class,(String) key);
                field.setAccessible(true);
                switch ((String)key) {
                    case "createdTime":
                        // Input String representing a date and time
                        String dateString = (String) value;
                        String dateString1 = dateString.substring(0, 19);

                        // Define the pattern that matches the input String format
                        String pattern = "yyyy-MM-dd'T'HH:mm:ss";

                        // Create a DateTimeFormatter with the specified pattern
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
                        // Parse the String to LocalDateTime
                        LocalDateTime localDateTime = LocalDateTime.parse(dateString1, formatter);
                        ReflectionUtils.setField(field,user1,localDateTime);
                        break;
                    default:
                        ReflectionUtils.setField(field,user1,value);
                        break;
                }

            });

            return userRepository.save(user1);
        }
        return null;
    }

    @Override
    public User findById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()){
            return optionalUser.get();
        }
        return null;
    }


}
