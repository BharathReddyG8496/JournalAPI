package com.bharath.journalapp.Repositories;

import com.bharath.journalapp.Entities.Journal;
import com.bharath.journalapp.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JournalJpaRepository extends JpaRepository<Journal, Long> {

}
