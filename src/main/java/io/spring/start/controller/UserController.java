package io.spring.start.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import io.spring.start.model.User;
import io.spring.start.services.IRoleService;
import io.spring.start.services.IUserService;

@Controller
@RequestMapping("/user")
public class UserController {
  private IUserService userService;
  private IRoleService roleService;

  // sama seperti instance objek tetapi cukup dipanggil satu kali aja dan bisa
  // dipakai berkali - kali
  @Autowired
  public UserController(IUserService userService, IRoleService roleService) {
    this.userService = userService;
    this.roleService = roleService;
  }

  @GetMapping
  public String index(Model model) {
    // model.addAttribute("users", userService.getAllByRole(3));
    model.addAttribute("usersWithDTO", userService.getUsernameAndPassword(3));
    model.addAttribute("usersWithNative", userService.getDataWithNativeQuery(3));
    return "user/index";
  }

  // form untuk insert dan juga update
  @GetMapping(value = { "form-user", "form-user/{id}" })
  // @pathvariable utk binding parameter yg ada di url
  // requried di path kalo false dia jadi optional
  public String form(Model model, @PathVariable(required = false) String id) {
    if (id != null) {
      model.addAttribute("user", userService.get(id));
      model.addAttribute("roles", roleService.get());
    } else {
      // mengirim objek user dan roles
      // addAttribute utk passing data dari controller ke view
      model.addAttribute("user", new User());
      model.addAttribute("roles", roleService.get());
    }
    return "user/form-user";
  }

  @PostMapping("save")
  public String save(User user) {
    Boolean result = userService.save(user);
    if (result) {
      return "redirect:/user";
    }
    return "user/form-user";
  }

  @GetMapping("delete/{id}")
  public String delete(@PathVariable(required = true) String id) {
    userService.delete(id);
    return "redirect:/user";
  }
}
