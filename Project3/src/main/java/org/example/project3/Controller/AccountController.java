package org.example.project3.Controller;

import lombok.RequiredArgsConstructor;
import org.example.project3.Api.ApiResponse;
import org.example.project3.Model.Account;
import org.example.project3.Model.User;
import org.example.project3.Service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping("/get-all")
    public ResponseEntity<?> getAllAccounts(){
        return ResponseEntity.status(HttpStatus.OK).body(accountService.getAccounts());
    }

    @PostMapping("/create")
    public ResponseEntity<?> createAccount(@AuthenticationPrincipal User user, @RequestBody Account account){
        accountService.createAccount(user.getId(), account);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("New Account added !"));
    }

    @PutMapping("/update/{accountId}")
    public ResponseEntity<?> updateAccount(@PathVariable Integer accountId, @RequestBody Account account){
        accountService.updateAccount(accountId, account);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Account updated "));
    }

    @DeleteMapping("/delete/{accountId}")
    public ResponseEntity<?> deleteAccount(@PathVariable Integer accountId){
        accountService.deleteAccount(accountId);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Account deleted "));
    }

    @PutMapping("/active/{accountId}")
    public ResponseEntity<?> activeAccount(@PathVariable Integer accountId){
        accountService.activeAccount(accountId);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Account activated "));
    }

    @GetMapping("/details/{accountId}")
    public ResponseEntity<?> getAccountDetails(@AuthenticationPrincipal User user, @PathVariable Integer accountId){
        return ResponseEntity.status(HttpStatus.OK).body(accountService.getAccountDetails(user.getId(), accountId));
    }

    @GetMapping("/my-accounts")
    public ResponseEntity<?> getMyAccounts(@AuthenticationPrincipal User user){
        return ResponseEntity.status(HttpStatus.OK).body(accountService.getMyAccounts(user.getId()));
    }

    @PutMapping("/deposit/{accountId}/{amount}")
    public ResponseEntity<?> deposit(@AuthenticationPrincipal User user, @PathVariable Integer accountId, @PathVariable Integer amount){
        accountService.deposit(user.getId(), accountId, amount);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Money deposited "));
    }

    @PutMapping("/withdraw/{accountId}/{amount}")
    public ResponseEntity<?> withdraw(@AuthenticationPrincipal User user, @PathVariable Integer accountId, @PathVariable Integer amount){
        accountService.withdraw(user.getId(), accountId, amount);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Money withdrawn "));
    }

    @PutMapping("/transfer/{fromAccountId}/{toAccountId}/{amount}")
    public ResponseEntity<?> transfer(@AuthenticationPrincipal User user, @PathVariable Integer fromAccountId, @PathVariable Integer toAccountId, @PathVariable Integer amount){
        accountService.transfer(user.getId(), fromAccountId, toAccountId, amount);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Money transferred "));
    }

    @PutMapping("/block/{accountId}")
    public ResponseEntity<?> blockAccount(@PathVariable Integer accountId){
        accountService.blockAccount(accountId);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Account blocked "));
    }
}
