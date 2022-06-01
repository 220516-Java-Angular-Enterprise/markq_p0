package com.revature.mindlight.services;

import com.revature.mindlight.dao.StoreDAO;
import com.revature.mindlight.models.Mindlight;
import com.revature.mindlight.models.Order;
import com.revature.mindlight.util.annotations.Inject;

import java.util.List;

public class MindlightService {

    @Inject
    private final StoreDAO storeDAO;

    public MindlightService(StoreDAO storeDAO) {
        this.storeDAO = storeDAO;
    }

    public List<Mindlight> allStores() {
        List<Mindlight> stores = storeDAO.getAll();
        return stores;
    }
}
