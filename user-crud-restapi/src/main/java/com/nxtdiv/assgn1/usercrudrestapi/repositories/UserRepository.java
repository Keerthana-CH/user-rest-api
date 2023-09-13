package com.nxtdiv.assgn1.usercrudrestapi.repositories;

import com.nxtdiv.assgn1.usercrudrestapi.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Long> {

}
