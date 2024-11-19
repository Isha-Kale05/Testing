package com.example.online.exam.system;

import com.example.online.exam.system.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Custom query methods can be added here if needed
	 User findByEmail(String email); 
}