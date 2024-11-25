package Service;

import DAO.MessageDAO;
import Model.Message;

import java.util.List;

public class MessageService {
    public MessageDAO messageDAO;

    /**
     * No-args constructor for bookService which creates a BookDAO.
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
     * TODO: Use the messageDAO to retrieve all books.
     * @return all books.
     */
    public List<Message> getAllMessages() {
        return messageDAO.getAllMessages();
    }

    /**
     * TODO: Use the messageDAO to persist a book to the database.
     * An Id will be provided in message. Method should check if the message ISBN already exists before it attempts to
     * persist it.
     * @param book a book object.
     * @return book if it was successfully persisted, null if it was not successfully persisted (eg if the book primary
     * key was already in use.)
     * should not br blank 2 more conditions.
     */
    public Message addMessage(Message message) {
        
        
            if(message == messageDAO.getMessageById(message.getMessage_id())){
                
                return null;
                
            }
            
        return messageDAO.insertMessage(message);
    }
    /**
     * TODO: Use the bookDAO to retrieve a list of all books that have a bookCount above 0.
     * @return all available books (bookCount over zero)
     */
    public List<Message> getAllUserMessages(int id) {
        return messageDAO.getAllUserMessages(id);
    }

delete
update 
getMessagebyid
    public get
}
