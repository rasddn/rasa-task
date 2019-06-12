package com.rasa.gildedrose.repository;

import com.rasa.gildedrose.entity.Item;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ItemsRepository extends MongoRepository<Item, String> {
}

