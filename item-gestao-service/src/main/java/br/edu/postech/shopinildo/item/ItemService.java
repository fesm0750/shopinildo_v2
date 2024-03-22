package br.edu.postech.shopinildo.item;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public Page<Item> findAll(Pageable pageable) {
        return itemRepository.findAll(pageable);
    }

    public Item findById(String id) {
        return itemRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException(String.format("Item with id '%s' not found", id)));
    }

    public Item createItem(Item item) {
        return itemRepository.save(item);
    }

    public Item updateItem(Item item) {
        if (itemRepository.existsById(item.getId())) {
            return itemRepository.save(item);
        } else {
            throw new NoSuchElementException(String.format("Item '%s' not found", item.getId()));
        }
    }

    public void deleteItem(String id) {
        itemRepository.deleteById(id);
    }

    public List<Item> findByIds(List<String> ids) {
        return itemRepository.findAllById(ids);
    }
}
