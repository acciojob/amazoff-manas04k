package com.driver;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class OrderRepository {
    Map<String, Order> orders = new HashMap<>();
    Map<String, DeliveryPartner> deliveryPartners = new HashMap<>();
    Map<String , List<String>> orderPartnerDB = new HashMap<>();

    public void addOrderInDB(Order order){
        if(!orders.containsKey(order.getId())) {
            orders.put(order.getId(), order);
        }
    }

    public void addPartnerInDB(String id){
        DeliveryPartner deliveryPartner = new DeliveryPartner(id);
        if(!deliveryPartners.containsKey(id)) {
            deliveryPartners.put(deliveryPartner.getId(), deliveryPartner);
        }
    }

    public void addOrderToPartnerInDB(String partnerID, String orderID){
        if(orderPartnerDB.containsKey(partnerID)){
            List<String> orders = orderPartnerDB.get(partnerID);
            orders.add(orderID);
            orderPartnerDB.put(partnerID, orders);
        }
        else{
            List<String> orders = new ArrayList<>();
            orders.add(orderID);
            orderPartnerDB.put(partnerID, orders);
        }

    }

    public Order getOrderByIDInDB(String orderID){
        return orders.get(orderID);
    }

    public DeliveryPartner getPartnerByIDInDB(String partnerID){
        return deliveryPartners.get(partnerID);
    }

    public int getOrderCountForPartner(String partnerID){
        return orderPartnerDB.get(partnerID).size();
    }

    public List<String> getListOfOrdersForPartner(String partnerID){
        return orderPartnerDB.get(partnerID);
    }

    public List<String> getAllOrdersInDB(){
        List<String> orderId = new ArrayList<>();

        for(String id:orders.keySet()){
            orderId.add(id);
        }

        return orderId;
    }

    public int getCountofUnassignedOrderFromDB(){

        int size = orders.size();
        int orderCount = 0;
        for(String partnerID:deliveryPartners.keySet()){
            orderCount+=deliveryPartners.get(partnerID).getNumberOfOrders();
        }
        int count = size - orderCount;

        return count;
    }

    public int getOrdersleftAfterTime(String time, String partnerID){

        String[] arr = time.split(":");
        int timeInt = Integer.parseInt(arr[0])*60 + Integer.parseInt(arr[1]);

        ArrayList<String> orderiD = (ArrayList<String>) orderPartnerDB.get(partnerID);
        int count = 0;

        for(int i=0;i<orderiD.size();i++){
            if(orders.get(orderiD.get(i)).getDeliveryTime()>timeInt){
                count++;
            }
        }

        return count;
    }

    public String getLastOrderFromDB(String partnerID){

        ArrayList<String> orderiD = (ArrayList<String>) orderPartnerDB.get(partnerID);

        int maxtime = 0;
        for(int i=0;i<orderiD.size();i++){
            if(orders.get(orderiD.get(i)).getDeliveryTime()>maxtime){
                maxtime = orders.get(orderiD.get(i)).getDeliveryTime();
            }
        }
        String tym = Integer.toString(maxtime/60)+":"+Integer.toString(maxtime%60);

        return tym;

    }

    public String deletePartnerByID(String partnerID){
        if(deliveryPartners.containsKey(partnerID)){
            deliveryPartners.remove(partnerID);
        }

        return partnerID;
    }

    public String deleteOrderByID(String orderID){
        if(orders.containsKey(orderID)){
            orders.remove(orderID);
        }

        for(String partnerID: orderPartnerDB.keySet()){
            if(orderPartnerDB.get(partnerID).contains(orderID)){
                orderPartnerDB.get(partnerID).remove(orderID);
            }
        }

        return orderID;
    }


}
