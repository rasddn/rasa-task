package com.rasa.gildedrose.controller;

import com.rasa.gildedrose.dto.ItemDto;
import com.rasa.gildedrose.service.ItemsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("/api/items")
public class ItemsController {

    private final ItemsService service;

    public ItemsController(ItemsService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(value = NO_CONTENT)
    public void addItem(@RequestBody ItemDto request) {
        service.saveItems(request);
    }

    @GetMapping
    public List<ItemDto> getAllItems() {
        return service.getItems();
    }
}

