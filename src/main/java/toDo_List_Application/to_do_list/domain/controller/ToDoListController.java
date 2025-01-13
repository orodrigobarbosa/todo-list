package toDo_List_Application.to_do_list.domain.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
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
    public ToDoList criarTarefa(@RequestBody ToDoList tarefa) {
        return toDoListService.criarTarefa(tarefa);
    }

    @GetMapping
    List<ToDoList> listarTarefas() {
        return toDoListService.listarTarefas();
    }

    @GetMapping("/buscar/id/{id}")
    public ToDoList buscarTarefaPorId(@PathVariable Long id) {
        return toDoListService.buscarPorId(id);
    }

    @GetMapping("/status/{status}")
    public List<ToDoList> listarPorStatus(@PathVariable String status) {
        try {
            StatusEnum statusEnum = StatusEnum.valueOf(status.toUpperCase());
            return toDoListService.listarPorStatus(statusEnum);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Status inválido: " + status);
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
