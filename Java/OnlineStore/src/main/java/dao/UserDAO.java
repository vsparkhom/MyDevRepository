package dao;

import com.vlpa.onlinestore.model.User;

import java.util.List;

public interface UserDAO {

    public User getUser(Integer id);

    public List<User> getUserList();

}
