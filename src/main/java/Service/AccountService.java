package Service;

import Model.Account;
import DAO.AccountDAO;


public class AccountService {
    AccountDAO accountDAO;

    public AccountService(){
        accountDAO = new AccountDAO();
    }

    public AccountService(AccountDAO accountDAO){
        this.accountDAO = accountDAO;
    }

    //Adding account
    public Account addAccount(Account account){
        if(accountDAO.getAccountByUsername(account.getUsername()) != null){ // account with username does already exist
            return null;
        }else{// account with username does not exist
            if(!account.getUsername().isBlank() && account.getPassword().length() >= 4){ // account username is not blank and password length is at least 4
                return accountDAO.insertAccount(account);
            }else{
                return null;
            }
            
        }
        
    }
    
}
