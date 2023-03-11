package io.spring.start.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import io.spring.start.model.User;
import io.spring.start.repositories.IManagementRepository;

@Controller
@RequestMapping("/user")
public class UserManagementController {
  IManagementRepository managementRepository;

  public UserManagementController(IManagementRepository managementRepository) {
    this.managementRepository = managementRepository;
  }

  @GetMapping("login")
  public String login(Model model) {
    model.addAttribute("user", new User());
    return "user-management/login";
  }

  // tambahkan change password, register dan forget
}
