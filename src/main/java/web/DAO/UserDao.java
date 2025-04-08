package web.DAO;

import web.model.User;

import java.util.List;

public interface UserDao {
    void add(User user);
    void delete(Long id);
    List<User> findAll();
    User findById(Long id);
}
