package com.crud.tasks.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class TrelloCard {
    private String name;
    private String description;
    private String pos;
    private String listId;

    public TrelloCard(){}

    public TrelloCard(String name, String description, String pos, String listId) {
        this.name = name;
        this.description = description;
        this.pos = pos;
        this.listId = listId;
    }
}
