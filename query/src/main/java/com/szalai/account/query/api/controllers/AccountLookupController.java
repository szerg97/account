package com.szalai.account.query.api.controllers;

import com.szalai.account.core.infrastructure.QueryDispatcher;
import com.szalai.account.query.api.dto.AccountLookupResponse;
import com.szalai.account.query.api.dto.EqualityType;
import com.szalai.account.query.api.queries.FindAccountByHolderQuery;
import com.szalai.account.query.api.queries.FindAccountByIdQuery;
import com.szalai.account.query.api.queries.FindAccountsWithBalanceQuery;
import com.szalai.account.query.api.queries.FindAllAccountsQuery;
import com.szalai.account.query.domain.BankAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.MessageFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping(path = "/api/v1/bank-account-lookup")
public class AccountLookupController {

    private final Logger logger = Logger.getLogger(AccountLookupController.class.getName());

    @Autowired
    private QueryDispatcher queryDispatcher;

    @GetMapping(path = "")
    public ResponseEntity<AccountLookupResponse> getAllAccounts(){
        try{
            List<BankAccount> accounts = queryDispatcher.send(new FindAllAccountsQuery());
            if (accounts == null || accounts.size() == 0){
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }
            AccountLookupResponse response = new AccountLookupResponse(
                    accounts,
                    MessageFormat.format("Successfully returned {0} accounts", accounts.size())
            );

            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            String safeErrorMessage = "Failed to complete to get all bank accounts request";
            logger.log(Level.SEVERE, safeErrorMessage, e);
            return new ResponseEntity<>(new AccountLookupResponse(safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/by-id/{id}")
    public ResponseEntity<AccountLookupResponse> getAccountById(@PathVariable(value = "id") String id){
        try{
            List<BankAccount> accounts = queryDispatcher.send(new FindAccountByIdQuery(id));
            if (accounts == null || accounts.size() == 0){
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }
            AccountLookupResponse response = new AccountLookupResponse(
                    accounts,
                    MessageFormat.format("Successfully returned bank account(s)", accounts.size())
            );

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e){
            String safeErrorMessage = MessageFormat.format("Failed to complete to get account by id - {0}", id);
            logger.log(Level.SEVERE, safeErrorMessage, e);
            return new ResponseEntity<>(new AccountLookupResponse(safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/by-holder/{accountHolder}")
    public ResponseEntity<AccountLookupResponse> getAccountByHolder(@PathVariable(value = "accountHolder") String accountHolder){
        try{
            List<BankAccount> accounts = queryDispatcher.send(new FindAccountByHolderQuery(accountHolder));
            if (accounts == null || accounts.size() == 0){
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }
            AccountLookupResponse response = new AccountLookupResponse(
                    accounts,
                    MessageFormat.format("Successfully returned bank account(s)", accounts.size())
            );

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e){
            String safeErrorMessage = MessageFormat.format("Failed to complete to get account by holder - {0}", accountHolder);
            logger.log(Level.SEVERE, safeErrorMessage, e);
            return new ResponseEntity<>(new AccountLookupResponse(safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/with-balance/{equalityType}/{balance}")
    public ResponseEntity<AccountLookupResponse> getAccountWithBalance(
            @PathVariable(value = "equalityType") EqualityType equalityType,
            @PathVariable(value = "balance") Double balance
    ){
        try{
            List<BankAccount> accounts = queryDispatcher.send(new FindAccountsWithBalanceQuery(equalityType, balance));
            if (accounts == null || accounts.size() == 0){
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }
            AccountLookupResponse response = new AccountLookupResponse(
                    accounts,
                    MessageFormat.format("Successfully returned bank accounts", accounts.size())
            );

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e){
            String safeErrorMessage = MessageFormat.format("Failed to complete to get accounts with balance - {0}", balance);
            logger.log(Level.SEVERE, safeErrorMessage, e);
            return new ResponseEntity<>(new AccountLookupResponse(safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
