package com.capgemini.service;

import java.util.LinkedList;

import com.capgemini.beans.Account;
import com.capgemini.exceptions.InsufficientBalanceException;
import com.capgemini.exceptions.InsufficientOpeningBalanceException;
import com.capgemini.exceptions.InvalidAccountNumberException;
import com.capgemini.repo.AccountRepository;

public class AccountServiceImpl implements AccountService{
	LinkedList<Account> accounts=new LinkedList<>();
    
AccountRepository accountRepository;
	
	
	public AccountServiceImpl(AccountRepository accountRepository) {
		super();
		this.accountRepository = accountRepository;
	}
	
	@Override
public Account createAccount(int accountNumber, int amount) throws InsufficientOpeningBalanceException {
        
        if(amount < 500){
            throw new InsufficientOpeningBalanceException();
        }
        
        Account account = new Account();
        account.setAccountNumber(accountNumber); 
         account.setAmount(amount); 
         if(accountRepository.save(account))
         {
             return account;
         }
        
        
        return null;
    }
    
	
	private Account searchAccount(int accountNumber)throws InvalidAccountNumberException
    {
        
    //  Object accounts;
        for(Account account : accounts)
        {
            if(account.getAccountNumber()==accountNumber)
            {
                return account;
            }
        }
        throw new InvalidAccountNumberException();
        
    }
	public boolean isaccountExist(int accountNumber) throws InvalidAccountNumberException {

        Account accountFound=accountRepository.searchAccount(accountNumber);
        return accountFound == null ? false : true;
        
    }
    
    public void isBalanceThere(int accountNumber) throws InsufficientBalanceException{
        
        
        
    }

    @Override
    public int depositAmount(int accountNumber, int amount) throws InvalidAccountNumberException{
        int updatedAmount = 0;
            if(isaccountExist(accountNumber)){
                updatedAmount = accountRepository.updateBalance(amount);
                updatedAmount = amount + accountRepository.searchAccount(accountNumber).getAmount();
                
                
            }
            else{
                throw new InvalidAccountNumberException();
            }
    
           return updatedAmount;
    }   
      
   
    
      
        
   
    
    @Override
    public int  withdrawAmount(int accountNumber, int amount) throws InvalidAccountNumberException,InsufficientBalanceException{
        int updatedAmount = 0;
    
            if(isaccountExist(accountNumber)){
            
            updatedAmount =  accountRepository.searchAccount(accountNumber).getAmount()- amount;
            if(updatedAmount < 0){
                throw new InsufficientBalanceException();
            }
            
        }
        else{
            throw new InvalidAccountNumberException();
        }
    
        return updatedAmount;
    }
    
    
    
    
    


}
