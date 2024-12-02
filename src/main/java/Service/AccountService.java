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
     * and an Account with that username does not already exist
     */
    public Account addAccount(Account account)  {
        
        
            if (account.getPassword().length() < 4 || account.getUsername().isBlank()){
                   return null;
                }
            //Account acct = accountDAO.getAccountByUserName(account.username);
            List<Account> a  = getAllAccounts();
            for(Account ac : a){
                if(ac.getAccount_id() == account.getAccount_id()){
                    return null;
                }
            }

            Account addAccount = accountDAO.insertAccount(account);
                return addAccount;
    }

            
    public Account loginAccount(Account account) {
        Account login = accountDAO.loginAccount(account);
            if (login != null){
                return login;
            }
        return null;
    }
 
}

    





















