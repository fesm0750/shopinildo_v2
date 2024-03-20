package br.edu.postech.shopinildo.item;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest
public class ItemControllerTest {
	
	@Autowired
	private ItemController itemController;
	
	@MockBean
	private ItemService itemService;
	
	@Test
	void shouldReturnAllItems() {
		List<Item> items = new ArrayList<>();
		items.add(new Item("I001", "Item 1", "Item 1 description", new BigDecimal(10.0)));
		items.add(new Item("I002", "Item 2", "Item 2 description", new BigDecimal(10.0)));
		
		PageImpl<Item> page = new PageImpl<>(items);
		
		when(itemService.findAll(PageRequest.of(0, 10))).thenReturn(page);
		
		ResponseEntity<Page<Item>> response = itemController.findAll(PageRequest.of(0, 10));
		
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(items, response.getBody().getContent());
	}
	
	@Test
	void shouldReturnItemById() {
		Item item = new Item("I001", "Item 1", "Item 1 description", new BigDecimal(10.0));
		
		when(itemService.findById(item.getId().toString())).thenReturn(item);
		
		ResponseEntity<Item> response = itemController.findById(item.getId().toString());
		
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(item, response.getBody());
	}
	
	@Test
	void shouldCreateItem() {
		Item item = new Item(null, "Item 1", "Item 1 description", new BigDecimal(10.0));
		
		when(itemService.createItem(item)).thenReturn(item);
		
		ResponseEntity<Item> response = itemController.createItem(item);
		
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(item, response.getBody());
	}
	
	@Test
	void shouldUpdateItem() {
		Item item = new Item("I001", "Item 1", "Item 1 description", new BigDecimal(10.0));
		
		when(itemService.updateItem(item)).thenReturn(item);
		
		ResponseEntity<Item> response = itemController.updateItem(item.getId().toString(), item);
		
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(item, response.getBody());
	}
	
	@Test
	void shouldDeleteItem() {
		Item item = new Item("I001", "Item 1", "Item 1 description", new BigDecimal(10.0));
		
		itemController.deleteItem(item.getId().toString());
		
		verify(itemService).deleteItem(item.getId().toString());
	}
}

