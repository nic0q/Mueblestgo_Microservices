package com.tingeso.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tingeso.user.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
}
