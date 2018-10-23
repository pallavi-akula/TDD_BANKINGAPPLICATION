package com.capgemini.repo;

import com.capgemini.beans.Account;

public interface AccountRepository {
boolean save(Account account);
	
	Account searchAccount(int accountNumber);
	int updateBalance(int amount);
	

}
