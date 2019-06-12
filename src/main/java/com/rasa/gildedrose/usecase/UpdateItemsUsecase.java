package com.rasa.gildedrose.usecase;

import com.rasa.gildedrose.entity.Item;
import com.rasa.gildedrose.service.ProcessorsManager;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

import static java.util.Collections.synchronizedList;
import static java.util.concurrent.Executors.newFixedThreadPool;

@Service
public class UpdateItemsUsecase {
    private final ProcessorsManager manager;

    public UpdateItemsUsecase(final ProcessorsManager manager) {
        this.manager = manager;
    }

    public void updateQuality(List<Item> items) throws InterruptedException {
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