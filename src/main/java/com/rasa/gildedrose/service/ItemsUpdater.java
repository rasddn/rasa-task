package com.rasa.gildedrose.service;

import com.rasa.gildedrose.entity.Item;
import com.rasa.gildedrose.repository.ItemsRepository;
import com.rasa.gildedrose.usecase.UpdateItemsUsecase;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ItemsUpdater {
    private final ItemsRepository repository;
    private final UpdateItemsUsecase usecase;

    public ItemsUpdater(ItemsRepository repository, UpdateItemsUsecase usecase) {
        this.repository = repository;
        this.usecase = usecase;
    }

    public void update() throws InterruptedException {
        List<Item> items = repository.findAll();
        usecase.updateQuality(items);
        repository.saveAll(items);
    }
}
