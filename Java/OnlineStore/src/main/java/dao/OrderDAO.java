package dao;

import com.vlpa.onlinestore.model.Order;

import java.util.List;

public interface OrderDAO {

    public Order getOrder(Integer id);
    public List<Order> getOrderList();
    public void addOrder(Integer userId, Integer[] productId);
    public void removeOrder(Integer id);

}
