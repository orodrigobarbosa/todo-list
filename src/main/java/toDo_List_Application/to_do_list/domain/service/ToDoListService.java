package toDo_List_Application.to_do_list.domain.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


import toDo_List_Application.to_do_list.domain.handler.HandleIDNaoEncontrado;
import toDo_List_Application.to_do_list.domain.model.ToDoList;
import toDo_List_Application.to_do_list.domain.repository.ToDoRepository;

import java.util.List;

@AllArgsConstructor
@Service
public class ToDoListService {

    private final ToDoRepository toDoRepository;

    public ToDoList criarTarefa(ToDoList tarefa) {
        return toDoRepository.save(tarefa);
    }

    public List<ToDoList> listarTarefas() {
        return toDoRepository.findAll();
    }

    public ToDoList buscarPorId(Long id) {
        return toDoRepository.findById(id).orElseThrow(() -> new HandleIDNaoEncontrado("Tarefa não encontrada pelo ID digitado: " + id));
    }

    public List<ToDoList> listarPorStatus(String status) {
        return toDoRepository.findByStatus(status);
    }

    @Transactional
    public ToDoList editarTarefa(Long id, ToDoList tarefa) {
        if (toDoRepository.existsById(id)) {
            tarefa.setId(id);
            return toDoRepository.save(tarefa);
        } else {
            throw new HandleIDNaoEncontrado("Tarefa com ID " + id + " não encontrada");
        }
    }


    @Transactional
    public void removerTarefa(Long id) {
        if (toDoRepository.existsById(id)) {
            toDoRepository.deleteById(id);
        } else {
            throw new HandleIDNaoEncontrado("Tarefa com ID " + id + " não encontrada para ser deletada.");
        }
    }


}
