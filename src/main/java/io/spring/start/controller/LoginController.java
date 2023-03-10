package io.spring.start.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import io.spring.start.repositories.IManagementRepository;

@Controller
@RequestMapping("/login")
public class LoginController {
  IManagementRepository managementRepository;

  public LoginController(IManagementRepository managementRepository) {
    this.managementRepository = managementRepository;
  }

  @GetMapping
  public String index(Model model) {
    // model.addAttribute("roles", managementRepository.Login());

    // return path view nya
    return "role/index";
  }
}
