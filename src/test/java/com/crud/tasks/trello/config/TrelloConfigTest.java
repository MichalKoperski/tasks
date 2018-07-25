package com.crud.tasks.trello.config;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class TrelloConfigTest {

    @Mock
    private TrelloConfig trelloConfig;

    @Test
    public void getTrelloApiEndpoint() {
        TrelloConfig config = new TrelloConfig();
        assertEquals(config.getTrelloApiEndpoint(), "https://api.trello.com/1");
    }

    @Test
    public void getTrelloAppKey() {
        assertEquals(trelloConfig.getTrelloAppKey(), "047fec945bda9e4aaa2d3461e8fbefc6");
    }

    @Test
    public void getTrelloToken() {
        assertEquals(trelloConfig.getTrelloToken(), "6551adac5b701e8aff354382a508cd800dcd229457226e60cab0835edc3952af");
    }
}