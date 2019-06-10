package com.rasa.gildedrose.processor;

import com.rasa.gildedrose.entity.Item;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SulfurasProcessorTest {
    private SulfurasProcessor processor;

    @Before
    public void setUp() throws Exception {
        processor = new SulfurasProcessor();
    }

    @Test
    public void quality_equals_80_before_sell_by_date_passes() {
        Item item = new Item("Sulfuras, Hand of Ragnaros", 5, 80);
        processor.update(item);
        assertThat(item.getQuality()).isEqualTo(80);
    }

    @Test
    public void quality_equals_80_after_sell_by_date_passes() {
        Item item = new Item("Sulfuras, Hand of Ragnaros", -5, 80);
        processor.update(item);
        assertThat(item.getQuality()).isEqualTo(80);
    }

    @Test
    public void sell_in_not_changes() {
        Item item = new Item("Sulfuras, Hand of Ragnaros", 5, 6);
        processor.update(item);
        assertThat(item.getSellIn()).isEqualTo(5);
    }
}