package toDo_List_Application.to_do_list.domain.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import toDo_List_Application.to_do_list.domain.model.ToDoList;
import toDo_List_Application.to_do_list.domain.service.ToDoListService;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/todolist")
public class ToDoListController {

    private final ToDoListService toDoListService;

    @PostMapping
    public ToDoList criar(@RequestBody ToDoList tarefa) {
        return toDoListService.criarTarefa(tarefa);
    }

    @GetMapping
    public List<ToDoList> listarTodos() {
        return toDoListService.listarTarefas();
    }


    @GetMapping("/{id}")
    public ToDoList buscarPorId(@PathVariable Long id) {
        return toDoListService.buscarPorId(id);
    }

    @GetMapping("/status/{status}")
    public List<ToDoList> buscarPorStatus(@PathVariable String status) {
        return toDoListService.listarPorStatus(status);
    }

    @PutMapping("/{id}")
    public ToDoList atualizar(@PathVariable Long id, @RequestBody ToDoList tarefa) {
        return toDoListService.editarTarefa(id, tarefa);
    }

    @DeleteMapping("/{id}")
    public void remover(@PathVariable Long id) {
        toDoListService.deletarTarefa(id);
    }


}
