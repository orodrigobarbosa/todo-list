package toDo_List_Application.to_do_list.domain.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import toDo_List_Application.to_do_list.domain.handler.HandleIDNaoEncontrado;
import toDo_List_Application.to_do_list.domain.model.ToDoList;
import toDo_List_Application.to_do_list.domain.repository.ToDoRepository;
import toDo_List_Application.to_do_list.domain.validator.StatusEnum;

import java.util.List;

@AllArgsConstructor
@Service
public class ToDoListService {

    private final ToDoRepository toDoRepository;

    @Transactional
    public ToDoList criarTarefa(ToDoList tarefa) {
        return toDoRepository.save(tarefa);
    }

    public List<ToDoList> listarTarefas() {
        return toDoRepository.findAll();
    }

    public ToDoList buscarPorId(Long id) {
        return toDoRepository.findById(id).orElseThrow(() -> new HandleIDNaoEncontrado("Tarefa não encontrada pelo ID digitado: " + id));
    }

    public List<ToDoList> listarPorStatus(StatusEnum status) {
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


    // Metodo para listar tarefas paginadas
    public Page<ToDoList> listarTarefasPaginadas(int pagina, int tamanho) {
        Pageable pageable = PageRequest.of(pagina, tamanho);  // Criação de Pageable
        return toDoRepository.findAll(pageable);  // Retorna uma página de tarefas
    }

}
