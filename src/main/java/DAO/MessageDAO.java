package DAO;

import Model.Message;
import Util.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MessageDAO {
    /**
     * 
     */
    public List<Message> getAllMessages(){
        Connection connection = ConnectionUtil.getConnection();
        List<Message> messages = new ArrayList<>();
        try {
            
            String sql = "SELECT * FROM message;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                Message message = new Message(rs.getInt("message_id"),
                        rs.getInt("posted_by"),
                        rs.getString("message_text"),
                        rs.getLong("time_posted_epoch"));
                messages.add(message);
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return messages;
    }

    /**
     * 
     */
    public Message getMessageById(int message_id){
        Connection connection = ConnectionUtil.getConnection();
        try {
            String sql = "SELECT * FROM message WHERE message_id = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, message_id);

            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                Message message = new Message(rs.getInt("message_id"),
                        rs.getInt("posted_by"),
                        rs.getString("message_text"),
                        rs.getLong("time_posted_epoch"));
                    return message;
                }
            }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    
    public Message addMessage(Message message){
        Connection connection = ConnectionUtil.getConnection();

        try {
            
            String sql = "INSERT INTO message (posted_by, message_text, time_posted_epoch) VALUES ( ?, ?, ?);" ;
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            
            preparedStatement.setInt(1, message.getPosted_by());
            preparedStatement.setString(2, message.getMessage_text());
            preparedStatement.setLong(3, message.getTime_posted_epoch());
        
            preparedStatement.executeUpdate();
            ResultSet pkeyResultSet = preparedStatement.getGeneratedKeys();
            if(pkeyResultSet.next()){
                int generated_message_id = (int) pkeyResultSet.getLong(1);
                return new Message(generated_message_id, message.getPosted_by(), message.getMessage_text(), message.getTime_posted_epoch());
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }
    /**
     * As a user, I should be able to submit a PATCH request on the endpoint PATCH localhost:8080/messages/{message_id}.
     * The request body should contain a new message_text values to replace the message identified by message_id.
     * The request body can not be guaranteed to contain any other information.
     * 
     * The update of a message should be successful if and only if the message id already exists and the new message_text is not blank and is not over 255 characters.
     * update is successful, the response body should contain the full updated message (including message_id, posted_by, message_text, and time_posted_epoch),
     * and the response status should be 200, which is the default. The message existing on the database should have the updated message_text.
     * If the update of the message is not successful for any reason, the response status should be 400. (Client error)
     * @param id
     * @param message
     * @return 
     */

    public  Message updateMessage(Message message, int id){
        Connection connection = ConnectionUtil.getConnection();
        try {
            //Write SQL logic here
            String sql = "UPDATE message SET message_text=? WHERE message_id= ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, message.getMessage_text());
            preparedStatement.setInt(2, id);

            preparedStatement.executeUpdate();

            //return message;
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return null;     
    }

    
    public List<Message> getAllUserMessages(int posted_by){
        Connection connection = ConnectionUtil.getConnection();
        List<Message> messages = new ArrayList<>();
        Message message = null;
        try {
            //Write SQL logic here
            String sql = "SELECT * FROM message WHERE posted_by = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, posted_by);

            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                message = new Message(rs.getInt("message_id"),
                        rs.getInt("posted_by"),
                        rs.getString("message_text"),
                        rs.getLong("time_posted_epoch"));
                messages.add(message);
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return messages;
    }

    public Message deleteMessagebyid(int message_id){
        Connection connection = ConnectionUtil.getConnection();
        try {
            Message msg = getMessageById(message_id);
            String sql = "DELETE FROM message WHERE message_id = ?;" ;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, message_id);

            preparedStatement.executeUpdate();
            return msg;

            }catch(SQLException e){
            System.out.println(e.getMessage());
        }    
        return null;
    }

}
