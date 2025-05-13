package com.gagansp1.myfirstsringboot.repository;

import com.gagansp1.myfirstsringboot.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


public interface UserRepository extends MongoRepository<User, ObjectId> {

    //we can make our functions and query method derivation to automatically generate MongoDB queries based on method names.
    User findByUserName(String username);

    User deleteByUserName(String username);

}
