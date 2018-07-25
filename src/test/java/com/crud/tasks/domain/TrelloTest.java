package com.crud.tasks.domain;

import org.junit.Test;

import static org.junit.Assert.*;

public class TrelloTest {

    @Test
    public void getterTest() {
        Trello trello = new Trello(1,1);
        assertEquals(trello.getBoard(), 1);
        assertEquals(trello.getCard(), 1);
    }
}