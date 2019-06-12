package com.rasa.gildedrose.processor;

import java.util.HashMap;

public class ProcessorsResolver extends HashMap<String, Updatable> {
    private static final String ITEM_AGED_BRIE_NAME = "Aged Brie";
    private static final String ITEM_SULFURAS_NAME = "Sulfuras, Hand of Ragnaros";
    private static final String ITEM_BACKSTAGE_NAME = "Backstage passes to a TAFKAL80ETC concert";
    private static final String ITEM_CONJURED_NAME = "Conjured Mana Cake";

    public ProcessorsResolver() {
        put(ITEM_AGED_BRIE_NAME, new AgedBrieProcessor());
        put(ITEM_SULFURAS_NAME, new SulfurasProcessor());
        put(ITEM_BACKSTAGE_NAME, new BackstageProcessor());
        put(ITEM_CONJURED_NAME, new ConjuredProcessor());
    }
}
