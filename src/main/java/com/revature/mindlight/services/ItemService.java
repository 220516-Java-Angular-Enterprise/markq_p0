package com.revature.mindlight.services;

import com.revature.mindlight.dao.ItemDAO;
import com.revature.mindlight.models.Items;
import com.revature.mindlight.util.annotations.Inject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ItemService {

    @Inject
    private final ItemDAO itemDAO;

    public ItemService(ItemDAO itemDAO) {
        this.itemDAO = itemDAO;
    }


    public List<Items> allItems() {
        List<Items> items = itemDAO.getAll(); // stores every unit as items object

        return items;

    }

    public void register(Items item) {
        itemDAO.save(item);
    }

    public void update(List<Items> itemsList) {
        // pass in list of items and update all to show sold in database
        // will also insert into orders_items table
        for (Items i: itemsList) {
            itemDAO.update(i);
        }
    }
}
