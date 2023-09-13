package com.nxtdiv.assgn1.usercrudrestapi.clr;

import com.nxtdiv.assgn1.usercrudrestapi.domain.User;
import com.nxtdiv.assgn1.usercrudrestapi.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

//@Component
public class UserClr implements CommandLineRunner {

    private final UserRepository userRepository;

    public UserClr(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        User user1 = new User("Keerthana","Chilupuri","Nxtdiv","xyz@gmail.com", LocalDateTime.now(),"H.No:1-20","Siddipet","45.555,34.23232","Telangana","505467","India","online",true);
        User savedUser1 = userRepository.save(user1);
//        userRepository.save(savedUser1);

        User user2 = new User("Sahithi","Eguda","Nxtdiv","abc@gmail.com", LocalDateTime.now(),"H.No:1-20","Manchirial","45.555,34.23232","Telangana","505427","India","online",false);
        User savedUser2 = userRepository.save(user2);
//        userRepository.save(savedUser2);

        User user3 = new User("Ganesh","Surna","Nxtdiv","mno@gmail.com", LocalDateTime.now(),"H.No:1-20","Jangoan","45.555,34.23232","Telangana","503427","India","offline",true);
        User savedUser3 = userRepository.save(user3);
//        userRepository.save(savedUser3);

        System.out.println("Users Count: " + userRepository.count());
    }
}
