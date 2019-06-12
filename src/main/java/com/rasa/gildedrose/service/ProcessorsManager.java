package com.rasa.gildedrose.service;


import com.rasa.gildedrose.entity.Item;
import com.rasa.gildedrose.processor.ItemProcessor;
import com.rasa.gildedrose.processor.ProcessorsResolver;
import com.rasa.gildedrose.processor.Updatable;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ProcessorsManager {
    private final Map<String, Updatable> processors;
    private final ItemProcessor defaultProcessor;

    public ProcessorsManager() {
        this(new ProcessorsResolver(), new ItemProcessor());
    }

    public ProcessorsManager(final Map<String, Updatable> processors, final ItemProcessor defaultProcessor) {
        this.processors = processors;
        this.defaultProcessor = defaultProcessor;
    }

    public void updateItem(Item item) {
        Updatable manager = processors.get(item.getName());
        if (manager == null) {
            manager = defaultProcessor;
        }
        manager.update(item);
    }
}
