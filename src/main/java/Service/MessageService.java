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
     * 
     * should not be blank 2 more conditions.
     */
    public Message addMessage(Message message) {
        
        
            if(message == messageDAO.getMessageById(message.getMessage_id())){
                
                return null;
                
            }
            
        return messageDAO.insertMessage(message);
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
