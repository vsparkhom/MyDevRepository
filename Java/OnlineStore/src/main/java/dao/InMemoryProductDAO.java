package dao;

import com.vlpa.onlinestore.exception.DefaultOnlineStoreException;
import com.vlpa.onlinestore.model.Product;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class InMemoryProductDAO implements ProductDAO {

    private static Integer PRODUCT_ID_SEQ = 1;

    private static List<Product> inMemoryProductList;

    static {
        System.out.println("Start In Memory Product List initialization...");
        inMemoryProductList = new ArrayList<Product>();
        inMemoryProductList.add(new Product(PRODUCT_ID_SEQ++, "iPhone 5", 200));
        inMemoryProductList.add(new Product(PRODUCT_ID_SEQ++, "iPhone 5s", 250));
        inMemoryProductList.add(new Product(PRODUCT_ID_SEQ++, "iPhone 6", 500));
        inMemoryProductList.add(new Product(PRODUCT_ID_SEQ++, "iPhone 6s", 550));
        inMemoryProductList.add(new Product(PRODUCT_ID_SEQ++, "iPhone 7", 700));

        System.out.println("Result:");
        for (Product product : inMemoryProductList) {
            System.out.println(product);
        }
        System.out.println("Initialization has been finished.");
    }

    private static InMemoryProductDAO instance;

    public static InMemoryProductDAO getInstance(){
        if (instance == null) {
            instance = new InMemoryProductDAO();
        }
        return instance;
    }

    private InMemoryProductDAO(){}

    @Override
    public Product getProduct(Integer id) {
        for (Product product : inMemoryProductList) {
            if (id == product.getId()) {
                return product;
            }
        }
        throw new DefaultOnlineStoreException("Product with ID '" + id + "' doesn't exist!");
    }

    @Override
    public List<Product> getProductList() {
        return inMemoryProductList;
    }

    @Override
    public void addProduct(String title, Double price) {
        inMemoryProductList.add(new Product(PRODUCT_ID_SEQ++, title, price));
    }

    @Override
    public void removeProduct(Integer id) {
        for (Iterator<Product> iterator = inMemoryProductList.iterator();iterator.hasNext();){
            Product product = iterator.next();
            if (id == product.getId()) {
                iterator.remove();
                break;
            }
        }
    }

}
