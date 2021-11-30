package com.example.hiddendragon;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.Collection;

import com.example.hiddendragon.User;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface UserRepository extends CrudRepository<User, Integer> {
  @Query("SELECT u FROM User u WHERE u.username = ?1 AND u.password = ?2")
  Collection<User> findUser(String username, String password);
  
}