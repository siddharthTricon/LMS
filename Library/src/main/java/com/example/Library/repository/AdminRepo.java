package com.example.Library.repository;

import com.example.Library.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AdminRepo extends JpaRepository<Admin, Long> {


    Admin findByName(String name);
}
