package Service;

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
    
}
