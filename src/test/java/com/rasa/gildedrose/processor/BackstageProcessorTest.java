package com.rasa.gildedrose.processor;

import com.rasa.gildedrose.entity.Item;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BackstageProcessorTest {
    private BackstageProcessor processor;

    @Before
    public void setUp() throws Exception {
        processor = new BackstageProcessor();
    }

    @Test
    public void quality_increases_by_1() {
        Item item = new Item("Backstage passes to a TAFKAL80ETC concert", 15, 6);
        processor.update(item);
        assertThat(item.getQuality()).isEqualTo(7);
    }

    @Test
    public void quality_increases_by_2_when_between_6_and_10_days_left() {
        Item item = new Item("Backstage passes to a TAFKAL80ETC concert", 10, 6);
        processor.update(item);
        assertThat(item.getQuality()).isEqualTo(8);
    }

    @Test
    public void quality_increases_by_3_when_between_1_and_5_days_left() {
        Item item = new Item("Backstage passes to a TAFKAL80ETC concert", 5, 6);
        processor.update(item);
        assertThat(item.getQuality()).isEqualTo(9);
    }

    @Test
    public void quality_0_after_sell_by_date_passes() {
        Item item = new Item("Backstage passes to a TAFKAL80ETC concert", 0, 6);
        processor.update(item);
        assertThat(item.getQuality()).isEqualTo(0);
    }

    @Test
    public void quality_not_exceeds_50() {
        Item item = new Item("Backstage passes to a TAFKAL80ETC concert", 5, 50);
        processor.update(item);
        assertThat(item.getQuality()).isEqualTo(50);
    }

    @Test
    public void sell_in_decreases_by_1() {
        Item item = new Item("Backstage passes to a TAFKAL80ETC concert", 5, 6);
        processor.update(item);
        assertThat(item.getSellIn()).isEqualTo(4);
    }
}