package com.rasa.gildedrose.processor;


import com.rasa.gildedrose.entity.Item;

public interface Updatable {
    int QUALITY_MIN = 0;
    int QUALITY_MAX = 50;

    void update(Item item);

    default void updateSellIn(Item item) {
        item.decrementSellInBy(1);
    }
}
