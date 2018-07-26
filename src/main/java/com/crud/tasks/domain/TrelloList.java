package com.crud.tasks.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
//@AllArgsConstructor
public class TrelloList {

    private String id;
    private String name;
    private boolean isClosed;

    public TrelloList() {}

    public TrelloList(String id, String name, boolean isClosed) {
        this.id = id;
        this.name = name;
        this.isClosed = isClosed;
    }
}
