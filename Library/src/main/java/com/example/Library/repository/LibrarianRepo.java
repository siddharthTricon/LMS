package com.example.Library.repository;

import com.example.Library.model.Librarian;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibrarianRepo extends JpaRepository<Librarian, Long> {
    Librarian findByName(String name);
}
