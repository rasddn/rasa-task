package com.rasa.gildedrose.processor;

import com.rasa.gildedrose.entity.Item;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AgedBrieProcessorTest {

    private AgedBrieProcessor processor;

    @Before
    public void setUp() throws Exception {
        processor = new AgedBrieProcessor();
    }

    @Test
    public void quality_increases_by_1() {
        Item item = new Item("Aged Brie", 5, 6);
        processor.update(item);
        assertThat(item.getQuality()).isEqualTo(7);
    }

    @Test
    public void quality_increases_by_2_after_sell_by_date_passes() {
        Item item = new Item("Aged Brie", 0, 6);
        processor.update(item);
        assertThat(item.getQuality()).isEqualTo(8);
    }

    @Test
    public void quality_not_exceeds_50() {
        Item item = new Item("Aged Brie", 5, 50);
        processor.update(item);
        assertThat(item.getQuality()).isEqualTo(50);
    }

    @Test
    public void sell_in_decreases_by_1() {
        Item item = new Item("Aged Brie", 5, 6);
        processor.update(item);
        assertThat(item.getSellIn()).isEqualTo(4);
    }
}