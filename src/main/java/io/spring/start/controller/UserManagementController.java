package io.spring.start.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import io.spring.start.dto.ChangePassword;
import io.spring.start.dto.ForgetPasssword;
import io.spring.start.dto.Login;
import io.spring.start.model.Employee;
import io.spring.start.model.Role;
import io.spring.start.model.User;
import io.spring.start.repositories.IManagementRepository;
import io.spring.start.services.IEmployeeService;
import io.spring.start.services.IUserService;

@Controller
@RequestMapping("/user")
public class UserManagementController {
  private AuthenticationManager authenticationManager;
  private IEmployeeService employeeService;
  private IUserService userService;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private IManagementRepository managementRepository;

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
      user.setPassword(passwordEncoder.encode(user.getPassword()));
      userService.save(user);
      return "redirect:/user-management/login";
    }
    return "user-management/register";
  }

  @PostMapping("authenticate")
  public String login(Login login) {
    Authentication authentication = authenticationManager
        .authenticate(new UsernamePasswordAuthenticationToken(
            login.getEmail(),
            login.getPassword()));

    if (authentication.isAuthenticated()) {
      SecurityContextHolder.getContext().setAuthentication(authentication);
      return "redirect:/";
    }
    return "redirect:/user-management/login";
  }

  // change password
  @GetMapping("change-password")
  public String changePassword(Model model) {
    model.addAttribute("cp", new ChangePassword());

    return "user-management/change-password";
  }

  @PostMapping("change-password/save")
  public String saveChangePassword(ChangePassword cp, Principal principal) {
    io.spring.start.model.User data = managementRepository.Login(principal.getName());
    Boolean matchingPassword = passwordEncoder.matches(cp.getOldPassword(), data.getPassword());
    if (matchingPassword) {
      User user = userService.get(data.getId());
      user.setPassword(passwordEncoder.encode(cp.getNewPassword()));
      userService.save(user);
      return "redirect:/";
    } else {
      return "redirect:/user/change-password";
    }
  }

  // forget password
  @GetMapping("forget-password")
  public String forgetPassword(Model model) {
    model.addAttribute("employee", new ForgetPasssword());

    return "user-management/forget-password";
  }

  @PostMapping("forget-password/save")
  public String forgetPasswordSave(Model model, ForgetPasssword forgetPasssword) {
    User data = managementRepository.Login(forgetPasssword.getEmail());
    if (data != null) {
      if (forgetPasssword.getNewPassword() == null) {
        if (data.getEmployee().getEmail().equals(forgetPasssword.getEmail())) {
          model.addAttribute("reset", forgetPasssword);
          return "user-management/update-password";
        }
      } else if (forgetPasssword.getNewPassword().equals(forgetPasssword.getConfirmationPassword())) {
        User user = userService.get(data.getId());
        user.setPassword(passwordEncoder.encode(forgetPasssword.getNewPassword()));
        userService.save(user);
        return "redirect:/user-management/login";
      }
    }

    return "redirect:/user-management/forget-password";
  }
}
