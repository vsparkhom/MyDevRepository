package service;

import com.vlpa.onlinestore.exception.DefaultOnlineStoreException;
import com.vlpa.onlinestore.model.Order;
import com.vlpa.onlinestore.model.Product;
import com.vlpa.onlinestore.model.User;
import dao.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class OnlineStoreConsole {

    public static void main(String[] args) {
        System.out.println("OnlineStoreConsole START");
        ProductDAO productDAO = InMemoryProductDAO.getInstance();
        UserDAO userDAO = InMemoryUserDAO.getInstance();
        OrderDAO orderDAO = InMemoryOrderDAO.getInstance();

        String command = "";
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("-----------------------------------");
            System.out.println("Available commands:");
            System.out.println("- Product cmd: product add/remove/list");
            System.out.println("- User cmd: user list");
            System.out.println("- Order cmd: order add/list");
            System.out.println("Type 'exit' to close console.");
            System.out.println("-----------------------------------");
            command = scanner.nextLine();

            try {
                if ("product add".equals(command)) {
                    System.out.println("Enter product name:");
                    String productName = scanner.next();
                    System.out.println("Enter product price:");
                    double productPrice = Double.valueOf(scanner.next());
                    productDAO.addProduct(productName, productPrice);
                    System.out.println("Product added");
                } else if ("product list".equals(command)) {
                    System.out.println("Product list:");
                    for(Product product : productDAO.getProductList()) {
                        System.out.println(product);
                    }
                } else if ("product remove".equals(command)) {
                    System.out.println("Enter product ID:");
                    int productID = Integer.valueOf(scanner.next());
                    productDAO.removeProduct(productID);
                    System.out.println("Product removed");
                } else if ("user list".equals(command)) {
                    System.out.println("User list:");
                    for(User user : userDAO.getUserList()) {
                        System.out.println(user);
                    }
                } else if ("order list".equals(command)) {
                    System.out.println("Order list:");
                    for(Order order : orderDAO.getOrderList()) {
                        System.out.println(order);
                    }
                } else if ("order add".equals(command)) {
                    System.out.println("Enter user ID:");
                    int userId = Integer.valueOf(scanner.next());
                    System.out.println("Specify product ids separated with comma:");
                    String productIdsString = scanner.next();
                    String[] productIdsStringList = productIdsString.split(",");
                    List<Integer> productIdsList = new ArrayList<Integer>();
                    for (String productIdString : productIdsStringList) {
                        productIdsList.add(Integer.valueOf(productIdString));
                    }
                    orderDAO.addOrder(userId, productIdsList.toArray(new Integer[]{}));
                    System.out.println("Order added");

                } else if (!"exit".equals(command)){
                    System.err.println("Unknown command: " + command);
                }
            } catch(NumberFormatException nfe) {
                System.err.println(nfe.getMessage());
                System.err.println("Please enter correct number!");
            } catch (DefaultOnlineStoreException storeException) {
                System.err.println(storeException.getMessage());
            }

        } while(!command.equals("exit"));


        System.out.println("OnlineStoreConsole END");
    }



}
