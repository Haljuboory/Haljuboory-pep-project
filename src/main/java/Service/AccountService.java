package Service;

import Model.Account;
import DAO.AccountDAO;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;

public class AccountService {
    private AccountDAO accountDAO;
    

    public AccountService(){
        accountDAO = new AccountDAO();
    }

    public AccountService(AccountDAO accountDAO){
        this.accountDAO = accountDAO;
    }

    public List<Account> getAllAccounts() {
        return accountDAO.getAllAccounts();
    }

    /*
     * the username is not blank
     * the password is at least 4 characters long
     * Account with that username does not already exist
     */
    public Account addAccount(Account account)  {
        
        try 
        {
            if (account != null){
                if((account.password.length() > 4)){
                   return account;
                }
                Account acct = accountDAO.getAccountByUserName(account.username);
                if(!account.username.equalsIgnoreCase(acct.username)){
              
                return null;
            }
        } 
        } catch (Exception e) {
         e.printStackTrace();
        }

        Account addAccount = accountDAO.insertAccount(account);
            
                return addAccount;
            }
            
        public Account loginAccount(Account account) {
        Account login = accountDAO.loginAccount(account);
            if (account != null){
                return login;
            }
        return null;
    } 
    }

    





















