package toDo_List_Application.to_do_list.domain.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestScope;
import org.springframework.web.server.ResponseStatusException;
import toDo_List_Application.to_do_list.domain.model.ToDoList;
import toDo_List_Application.to_do_list.domain.service.ToDoListService;
import toDo_List_Application.to_do_list.domain.validator.StatusEnum;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/todolist")
public class ToDoListController {

    private final ToDoListService toDoListService;

    @PostMapping
    public ResponseEntity<ToDoList> criarTarefa(@RequestBody ToDoList tarefa){
        ToDoList novaTarefa = toDoListService.criarTarefa(tarefa);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaTarefa);
    }


    @GetMapping
    public ResponseEntity<List<ToDoList>> listarTarefas(){
        List<ToDoList> tarefas = toDoListService.listarTarefas();
        return ResponseEntity.ok().body(tarefas);
    }


    @GetMapping("/buscar/id/{id}")
    public ResponseEntity<ToDoList> buscarTarefaPorId(@PathVariable Long id){
        ToDoList tarefa = toDoListService.buscarPorId(id);
        return ResponseEntity.ok().body(tarefa);

    }



    @GetMapping("/status/{status}")
    public ResponseEntity<List<ToDoList>> listarPorStatus(@PathVariable String status) {
        try {
            // Valida e converte o status para o enum
            StatusEnum statusEnum = StatusEnum.valueOf(status.trim().toUpperCase());

            // Obtém a lista a partir do serviço
            List<ToDoList> lista = toDoListService.listarPorStatus(statusEnum);

            return ResponseEntity.ok(lista);
        } catch (IllegalArgumentException e) {
            // Retorna BAD_REQUEST caso o status seja inválido
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Status inválido: " + status, e);
        }
    }

    @PutMapping
    public ToDoList atualizarPorId(@RequestBody Long id, @RequestBody ToDoList tarefa){
        return toDoListService.editarTarefa(id, tarefa);
    }

    @DeleteMapping("/{id}")
    public void excluirPorId(@PathVariable Long id){
        toDoListService.removerTarefa(id);
    }
}
