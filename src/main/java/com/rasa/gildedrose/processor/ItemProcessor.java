package com.rasa.gildedrose.processor;


import com.rasa.gildedrose.entity.Item;

public class ItemProcessor implements Updatable {

    @Override
    public void update(Item item) {
        item.changeQualityValueBy(-1);
        if (item.getSellIn() <= 0) {
            item.changeQualityValueBy(-1);
        }
        if (item.getQuality() < QUALITY_MIN) {
            item.setQuality(QUALITY_MIN);
        }

        updateSellIn(item);
    }
}
