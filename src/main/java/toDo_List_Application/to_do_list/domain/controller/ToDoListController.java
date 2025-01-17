package toDo_List_Application.to_do_list.domain.controller;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
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

    @PutMapping("/{id}")
    public ResponseEntity<ToDoList> atualizarTarefa(@PathVariable Long id, @RequestBody ToDoList tarefa){
        ToDoList tarefaAtualizada = toDoListService.editarTarefa(id, tarefa);
        return ResponseEntity.ok(tarefaAtualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirTarefa(@PathVariable Long id){
        toDoListService.removerTarefa(id);
        return ResponseEntity.noContent().build();  // Retorna HTTP 204 No Content após a exclusão
    }

    // Endpoint para retornar tarefas paginadas utilizando ResponseEntity
    @GetMapping("/pagina")
    public ResponseEntity<Page<ToDoList>> listarTarefasPaginadas(
            @RequestParam(defaultValue = "0") int pagina,
            @RequestParam(defaultValue = "10") int tamanho) {

        Page<ToDoList> tarefas = toDoListService.listarTarefasPaginadas(pagina, tamanho);

        if (tarefas.isEmpty()) {
            // Caso a página esteja vazia, retorna HTTP 204 (No Content)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            // Caso contrário, retorna as tarefas paginadas com HTTP 200 OK
            return new ResponseEntity<>(tarefas, HttpStatus.OK);
        }
    }
}
