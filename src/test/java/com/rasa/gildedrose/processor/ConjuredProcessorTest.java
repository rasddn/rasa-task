package com.rasa.gildedrose.processor;

import com.rasa.gildedrose.entity.Item;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ConjuredProcessorTest {
    private ConjuredProcessor processor;

    @Before
    public void setUp() throws Exception {
        processor = new ConjuredProcessor();
    }

    @Test
    public void quality_decreases_by_2() {
        Item item = new Item("Conjured Mana Cake", 5, 6);
        processor.update(item);
        assertThat(item.getQuality()).isEqualTo(4);
    }

    @Test
    public void quality_decreases_by_4_after_sell_by_date_passes() {
        Item item = new Item("Conjured Mana Cake", 0, 6);
        processor.update(item);
        assertThat(item.getQuality()).isEqualTo(2);
    }

    @Test
    public void quality_is_never_less_than_0() {
        Item item = new Item("Conjured Mana Cake", 5, 0);
        processor.update(item);
        assertThat(item.getQuality()).isEqualTo(0);
    }

    @Test
    public void sell_in_decreases_by_1() {
        Item item = new Item("Conjured Mana Cake", 5, 6);
        processor.update(item);
        assertThat(item.getSellIn()).isEqualTo(4);
    }
}