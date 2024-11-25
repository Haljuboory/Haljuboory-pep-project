package Service;

import Model.Account;
import DAO.AccountDAO;

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

    public Account addAccount(Account account) {
        Account addAccount = accountDAO.insertAccount(account);
            if (addAccount != null &&  && &&){
                return addAccount;
            }
        return null;
    }

    public Account loginAccount(Account account) {
        Account addAccount = accountDAO.userLogin(account);
            if (addAccount != null){
                return addAccount;
            }
        return null;
    } 




















}
