package hiber.dao;


import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   public User userModelCar(String model, int series) {
      String HQL = "FROM User user" +
              " LEFT OUTER JOIN FETCH user.car test" +
              " WHERE test.model = :Model" +
              " AND test.series = :Series";
      TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery(HQL);
      query.setParameter("Model", model);
      query.setParameter("Series", series);
      return query.getSingleResult();
   }

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }


   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("FROM User");
      return query.getResultList();
   }

}
