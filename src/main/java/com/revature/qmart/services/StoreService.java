package com.revature.qmart.services;

import com.revature.qmart.dao.StoreDAO;
import com.revature.qmart.models.Items;
import com.revature.qmart.util.annotations.Inject;

import java.io.FileNotFoundException;

public class StoreService {

    @Inject
    private final StoreDAO storeDAO;

    public StoreService(StoreDAO storeDAO) {
        this.storeDAO = storeDAO;
    }
    // get all items

}
