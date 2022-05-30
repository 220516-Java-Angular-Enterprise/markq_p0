package com.revature.mindlight.services;

import com.revature.mindlight.dao.CartDAO;
import com.revature.mindlight.dao.OrderDAO;
import com.revature.mindlight.models.Items;
import com.revature.mindlight.models.Order;
import com.revature.mindlight.models.User;
import com.revature.mindlight.util.annotations.Inject;

public class OrderService {

    @Inject
    private final OrderDAO orderDAO;

    @Inject
    private final CartDAO cartDAO;

    public OrderService(OrderDAO orderDAO, CartDAO cartDAO) {
        this.orderDAO = orderDAO;
        this.cartDAO = cartDAO;
    }

    public void makeOrder(Order order) {
        orderDAO.save(order);
    }

    public void makeCartOrder(Order order) {
            cartDAO.save(order);
    }
}
