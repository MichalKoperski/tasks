package com.crud.tasks.domain;

import org.junit.Test;

import static org.junit.Assert.*;

public class CreatedTrelloCardDtoTest {

    @Test
    public void getterTest() {
        CreatedTrelloCardDto card = new CreatedTrelloCardDto("5","u","p");

        assertEquals(card.getName(), "u");
        assertEquals(card.getShortUrl(), "p");
        assertEquals(card.getId(), "5");
    }
}