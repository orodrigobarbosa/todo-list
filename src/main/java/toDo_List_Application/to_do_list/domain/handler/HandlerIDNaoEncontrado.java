package toDo_List_Application.to_do_list.domain.handler;

public class HandlerIDNaoEncontrado extends RuntimeException{

    public HandlerIDNaoEncontrado(String message){
        super(message);
    }
}
