package com.rasa.gildedrose.dto;

public class ItemDto {

    private String name;

    private int sellIn;

    private int quality;

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

    public void setSellIn(int sellIn) {
        this.sellIn = sellIn;
    }

    public void setName(String name) {
        this.name = name;
    }
}
