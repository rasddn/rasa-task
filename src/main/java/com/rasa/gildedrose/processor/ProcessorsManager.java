package com.rasa.gildedrose.processor;


import com.rasa.gildedrose.entity.Item;

import java.util.HashMap;
import java.util.Map;

public class ProcessorsManager {
    private Map<String, Updatable> processors = new HashMap<>();
    private ItemProcessor defaultProcessor = new ItemProcessor();

    public void add(String name, Updatable manager) {
        processors.put(name, manager);
    }

    public void updateItem(Item item) {
        Updatable manager = processors.get(item.getName());
        if (manager == null) {
            manager = defaultProcessor;
        }
        manager.update(item);
    }
}
