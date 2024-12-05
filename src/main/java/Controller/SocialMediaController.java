package Controller;


import io.javalin.Javalin;
import io.javalin.http.Context;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import Model.Account;
import Model.Message;
import Service.AccountService;
import Service.MessageService;
import java.util.List;

/**
 * 
 */

public class SocialMediaController {
    AccountService accountService;
    MessageService messageService;

    public SocialMediaController(){
        accountService = new AccountService();
        messageService = new MessageService();
    }
    
    /**
     * .
     */
    public Javalin startAPI() {
        Javalin app = Javalin.create();
        app.post("/register", this::postAccountHandler);
        app.post("/login", this::postLoginAccountHandler);
        app.get("/messages", this::getAllMessagesHandler);
        app.post("/messages", this::postAddMessageHandler);
        app.get("/messages/{message_id}", this::getMessageByIdHandler);
        app.delete("/messages/{message_id}", this::deleteMessageHandler);
        app.patch("/messages/{message_id}", this::updateMessageHandler);
        app.get("accounts/{account_id}/messages", this::getAllUserMessagesHandler);
        
        

        return app;
    }

    /**
     * 
     */
    private void postAccountHandler(Context ctx) throws JsonProcessingException{
        ObjectMapper mapper = new ObjectMapper();
        Account account = mapper.readValue(ctx.body(), Account.class);
        //System.out.println("Account:"+account);
        Account addedAccount = accountService.addAccount(account);
        if(addedAccount!=null){
            ctx.json(mapper.writeValueAsString(addedAccount));
        }else{
            ctx.status(400);
        }
    }

    private void postLoginAccountHandler(Context ctx) throws JsonProcessingException{
        ObjectMapper mapper = new ObjectMapper();
        Account account = mapper.readValue(ctx.body(), Account.class);
        Account existAccount = accountService.loginAccount(account);
        if(existAccount!=null){
            ctx.json(mapper.writeValueAsString(existAccount));
        }else{
            ctx.status(401);
        }
    }

    private void postAddMessageHandler(Context ctx) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Message message = mapper.readValue(ctx.body(), Message.class);
        Message addedMessage = messageService.addMessage(message);
        if(addedMessage!=null){
            ctx.json(mapper.writeValueAsString(addedMessage));
        }else{
            ctx.status(400);
        }
    }

    public void getAllMessagesHandler(Context ctx) throws JsonProcessingException{
        List<Message> messages = messageService.getAllMessages();
        ctx.json(messages);
    }

    private void getAllUserMessagesHandler(Context ctx) throws JsonProcessingException{
        int id= Integer.valueOf(ctx.pathParam("account_id"));
        ctx.json(messageService.getAllUserMessages(id));
    }

    private void getMessageByIdHandler(Context ctx) throws JsonProcessingException{
        int message_id= Integer.valueOf(ctx.pathParam("message_id"));
        ctx.json(messageService.getMessagebyid(message_id));
    }

    private void deleteMessageHandler(Context ctx) throws JsonProcessingException{
        ObjectMapper mapper = new ObjectMapper();
        //Message message = mapper.readValue(ctx.body(),  Message.class);
        int message_id= Integer.valueOf(ctx.pathParam("message_id"));
        Message deletedMessage = messageService.deleteMessagebyid(message_id);
        if(deletedMessage !=null){
            ctx.json(mapper.writeValueAsString(deletedMessage));
        }else{
            ctx.status(200);
        }
    }
    
    private void updateMessageHandler(Context ctx) throws JsonProcessingException{
        ObjectMapper mapper = new ObjectMapper();
        Message message = mapper.readValue(ctx.body(),  Message.class);
        int message_id= Integer.parseInt(ctx.pathParam("message_id"));
        Message updatedMessage = messageService.updateMessage(message, message_id);
        if(updatedMessage!=null){
            ctx.json(mapper.writeValueAsString(updatedMessage));
        }else{
            ctx.status(400);
        }
        
    }

}

