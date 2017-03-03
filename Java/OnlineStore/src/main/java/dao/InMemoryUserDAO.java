package dao;

import com.vlpa.onlinestore.exception.DefaultOnlineStoreException;
import com.vlpa.onlinestore.model.Product;
import com.vlpa.onlinestore.model.User;

import java.util.ArrayList;
import java.util.List;

public class InMemoryUserDAO implements UserDAO {

    private static Integer USER_ID_SEQ = 10;

    private static List<User> inMemoryUserList;

    static {
        System.out.println("Start In Memory User List initialization...");
        inMemoryUserList = new ArrayList<User>();
        inMemoryUserList.add(new User(USER_ID_SEQ++, "Ivan Pupkin", 100));
        inMemoryUserList.add(new User(USER_ID_SEQ++, "John Smith", 200));

        System.out.println("Result:");
        for (User user : inMemoryUserList) {
            System.out.println(user);
        }

        System.out.println("Initialization has been finished.");
    }

    private static InMemoryUserDAO instance;

    public static InMemoryUserDAO getInstance(){
        if (instance == null) {
            instance = new InMemoryUserDAO();
        }
        return instance;
    }

    private InMemoryUserDAO(){
    }

    @Override
    public User getUser(Integer id) {
        for (User user : inMemoryUserList) {
            if (id == user.getId()) {
                return user;
            }
        }
        throw new DefaultOnlineStoreException("User with ID '" + id + "' doesn't exist!");
    }

    @Override
    public List<User> getUserList() {
        return inMemoryUserList;
    }

}
