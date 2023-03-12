package io.spring.start.controller;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import io.spring.start.dto.Login;
import io.spring.start.model.Employee;
import io.spring.start.model.Role;
import io.spring.start.model.User;
import io.spring.start.services.IEmployeeService;
import io.spring.start.services.IUserService;

@Controller
@RequestMapping("/user")
public class UserManagementController {
  AuthenticationManager authenticationManager;
  private IEmployeeService employeeService;
  private IUserService userService;

  public UserManagementController(AuthenticationManager authenticationManager, IEmployeeService employeeService,
      IUserService userService) {
    this.authenticationManager = authenticationManager;
    this.employeeService = employeeService;
    this.userService = userService;
  }

  @GetMapping("login")
  public String login(Model model) {
    model.addAttribute("login", new Login());
    return "user-management/login";
  }

  @GetMapping("register")
  public String register(Model model) {
    model.addAttribute("user", new User());
    model.addAttribute("employee", new Employee());
    return "user-management/register";
  }

  @PostMapping("register/save")
  public String register(Model model, User user, Employee employee) {
    Boolean result = employeeService.save(employee);
    Role role = new Role();
    role.setId(2);
    if (result) {
      user.setId(employee.getId());
      user.setRole(role);
      userService.save(user);
      return "redirect:/user-management/login";
    }
    return "user-management/register";
  }

  // tambahkan change password,
  // register dan forget
  @PostMapping("authenticate")
  public String login(Login login) {
    Authentication authentication = authenticationManager
        .authenticate(new UsernamePasswordAuthenticationToken(
            login.getEmail(),
            login.getPassword()));

    if (authentication.isAuthenticated()) {
      SecurityContextHolder.getContext().setAuthentication(authentication);
      return "redirect:/employee";
    }
    return "role/index";
  }
}
