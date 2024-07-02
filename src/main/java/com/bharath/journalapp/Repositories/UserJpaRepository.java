package com.bharath.journalapp.Repositories;

import com.bharath.journalapp.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserJpaRepository extends JpaRepository<User,Long> {

    User findByUsername(String username);

    void deleteByUsername(String username);


}
