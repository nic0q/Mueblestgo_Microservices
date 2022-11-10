package com.tingeso.user.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tingeso.user.entity.User;
import com.tingeso.user.repository.UserRepository;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  public List<User> getAll() {
    return userRepository.findAll();
  }
}
