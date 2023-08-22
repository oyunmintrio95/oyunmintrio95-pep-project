package Service;

import java.util.List;

import DAO.AccountDAO;
import DAO.MessageDAO;

import Model.Message;

public class MessageService {
    MessageDAO messageDAO;
    AccountDAO accountDAO;

    public MessageService(){
        messageDAO = new MessageDAO();
        accountDAO = new AccountDAO();
    }

    public MessageService(MessageDAO messageDAO, AccountDAO accountDAO){
        this.accountDAO = accountDAO;
        this.messageDAO = messageDAO;
    }

    //getting message lists
    public List<Message> getAllMessages(){
        return messageDAO.getAllMessages();
    }

    //Adding a message
    public Message addMessage(Message message){
        if(accountDAO.getAccountByAccountId(message.getPosted_by()) != null){//the post_by account does exist in the account Table.
           String text = message.getMessage_text();
            if(!text.isBlank() && text.length() < 255){
                return messageDAO.insertMessage(message);
            }else{
                return null;
            }
        }else{ // the account does not exist.
            return null;
        }
        
    }

    //retrieve a message 
    public Message getAMessageByMessageId(int message_id){
        return messageDAO.getAMessageByMessageId(message_id);
    }

    //delete a message
    public Message deleteMessage(int message_id){
        Message message;
        if(messageDAO.getAMessageByMessageId(message_id) == null){//MessageId doesn't exist.
            return null;
        }else{//Messageid does exist.
            message = messageDAO.getAMessageByMessageId(message_id);
            int deleted = messageDAO.deleteMessageByMessageId(message_id);
            if(deleted >0){//deletion success
                return message;
            }else{//failed deletion
                message = null;
            }
        }
        return message;
        
    }

    //updating a message
    public Message updateMessage(int message_id, Message message){
        Message beforeMessage = messageDAO.getAMessageByMessageId(message_id);
        String text = message.getMessage_text();
        if(beforeMessage != null){//means the message of message_id does exist.
             if(!text.isBlank() && text.length() < 255){
                 int update = messageDAO.updateMessage(message_id, message.getMessage_text());
                 if(update <=0){ // update didn't go through.
                    return null;
                 }
             }else{//text is either balnk or over 255 character. Cannot update the message
                 return null;
             }
        }else{ // the message of message_id does not exist.
            return null;
        }
        //updated successful
        Message updatedMessage = messageDAO.getAMessageByMessageId(message_id);
        return updatedMessage;
    }

    //get all messages posted by certain user
    public List<Message> getAllMessagesByAccountId(int account_id){
        return messageDAO.getAllMessagesByAccountId(account_id);
    }
    
}
