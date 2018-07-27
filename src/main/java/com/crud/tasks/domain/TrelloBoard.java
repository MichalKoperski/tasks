package com.crud.tasks.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
public class TrelloBoard {

    private String id;
    private String name;
    private List<TrelloList> lists;

    public TrelloBoard(){}

    public TrelloBoard(String id, String name, List<TrelloList> lists) {
        this.id = id;
        this.name = name;
        this.lists = lists;
    }
}
