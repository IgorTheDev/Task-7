package web.DAO;

import jdk.jfr.Percentage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager em;


    @Override
    public User findById(Long id) {
       return em.find(User.class, id);
    }

    @Override
    public void add(User user) {
        if(user.getId()==null || findById(user.getId()) == null){
            em.persist(user);
        }else em.merge(user);


    }

    @Override
    public void delete(Long id) {
        User user = em.find(User.class, id);
        if (user != null) {
            em.remove(user);
        }
    }

    @Override
    public List<User> findAll() {
       return em.createQuery("select u from User u", User.class).getResultList();
    }
}
