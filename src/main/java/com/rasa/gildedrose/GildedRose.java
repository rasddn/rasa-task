package com.rasa.gildedrose;

import com.rasa.gildedrose.entity.Item;
import com.rasa.gildedrose.processor.ProcessorsManager;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

import static java.util.Collections.synchronizedList;
import static java.util.concurrent.Executors.newFixedThreadPool;

class GildedRose {
    private List<Item> items;
    private ProcessorsManager manager;

    public GildedRose(List<Item> items, ProcessorsManager manager) {
        this.items = items;
        this.manager = manager;
    }

    public void updateQuality() throws InterruptedException {
        List<Item> syncItems = synchronizedList(items);
        ExecutorService executor = newFixedThreadPool(3);

        executor.execute(() -> {
            synchronized (syncItems) {
                for (Item item : syncItems) {
                    manager.updateItem(item);
                }
            }
        });

        executor.shutdown();
        executor.awaitTermination(60, TimeUnit.SECONDS);
    }
}