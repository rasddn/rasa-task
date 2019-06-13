package com.rasa.gildedrose.service;

import com.rasa.gildedrose.entity.Item;
import com.rasa.gildedrose.repository.ItemsRepository;
import com.rasa.gildedrose.usecase.UpdateItemsUsecase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static java.util.Collections.singletonList;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ItemsUpdaterTest {

    @InjectMocks
    private ItemsUpdater updater;

    @Mock
    private ItemsRepository repository;

    @Mock
    private UpdateItemsUsecase usecase;

    @Test
    public void updates_items_and_saves_in_database() throws InterruptedException {
        List<Item> items = singletonList(new Item("name", 4, 4));
        when(repository.findAll()).thenReturn(items);

        updater.update();

        verify(usecase).updateQuality(items);
        verify(repository).saveAll(items);
    }
}