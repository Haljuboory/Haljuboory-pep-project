package Service;

import DAO.MessageDAO;
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
    /**
     * Constructor for a MessageService when a MessageDAO is provided.
     * This is used for when a mock MessageDAO that exhibits mock behavior is used in the test cases.
     * This would allow the testing of MessageService independently of MessageDAO.
     * 
     * @param bookDAO
     */
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
   
    public List<Message> getAllUserMessages(int id) {
        return messageDAO.getAllUserMessages(id);
    }

delete
update 
getMessagebyid
    public get
}
