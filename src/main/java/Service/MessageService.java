package Service;

import DAO.MessageDAO;
import Model.Account;
import Model.Message;

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
     * The creation of the message will be successful if and only if the message_text is not blank, 
     * is not over 255 characters, 
     * and posted_by refers to a real, existing user.
     * 
     * 
     */
    public Message addMessage(Message message) {
        message = messageDAO.addMessage(message);
        try {
            if (message.message_text.isBlank() )
                return null;
             if(message.message_text.length() > 255)
                return null;
             if(message.posted_by.equals(account_id))
                return message;
                
            
            } catch (Exception e) {
            // TODO: handle exception
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

    public Message updateMessage( Message message){
        messageDAO.updateMessage(message);
        return null;
    }
    public Message deleteMessagebyid(int message_id){
     messageDAO.deleteMessagebyid(message_id);
     return null;
    
    }
}
