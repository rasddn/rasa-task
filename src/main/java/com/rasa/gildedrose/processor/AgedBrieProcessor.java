package com.rasa.gildedrose.processor;


import com.rasa.gildedrose.entity.Item;

public class AgedBrieProcessor implements Updatable {

    @Override
    public void update(Item item) {
        item.changeQualityValueBy(1);
        if (item.getSellIn() <= 0) {
            item.changeQualityValueBy(1);
        }
        if (item.getQuality() > QUALITY_MAX) {
            item.setQuality(QUALITY_MAX);
        }

        updateSellIn(item);
    }
}
