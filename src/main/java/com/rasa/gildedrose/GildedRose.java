package com.rasa.gildedrose;

import com.rasa.gildedrose.entity.Item;
import com.rasa.gildedrose.processor.ProcessorsManager;

import java.util.List;

class GildedRose {
    private List<Item> items;
    private ProcessorsManager manager;

    public GildedRose(List<Item> items, ProcessorsManager manager) {
        this.items = items;
        this.manager = manager;
    }

    public void updateQuality() {
        for (Item item : items) {
            manager.updateItem(item);
        }
    }
}