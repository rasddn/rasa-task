package com.rasa.gildedrose.scheduler;

import com.rasa.gildedrose.service.ItemsUpdater;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ItemsScheduler {

    private static final String UPDATE_ITEMS_INTERVAL = "0 59 23 * * *";
    private final ItemsUpdater usecase;

    public ItemsScheduler(final ItemsUpdater usecase) {
        this.usecase = usecase;
    }

    @Scheduled(cron = UPDATE_ITEMS_INTERVAL)
    public void updateItems() throws Exception {
        usecase.update();
    }
}
