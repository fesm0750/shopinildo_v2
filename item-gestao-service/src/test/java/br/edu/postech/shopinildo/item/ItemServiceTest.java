package br.edu.postech.shopinildo.item;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ItemServiceTest {

    @Mock
    private ItemRepository itemRepository;

    @InjectMocks
    private ItemService itemService;

    @Test
    void createItem() {
        Item item = new Item("1", "teste", "descrição", new BigDecimal("20.5"));
        when(itemRepository.save(item)).thenReturn(item);
        assertEquals(item, itemService.createItem(item));
    }

    @Test
    @SuppressWarnings("unchecked")
    void findAll() {
        Page<Item> items = mock(Page.class);
        when(itemRepository.findAll(PageRequest.of(0, 10))).thenReturn(items);
        assertEquals(items, itemService.findAll(PageRequest.of(0, 10)));
    }

    @Test
    void findById() {
        Item item = new Item("1", "teste", "descrição", new BigDecimal("20.5"));
        when(itemRepository.findById("1")).thenReturn(Optional.of(item));
        assertEquals(item, itemService.findById("1"));
    }

    @Test
    void updateItem() {
        Item item = new Item("2", "teste", "descrição", new BigDecimal("20.5"));
        when(itemRepository.existsById(item.getId())).thenReturn(true);
        when(itemRepository.save(item)).thenReturn(item);
        assertEquals(item, itemService.updateItem(item));
    }

    @Test
    void deleteItem() {
        Item item = new Item("3", "teste", "descrição", new BigDecimal("20.5"));
        doNothing().when(itemRepository).deleteById(item.getId());
        itemService.deleteItem(item.getId());
        verify(itemRepository, times(1)).deleteById(item.getId());
    }
}

