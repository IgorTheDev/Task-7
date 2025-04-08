package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

import javax.persistence.EntityManager;
import java.util.List;

@Controller
@RequestMapping("/")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String users(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "user-list";
    }

    @GetMapping("/new")
    public String newUserForm(Model model) {
        model.addAttribute("user", new User());
        return "new-user";
    }

    @PostMapping("/")
    public String newUser(@ModelAttribute("user") User user) {
        userService.add(user);
        return "redirect:/";
    }


  @GetMapping("/edit/{id}")
  public String edit(@PathVariable Long id, Model model) {
    User user=userService.findById(id);
        if (user != null) {
            model.addAttribute("user", user);
            return "new-user";
        }
        return "redirect:/";
  }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.delete(id);
       return "redirect:/";



    }
}
