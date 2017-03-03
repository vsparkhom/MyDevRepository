package dao;

import com.vlpa.onlinestore.model.Product;

import java.util.List;

public interface ProductDAO {

    public Product getProduct(Integer id);

    public List<Product> getProductList();

    public void addProduct(String title, Double price);

    public void removeProduct(Integer id);
}
