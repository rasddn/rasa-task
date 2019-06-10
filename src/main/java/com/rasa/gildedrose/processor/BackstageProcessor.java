package com.rasa.gildedrose.processor;


import com.rasa.gildedrose.entity.Item;

public class BackstageProcessor implements Updatable {

    @Override
    public void update(Item item) {
        item.changeQualityValueBy(1);
        if (item.getSellIn() <= 10) {
            item.changeQualityValueBy(1);
        }
        if (item.getSellIn() <= 5) {
            item.changeQualityValueBy(1);
        }
        if (item.getSellIn() <= 0) {
            item.setQuality(QUALITY_MIN);
        }
        if (item.getQuality() > QUALITY_MAX) {
            item.setQuality(QUALITY_MAX);
        }

        updateSellIn(item);
    }
}
