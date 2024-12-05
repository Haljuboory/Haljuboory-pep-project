package Service;

import DAO.AccountDAO;
import DAO.MessageDAO;
import Model.Account;
import Model.Message;

import static org.junit.Assume.assumeNotNull;

import java.util.List;

public class MessageService {
    public MessageDAO messageDAO;
    public AccountDAO accountDAO;

    /**
     * 
     * 
     */
    public MessageService(){
        accountDAO = new AccountDAO();
        messageDAO = new MessageDAO();
    }
    
    public MessageService(MessageDAO messageDAO){
        this.messageDAO = messageDAO;
    }
    /**
     * 
     * 
     */
    public List<Message> getAllMessages() {
        return messageDAO.getAllMessages();
    }

    /**
     * The creation of the message will be successful if and only if 
     * the message_text is not blank, 
     * is not over 255 characters, 
     * and posted_by refers to a real, existing user.
     * 
     * 
     */
    public Message addMessage(Message message) {
        //message = messageDAO.addMessage(message);
       
            if (!message.message_text.isBlank() && 

            message.message_text.length() < 255 &&

            accountDAO.getAccountById(message.getPosted_by())){
                return messageDAO.addMessage(message);
             }
             return null;
    }   

   
    public List<Message> getAllUserMessages(int posted_by) {
        return messageDAO.getAllUserMessages(posted_by);
    }


    public Message getMessagebyid(int message_id){
        return messageDAO.getMessageById(message_id);
         
    }

    /*
     * The update of a message should be successful if and only if :
     * the message id already exists 
     * and the new message_text is not blank 
     * and is not over 255 characters.
     */
    public Message updateMessage( Message message, int id){
         Message message1 = messageDAO.getMessageById(id);
        if(message1 != null && !message.message_text.isBlank() 
                            && message.message_text.length() < 255){
              messageDAO.updateMessage(message, id);
        return messageDAO.getMessageById(id);                  
        }
        return null;
        
    }
        
    
    public  Message deleteMessagebyid(int message){
        Message message1 = messageDAO.getMessageById(message);
        if(message1 != null){
           return messageDAO.deleteMessagebyid(message); 
        }
     
     return null;
    
    }
}
