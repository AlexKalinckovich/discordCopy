package com.example.demo.Repositories;

import com.example.demo.Models.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    @Query(value = "SELECT * FROM user where username = :username",nativeQuery = true)
    Optional<User> findByUsername(String username);

    @Modifying
    @Query(value = "INSERT INTO user (username, password) VALUES (:username, :password)", nativeQuery = true)
    void saveUser(@Param("username") String username, @Param("password") String password);

}
