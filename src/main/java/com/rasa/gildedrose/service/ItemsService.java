package com.rasa.gildedrose.service;

import com.rasa.gildedrose.dto.ItemDto;
import com.rasa.gildedrose.entity.Item;
import com.rasa.gildedrose.repository.ItemsRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemsService {
    private final ItemsRepository repository;

    public ItemsService(ItemsRepository repository) {
        this.repository = repository;
    }

    public List<ItemDto> getItems() {
        return toDtoItems(repository.findAll());
    }

    public void saveItems(ItemDto request) {
        Item item = new Item(request.getName(), request.getSellIn(), request.getQuality());
        repository.save(item);
    }

    private List<ItemDto> toDtoItems(List<Item> items) {
        List<ItemDto> dtoItems = new ArrayList<>();
        for (Item item : items) {
            dtoItems.add(toDtoItem(item));
        }

        return dtoItems;

    }

    private ItemDto toDtoItem(Item item) {
        ItemDto itemDto = new ItemDto();
        itemDto.setQuality(item.getQuality());
        itemDto.setSellIn(item.getSellIn());
        itemDto.setName(item.getName());

        return itemDto;
    }
}
