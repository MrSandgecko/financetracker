package com.example.financetracker.Controllers;

import com.example.financetracker.Models.UserEnt;
import com.example.financetracker.Services.LoginService;
import com.example.financetracker.Services.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

  private final LoginService loginService;

  private final UserService userService;

  @Autowired
  public LoginController(LoginService loginService, UserService userService) {
    this.loginService = loginService;
    this.userService = userService;
  }

  @GetMapping("/Login")
  public String showLoginTab() {
    return "login";
  }

  @PostMapping("/Login")
  public String AuthenticateUser(
      @RequestParam String username, @RequestParam String password, HttpSession session, Model model) {
    if (username == null ||
        username.isEmpty() ||
        password == null ||
        password.isEmpty()) {
      System.out.println(
          model.addAttribute(
              "Login error",
              "Invalid username or password. Please enter valid credentials."));
      return "Login";
    }

    if (loginService.checkCreds(username, password)) {
      UserEnt authenticatedUser = userService.getUserByUsername(username);

      session.setAttribute("authenticatedUser", authenticatedUser);

      return "redirect:/Main";
    } else {
      System.out.println("Invalid username or password. LOL");
      return "Login";
    }
  }
}
