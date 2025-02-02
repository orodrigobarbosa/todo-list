package toDo_List_Application.to_do_list.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import toDo_List_Application.to_do_list.domain.model.ToDoList;
import toDo_List_Application.to_do_list.domain.validator.StatusEnum;

import java.util.List;

@Repository
public interface ToDoRepository extends JpaRepository<ToDoList, Long> {
    List<ToDoList> findByStatus(StatusEnum status);

    //metodo de paginacao diretamente no repositório
    Page<ToDoList> findAll(Pageable pageable);
}
