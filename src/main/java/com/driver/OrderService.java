package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    public void addOrderToDB(Order order){
        orderRepository.addOrderInDB(order);
    }

    public void addPartnerToDB(String partnerID){
        orderRepository.addPartnerInDB(partnerID);
    }

    public void addOrderToPartnerToDB(String partnerID, String orderID){
        orderRepository.addOrderToPartnerInDB(partnerID, orderID);
    }

    public Order getOrderFromDB(String orderID){
        return orderRepository.getOrderByIDInDB(orderID);
    }

    public DeliveryPartner getPartnerFromDB(String partnerID){
        return orderRepository.getPartnerByIDInDB(partnerID);
    }

    public int getOrderCount(String partnerID){
        return orderRepository.getOrderCountForPartner(partnerID);
    }

    public List<String> getListOfOrders(String partnerID){
        return orderRepository.getListOfOrdersForPartner(partnerID);
    }

    public List<String> getAllOrders(){
        return orderRepository.getAllOrdersInDB();
    }

    public int getCountOfUnassignedOrder(){
        return orderRepository.getCountofUnassignedOrderFromDB();
    }

    public int getOrdersLeft(String time, String partnerID){
        return orderRepository.getOrdersleftAfterTime(time, partnerID);
    }

    public String getLastOrder(String partnerID){
        return orderRepository.getLastOrderFromDB(partnerID);
    }

    public String deletePartner(String partnerID){
        return orderRepository.deletePartnerByID(partnerID);
    }

    public String deleteOrder(String orderID){
        return orderRepository.deleteOrderByID(orderID);
    }
}
