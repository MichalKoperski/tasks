package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import com.crud.tasks.service.TrelloService;
import com.crud.tasks.trello.client.TrelloClient;
import com.crud.tasks.trello.facade.TrelloFacade;
import com.crud.tasks.trello.validator.TrelloValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyList;
import static org.mockito.Mockito.when;
@RunWith(MockitoJUnitRunner.class)
public class TrelloMapperTest {

    @InjectMocks
    private TrelloMapper trelloMapper;

    @Test
    public void testMapperIntoDTO() {

        TrelloList trelloList1 = new TrelloList("1", "list1", false);

        List<TrelloList> listOfTrelloLists = new ArrayList<>();
        listOfTrelloLists.add(trelloList1);

        TrelloBoard trelloBoard1 = new TrelloBoard("1", "board1", listOfTrelloLists);

        TrelloCard trelloCard1 = new TrelloCard("card1", "aaa", "1", "1");

        List<TrelloBoard> listOfTrelloBoards = new ArrayList<>();
        listOfTrelloBoards.add(trelloBoard1);


        List<TrelloBoardDto> mappedBoardDto = trelloMapper.mapToBoardsDto(listOfTrelloBoards);
        List<TrelloListDto> mappedListDto = trelloMapper.mapToListDto(listOfTrelloLists);
        TrelloCardDto mappedCardDto = trelloMapper.mapToCardDto(trelloCard1);


        assertEquals(mappedBoardDto.get(0).getName(), trelloBoard1.getName());
        assertEquals(mappedBoardDto.get(0).getId(), trelloBoard1.getId());
        assertEquals(mappedBoardDto.get(0).getLists().size(), trelloBoard1.getLists().size());

        assertEquals(mappedListDto.get(0).getName(), trelloList1.getName());
        assertEquals(mappedListDto.get(0).getId(), trelloList1.getId());

        assertEquals(mappedCardDto.getName(), trelloCard1.getName());
        assertEquals(mappedCardDto.getDescription(), trelloCard1.getDescription());
        assertEquals(mappedCardDto.getPos(), trelloCard1.getPos());
        assertEquals(mappedCardDto.getListId(), trelloCard1.getListId());
    }

    @Test
    public void testMapperFromDTO() {

        TrelloListDto trelloListDto1 = new TrelloListDto("1", "listDTO1", false);

        List<TrelloListDto> listOfTrelloListsDto = new ArrayList<>();
        listOfTrelloListsDto.add(trelloListDto1);

        TrelloBoardDto trelloBoardDto1 = new TrelloBoardDto("1", "boardDTO1", listOfTrelloListsDto);

        TrelloCardDto trelloCardDto1 = new TrelloCardDto("cardDTO1", "aaa", "1", "1");

        List<TrelloBoardDto> listOfTrelloBoardsDto = new ArrayList<>();
        listOfTrelloBoardsDto.add(trelloBoardDto1);

        List<TrelloBoard> mappedBoard = trelloMapper.mapToBoards(listOfTrelloBoardsDto);
        List<TrelloList> mappedList = trelloMapper.mapToList(listOfTrelloListsDto);
        TrelloCard mappedCard = trelloMapper.mapToCard(trelloCardDto1);


        assertEquals(mappedBoard.get(0).getName(), trelloBoardDto1.getName());
        assertEquals(mappedBoard.get(0).getId(), trelloBoardDto1.getId());
        assertEquals(mappedBoard.get(0).getLists().size(), trelloBoardDto1.getLists().size());

        assertEquals(mappedList.get(0).getName(), trelloListDto1.getName());
        assertEquals(mappedList.get(0).getId(), trelloListDto1.getId());

        assertEquals(mappedCard.getName(), trelloCardDto1.getName());
        assertEquals(mappedCard.getDescription(), trelloCardDto1.getDescription());
        assertEquals(mappedCard.getPos(), trelloCardDto1.getPos());
        assertEquals(mappedCard.getListId(), trelloCardDto1.getListId());

    }
}
