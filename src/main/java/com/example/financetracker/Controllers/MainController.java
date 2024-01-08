package com.example.financetracker.Controllers;

import com.example.financetracker.Models.TransactionEnt;
import com.example.financetracker.Models.UserEnt;
import com.example.financetracker.Repositories.UserRepo;
import com.example.financetracker.Services.TransactionService;

import jakarta.servlet.http.HttpSession;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class MainController {

  @Autowired
  private TransactionService transactionService;
  @Autowired
  private UserRepo userRepo;

  @GetMapping("/Main")
  public String showMainPage(Model model, HttpSession session) {
    List<TransactionEnt> transactions = transactionService.getAllTransactions();
    model.addAttribute("transactions", transactions);

    UserEnt authenticatedUser = (UserEnt) session.getAttribute("authenticatedUser");
    model.addAttribute("authenticatedUser", authenticatedUser);

    model.addAttribute("lastTransaction", transactions.isEmpty() ? null : transactions.get(transactions.size() - 1));

    return "Main";
  }

  @PostMapping("/addTransaction")
  public String addTransaction(@RequestParam String item, @RequestParam double amount,
      @RequestParam double balanceValue, HttpSession session) {
    UserEnt authenticatedUser = (UserEnt) session.getAttribute("authenticatedUser");

    TransactionEnt transactionEnt = new TransactionEnt(item, amount);
    transactionEnt.setTransactionUserEnt(authenticatedUser);
    System.out.println(authenticatedUser);

    transactionService.saveTransaction(transactionEnt);

    authenticatedUser.setBalance(balanceValue - amount);
    userRepo.save(authenticatedUser);

    return "redirect:/Main";
  }

  @PostMapping("/updateBalance")
  public String updateBalance(@RequestParam double currentBalance, HttpSession session) {

    UserEnt authenticatedUser = (UserEnt) session.getAttribute("authenticatedUser");
    System.out.println(authenticatedUser);
    authenticatedUser.setBalance(currentBalance);
    userRepo.save(authenticatedUser);

    return "redirect:/Main";
  }

  @PostMapping("/deleteLastTransaction")
public String deleteLastTransaction(HttpSession session) {
    UserEnt authenticatedUser = (UserEnt) session.getAttribute("authenticatedUser");

    List<TransactionEnt> transactions = transactionService.getAllTransactions();
    if (!transactions.isEmpty()) {
        TransactionEnt lastTransaction = transactions.get(transactions.size() - 1);
        authenticatedUser.setBalance(authenticatedUser.getBalance() + lastTransaction.getAmount());
        userRepo.save(authenticatedUser);
        transactionService.deleteTransaction(lastTransaction.getId());
    }

    return "redirect:/Main";
}

 
}


