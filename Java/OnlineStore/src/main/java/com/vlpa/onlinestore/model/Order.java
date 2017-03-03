package com.vlpa.onlinestore.model;

import java.util.List;

public class Order {

    private int id;
    private List<Product> products;
    private User user;

    public Order(int id) {
        this.id = id;
    }

    public Order(int id, User user, List<Product> products) {
        this.id = id;
        this.user = user;
        this.products = products;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("[");
        for(Product product : products){
            builder.append(product.getId()).append(",");
        }
        int lastCommaIndex = builder.lastIndexOf(",");
        builder.delete(lastCommaIndex, lastCommaIndex + 1);
        builder.append("]");

        return "Order{" +
                "id=" + id +
                ", products=" + builder.toString() +
                ", user=" + user +
                '}';
    }
}
