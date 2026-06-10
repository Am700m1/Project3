package org.example.project3.Service;

import lombok.RequiredArgsConstructor;
import org.example.project3.Api.ApiException;
import org.example.project3.Model.Account;
import org.example.project3.Model.Customer;
import org.example.project3.Model.User;
import org.example.project3.Repository.AccountRepository;
import org.example.project3.Repository.AuthRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    private final AuthRepository authRepository;

    public List<Account> getAccounts(){
        return accountRepository.findAll();
    }

    public void createAccount(Integer userId, Account account){
        User user = authRepository.findUserById(userId);

        if(user == null){
            throw new ApiException("User not found!");
        }

        if(user.getCustomer() == null){
            throw new ApiException("Customer not found!");
        }

        account.setCustomer(user.getCustomer());
        account.setIsActive(false);
        accountRepository.save(account);
    }

    public void updateAccount(Integer accountId, Account account){
        Account oldAccount = accountRepository.findAccountById(accountId);

        if(oldAccount == null){
            throw new ApiException("Account not found!");
        }

        oldAccount.setAccountNumber(account.getAccountNumber());
        oldAccount.setBalance(account.getBalance());
        accountRepository.save(oldAccount);
    }

    public void deleteAccount(Integer accountId){
        Account account = accountRepository.findAccountById(accountId);

        if(account == null){
            throw new ApiException("Account not found!");
        }

        accountRepository.delete(account);
    }

    public void activeAccount(Integer accountId){
        Account account = accountRepository.findAccountById(accountId);

        if(account == null){
            throw new ApiException("Account not found!");
        }

        account.setIsActive(true);
        accountRepository.save(account);
    }

    public Account getAccountDetails(Integer userId, Integer accountId){
        Account account = accountRepository.findAccountById(accountId);

        if(account == null){
            throw new ApiException("Account not found!");
        }

        if(account.getCustomer() == null){
            throw new ApiException("Customer not found!");
        }

        if(!account.getCustomer().getId().equals(userId)){
            throw new ApiException("Sorry, you do not have the authority to view this account");
        }

        return account;
    }

    public List<Account> getMyAccounts(Integer userId){
        User user = authRepository.findUserById(userId);

        if(user == null){
            throw new ApiException("User not found!");
        }

        Customer customer = user.getCustomer();

        if(customer == null){
            throw new ApiException("Customer not found!");
        }

        List<Account> accounts = accountRepository.findAccountsByCustomer(customer);

        if(accounts.isEmpty()){
            throw new ApiException("No accounts were found for this user!");
        }

        return accounts;
    }

    public void deposit(Integer userId, Integer accountId, Integer amount){
        checkAmount(amount);
        Account account = getAccountDetails(userId, accountId);
        checkAccount(account);

        account.setBalance(account.getBalance() + amount);
        accountRepository.save(account);
    }

    public void withdraw(Integer userId, Integer accountId, Integer amount){
        checkAmount(amount);
        Account account = getAccountDetails(userId, accountId);
        checkAccount(account);

        if(account.getBalance() < amount){
            throw new ApiException("Insufficient balance!");
        }

        account.setBalance(account.getBalance() - amount);
        accountRepository.save(account);
    }

    public void transfer(Integer userId, Integer fromAccountId, Integer toAccountId, Integer amount){
        checkAmount(amount);
        Account fromAccount = getAccountDetails(userId, fromAccountId);
        Account toAccount = accountRepository.findAccountById(toAccountId);

        if(toAccount == null){
            throw new ApiException("Receiver account not found!");
        }

        checkAccount(fromAccount);
        checkAccount(toAccount);

        if(fromAccount.getBalance() < amount){
            throw new ApiException("Insufficient balance!");
        }

        fromAccount.setBalance(fromAccount.getBalance() - amount);
        toAccount.setBalance(toAccount.getBalance() + amount);

        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);
    }

    private void checkAmount(Integer amount){
        if(amount == null || amount <= 0){
            throw new ApiException("Amount must be positive!");
        }
    }

    public void blockAccount(Integer accountId){
        Account account = accountRepository.findAccountById(accountId);

        if(account == null){
            throw new ApiException("Account not found!");
        }

        account.setIsActive(false);
        accountRepository.save(account);
    }

    private void checkAccount(Account account){
        if(!account.getIsActive()){
            throw new ApiException("Account is not active!");
        }
    }
}
