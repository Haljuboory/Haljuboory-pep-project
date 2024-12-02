package Service;

import DAO.MessageDAO;
import Model.Account;
import Model.Message;

import static org.junit.Assume.assumeNotNull;

import java.util.List;

public class MessageService {
    public MessageDAO messageDAO;

    /**
     * 
     * 
     */
    public MessageService(){
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
        message = messageDAO.addMessage(message);
        try {
            if (!message.message_text.isBlank())
                return message;
             if(message.message_text.length() < 255)
                return message;
             if(message.posted_by == message.message_id)
                return message;
                
            
            } catch (Exception e) {
                System.out.println(e.getMessage());
            
        }
        return messageDAO.addMessage(message);
    }

   
    public List<Message> getAllUserMessages(int posted_by) {
        return messageDAO.getAllUserMessages(posted_by);
    }


    public Message getMessagebyid(int message_id){
        Message message = messageDAO.getMessageById(message_id);
        return message;
    }

    /*
     * The update of a message should be successful if and only if :
     * the message id already exists 
     * and the new message_text is not blank 
     * and is not over 255 characters.
     */
    public Message updateMessage( Message message){
         messageDAO.updateMessage(message);
        
        try {
            if(message.message_id != null){
                return message;
            }
            if (!message.message_text.isBlank())
                return message;
             if(message.message_text.length() < 255)
                return message;
             
                
            
            } catch (Exception e) {
                System.out.println(e.getMessage());
        }
        return messageDAO.updateMessage(message);
    }
        
    
    public  Message deleteMessagebyid(int message){
     messageDAO.deleteMessagebyid(message);
     return null;
    
    }
}
