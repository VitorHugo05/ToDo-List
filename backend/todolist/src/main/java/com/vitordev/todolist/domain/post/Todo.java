package com.vitordev.todolist.domain.post;

import com.vitordev.todolist.domain.post.enums.TodoStatus;

import lombok.*;

import org.springframework.data.annotation.Id;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Todo implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    private String id;
    private String content;
    private TodoStatus status;

}
