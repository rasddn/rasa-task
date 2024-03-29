package com.rasa.gildedrose;

import com.rasa.gildedrose.entity.Item;
import com.rasa.gildedrose.processor.*;
import com.rasa.gildedrose.service.ProcessorsManager;
import com.rasa.gildedrose.usecase.UpdateItemsUsecase;
import org.junit.Test;

import java.util.*;

import static java.util.Arrays.asList;
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
    private static final List<String> itemNames = asList(
            ITEM_DEXTERY_NAME,
            ITEM_AGED_BRIE_NAME,
            ITEM_ELIXIR_NAME,
            ITEM_SULFURAS_NAME,
            ITEM_BACKSTAGE_NAME
    );

    private Random random = new Random(SEED);

    @Test
    public void
    generates_updated_items_properties_output() throws Exception {
        List<Item> items = generateRandomItems();
        Map<String, Updatable> processors = new HashMap<>();
        processors.put(ITEM_AGED_BRIE_NAME, new AgedBrieProcessor());
        processors.put(ITEM_SULFURAS_NAME, new SulfurasProcessor());
        processors.put(ITEM_BACKSTAGE_NAME, new BackstageProcessor());
        ProcessorsManager manager = new ProcessorsManager(processors, new ItemProcessor());

        UpdateItemsUsecase updateItemsUsecase = new UpdateItemsUsecase(manager);
        verify(getAllDaysItemsStringRepresentation(items, updateItemsUsecase));
    }

    private String getAllDaysItemsStringRepresentation(List<Item> items, UpdateItemsUsecase updateItemsUsecase) throws Exception {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < DAYS_NUMBER; i++) {
            String header = "-------- day " + i + " --------\nname, sellIn, quality\n";
            builder.
                    append(header).
                    append(getItemsStringRepresentation(items)).
                    append("\n\n");
            updateItemsUsecase.updateQuality(items);
        }

        return builder.toString();
    }

    private StringBuilder getItemsStringRepresentation(List<Item> items) {
        StringBuilder builder = new StringBuilder();
        for (Item item : items) {
            builder.append(item).append("\n");
        }

        return builder;
    }

    private List<Item> generateRandomItems() {
        List<Item> items = new ArrayList<>(ITEMS_NUMBER);
        for (int i = 0; i < ITEMS_NUMBER; i++) {
            Item item = new Item(getItemName(), generateSellIn(), generateQuality());
            items.add(item);
            setSpecificItemsQualities(item);
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
        return itemNames.get(random.nextInt(itemNames.size()));
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
