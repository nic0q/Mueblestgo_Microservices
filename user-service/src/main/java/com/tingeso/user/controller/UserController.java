package com.tingeso.user.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.tingeso.user.entity.User;
import com.tingeso.user.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
  @Autowired
  UserService userService;

  @GetMapping
  public ResponseEntity<List<User>> getAll() {
    List<User> Justificatives = userService.getAll();
    if (Justificatives.isEmpty()) return ResponseEntity.noContent().build();
    return ResponseEntity.ok(Justificatives);
  }
}
