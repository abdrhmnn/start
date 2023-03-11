package io.spring.start.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import io.spring.start.model.Employee;
import io.spring.start.model.User;
import io.spring.start.services.IEmployeeService;
import io.spring.start.services.IRoleService;
import io.spring.start.services.IUserService;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
  private IEmployeeService employeeService;
  private IRoleService roleService;
  private IUserService userService;

  public EmployeeController(IEmployeeService employeeService, IRoleService roleService, IUserService userService) {
    this.employeeService = employeeService;
    this.roleService = roleService;
    this.userService = userService;
  }

  @GetMapping
  public String index(Model model) {
    model.addAttribute("employees", employeeService.get());

    return "employee/index";
  }

  @GetMapping(value = { "form-employee", "form-employee/{id}" })
  public String form(Model model, @PathVariable(required = false) Integer id) {
    if (id != null) {
      model.addAttribute("user", userService.get(id));
      model.addAttribute("employee", employeeService.get(id));
      model.addAttribute("roles", roleService.get());
    } else {
      model.addAttribute("user", new User());
      model.addAttribute("employee", new Employee());
      model.addAttribute("roles", roleService.get());
    }
    return "employee/form-employee";
  }

  @PostMapping("save")
  public String save(User user, Employee employee) {
    Boolean result = employeeService.save(employee);
    if (result) {
      user.setId(employee.getId());
      userService.save(user);
      return "redirect:/employee";
    }
    return "employee/form-employee";
  }

  @GetMapping("delete/{id}")
  public String delete(@PathVariable(required = true) Integer id) {
    userService.delete(id);
    employeeService.delete(id);
    return "redirect:/employee";
  }
}
