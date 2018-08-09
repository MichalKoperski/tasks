package com.crud.tasks.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class Trello {
    private int board;
    private int card;

    public Trello(int board, int card) {
        this.board = board;
        this.card = card;
    }

    public Trello() {}
}
