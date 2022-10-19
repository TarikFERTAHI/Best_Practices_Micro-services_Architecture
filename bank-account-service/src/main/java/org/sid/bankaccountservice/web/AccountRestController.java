package org.sid.bankaccountservice.web;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.sid.bankaccountservice.dto.BankAccountRequestDTO;
import org.sid.bankaccountservice.dto.BankAccountResponseDTO;
import org.sid.bankaccountservice.entities.BankAccount;
import org.sid.bankaccountservice.mappers.AccountMapper;
import org.sid.bankaccountservice.repositories.BankAccountRepository;
import org.sid.bankaccountservice.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class AccountRestController {

    @Autowired
    private BankAccountRepository bankAccountRepository;
    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private AccountService accountService;
    @GetMapping("/bankAccounts") // pour acceder a la methode et envoie la reuqete
    public List<BankAccount> bankAccounts(){
        return bankAccountRepository.findAll();
    }
    @GetMapping("/bankAccounts/{id}")
    public BankAccount bankAccounts(@PathVariable String id){
        return bankAccountRepository.findById(id).orElseThrow(()->new RuntimeException(String.format("AAccount %s not found",id)));
    }
    @PostMapping("/bankAccounts")
    public BankAccountResponseDTO save(@RequestBody BankAccountRequestDTO requestDTO) {
        return accountService.addAccount(requestDTO);
    }

    @PutMapping ("/bankAccounts/{id}")
    public BankAccount update(@PathVariable String id, @RequestBody BankAccount bankAccount) {
        BankAccount account = bankAccountRepository.findById(id).orElseThrow();
        if (bankAccount.getBalance()!= null) account.setBalance(bankAccount.getBalance());
        if (bankAccount.getCreatedDate()!= null) account.setCreatedDate(new Date());
        if (bankAccount.getAccountType()!= null) account.setAccountType(bankAccount.getAccountType());
        if (bankAccount.getCurrency()!= null) account.setCurrency(bankAccount.getCurrency());
        return bankAccountRepository.save(account);
    }

    @DeleteMapping("/bankAccounts/{id}")
    public void deleteAccount(@PathVariable String id){
        bankAccountRepository.deleteById(id);
    }

}
