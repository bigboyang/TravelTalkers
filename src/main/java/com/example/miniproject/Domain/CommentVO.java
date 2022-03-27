package com.example.miniproject.Domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CommentVO {

    public CommentVO() {
    }

    private int cno,bno;
    private String memberId,context;


}
