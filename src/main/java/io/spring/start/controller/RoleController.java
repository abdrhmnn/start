package io.spring.start.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import io.spring.start.model.Role;
import io.spring.start.services.IRoleService;

@Controller
@RequestMapping("/role")
public class RoleController {
  private IRoleService roleService;

  public RoleController(IRoleService roleService) {
    this.roleService = roleService;
  }

  @GetMapping
  public String index(Model model) {
    model.addAttribute("roles", roleService.get());

    return "role/index";
  }

  @GetMapping(value = { "form-role", "form-role/{id}" })
  public String form(Model model, @PathVariable(required = false) Integer id) {
    if (id != null) {
      model.addAttribute("roles", roleService.get(id));
    } else {
      model.addAttribute("roles", new Role());
    }
    return "role/form-role";
  }

  @PostMapping("save")
  public String save(Role role) {
    Boolean result = roleService.save(role);
    if (result) {
      return "redirect:/role";
    }
    return "role/form-role";
  }

  @GetMapping("delete/{id}")
  public String delete(@PathVariable(required = true) Integer id) {
    roleService.delete(id);
    return "redirect:/role";
  }
}
