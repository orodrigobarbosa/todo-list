package toDo_List_Application.to_do_list.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import toDo_List_Application.to_do_list.domain.validator.StatusEnum;


@Getter
@Setter
@Entity
public class ToDoList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String descricao;


    @Enumerated(EnumType.STRING)
    private StatusEnum status;
}
