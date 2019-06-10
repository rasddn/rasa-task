package com.rasa.gildedrose;

import com.rasa.gildedrose.entity.Item;
import org.junit.Test;

import java.util.Random;

import static org.approvaltests.Approvals.verify;


public class GildedRoseTest {

    private static final int SEED = 100;
    private static final int DAYS_NUMBER = 100;
    private static final int ITEMS_NUMBER = 20;
    private static final int QUALITY_MIN = 0;
    private static final int QUALITY_MAX = 50;
    private static final int SELLIN_MIN = -10;
    private static final int SELLIN_MAX = 20;
    private static final String ITEM_DEXTERY_NAME = "+5 Dexterity Vest";
    private static final String ITEM_AGED_BRIE_NAME = "Aged Brie";
    private static final String ITEM_ELIXIR_NAME = "Elixir of the Mongoose";
    private static final String ITEM_SULFURAS_NAME = "Sulfuras, Hand of Ragnaros";
    private static final String ITEM_BACKSTAGE_NAME = "Backstage passes to a TAFKAL80ETC concert";

    private static final String[] itemNames = {
            ITEM_DEXTERY_NAME,
            ITEM_AGED_BRIE_NAME,
            ITEM_ELIXIR_NAME,
            ITEM_SULFURAS_NAME,
            ITEM_BACKSTAGE_NAME,
    };

    private Random random = new Random(SEED);

    @Test
    public void
    generates_updated_items_properties_output() {
        Item[] items = generateRandomItems();
        GildedRose gildedRose = new GildedRose(items);
        verify(getAllDaysItemsStringRepresentation(items, gildedRose));
    }

    private String getAllDaysItemsStringRepresentation(Item[] items, GildedRose gildedRose) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < DAYS_NUMBER; i++) {
            String header = "-------- day " + i + " --------\nname, sellIn, quality\n";
            builder.
                    append(header).
                    append(getItemsStringRepresentation(items)).
                    append("\n\n");
            gildedRose.updateQuality();
        }

        return builder.toString();
    }

    private StringBuilder getItemsStringRepresentation(Item[] items) {
        StringBuilder builder = new StringBuilder();
        for (Item item : items) {
            builder.append(item).append("\n");
        }

        return builder;
    }

    private Item[] generateRandomItems() {
        Item[] items = new Item[ITEMS_NUMBER];
        for (int i = 0; i < items.length; i++) {
            items[i] = new Item(getItemName(), generateSellIn(), generateQuality());
            setSpecificItemsQualities(items[i]);
        }

        return items;
    }

    private void setSpecificItemsQualities(Item item) {
        if (item.getName().equals(ITEM_SULFURAS_NAME)) {
            item.setQuality(80);
        } else if (item.getName().equals(ITEM_BACKSTAGE_NAME) && item.getSellIn() < 0) {
            item.setQuality(0);
        }
    }

    private String getItemName() {
        return itemNames[random.nextInt(itemNames.length)];
    }

    private int generateSellIn() {
        return generateRandomNumberBetween(SELLIN_MIN, SELLIN_MAX);
    }

    private int generateQuality() {
        return generateRandomNumberBetween(QUALITY_MIN, QUALITY_MAX);
    }

    private int generateRandomNumberBetween(int min, int max) {
        return min + random.nextInt(max - min + 1);
    }
}
