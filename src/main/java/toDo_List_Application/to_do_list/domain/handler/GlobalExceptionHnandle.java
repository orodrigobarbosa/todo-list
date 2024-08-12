package toDo_List_Application.to_do_list.domain.handler;

import jakarta.persistence.EntityNotFoundException;
import org.hibernate.action.internal.EntityActionVetoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHnandle {

    @ExceptionHandler (HandleIDNaoEncontrado.class)
    public ResponseEntity<?> handleIDnaoEncontrado(HandleIDNaoEncontrado idNaoEncontrado, WebRequest request) {
        return  new ResponseEntity<>(idNaoEncontrado.getMessage(), HttpStatus.NOT_FOUND);
    }



    @ExceptionHandler(EntityActionVetoException.class)
    public ResponseEntity<?> handleEntityNotFound(EntityNotFoundException entity, WebRequest request){
        return new ResponseEntity<>(entity.getMessage(), HttpStatus.NOT_FOUND);
    }
}
