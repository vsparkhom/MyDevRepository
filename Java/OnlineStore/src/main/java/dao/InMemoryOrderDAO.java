package dao;

import com.vlpa.onlinestore.model.Order;
import com.vlpa.onlinestore.model.Product;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class InMemoryOrderDAO implements OrderDAO {

    private static Integer ORDER_ID_SEQ = 100;

    private static List<Order> inMemoryOrderList;
    private static InMemoryProductDAO productDAO;
    private static InMemoryUserDAO userDAO;

    static {
        System.out.println("Start In Memory Order List initialization...");
        productDAO = InMemoryProductDAO.getInstance();
        userDAO = InMemoryUserDAO.getInstance();
        inMemoryOrderList = new ArrayList<Order>();

        inMemoryOrderList.add(new Order(ORDER_ID_SEQ++, userDAO.getUser(10),
                Arrays.asList(productDAO.getProduct(1), productDAO.getProduct(2))));
        inMemoryOrderList.add(new Order(ORDER_ID_SEQ++, userDAO.getUser(11),
                Arrays.asList(productDAO.getProduct(3), productDAO.getProduct(4))));

        System.out.println("Result:");
        for (Order order : inMemoryOrderList) {
            System.out.println(order);
        }

        System.out.println("Initialization has been finished.");
    }

    private static InMemoryOrderDAO instance;

    public static InMemoryOrderDAO getInstance() {
        if (instance == null) {
            instance = new InMemoryOrderDAO();
        }
        return instance;
    }

    private InMemoryOrderDAO(){}

    @Override
    public Order getOrder(Integer id) {
        for (Order order : inMemoryOrderList) {
            if (id == order.getId()) {
                return order;
            }
        }
        return null;
    }

    @Override
    public List<Order> getOrderList() {
        return inMemoryOrderList;
    }

    @Override
    public void addOrder(Integer userId, Integer[] productIds) {
        List<Product> products = new ArrayList<Product>();
        for (Integer productId : productIds) {
            products.add(productDAO.getProduct(productId));
        }
        inMemoryOrderList.add(new Order(ORDER_ID_SEQ++, userDAO.getUser(userId), products));
    }

    @Override
    public void removeOrder(Integer id) {
        for (Iterator<Order> iterator = inMemoryOrderList.iterator(); iterator.hasNext();) {
            Order order = iterator.next();
            if (id == order.getId()) {
                iterator.remove();
                break;
            }
        }
    }
}
