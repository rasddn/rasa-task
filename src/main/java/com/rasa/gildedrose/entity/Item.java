package com.rasa.gildedrose.entity;

import org.springframework.data.annotation.Id;

public class Item {

    @Id
    private String id;

    private String name;

    private int sellIn;

    private int quality;

    public Item(String name, int sellIn, int quality) {
        this.name = name;
        this.sellIn = sellIn;
        this.quality = quality;
    }

    public void decrementSellInBy(int amount) {
        sellIn -= amount;
    }

    public void changeQualityValueBy(int amount) {
        quality += amount;
    }

    @Override
    public String toString() {
        return this.name + ", " + this.sellIn + ", " + this.quality;
    }

    public String getName() {
        return name;
    }

    public int getSellIn() {
        return sellIn;
    }

    public int getQuality() {
        return quality;
    }

    public void setQuality(int quality) {
        this.quality = quality;
    }
}
