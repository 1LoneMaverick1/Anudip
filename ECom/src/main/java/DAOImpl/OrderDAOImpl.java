package DAOImpl;

import DAO.OrderDAO;
import Model.Order;
import Utilities.hibernateUtil; 
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class OrderDAOImpl implements OrderDAO {

    @Override
    public void saveOrder(Order order) {
        Transaction transaction = null;
        try (Session session = hibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(order);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public Order getOrderById(Long id) {
        try (Session session = hibernateUtil.getSessionFactory().openSession()) {
            return session.find(Order.class, id);
        }
    }

    @Override
    public List<Order> getAllOrders() {
        try (Session session = hibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Order", Order.class).list();
        }
    }

    @Override
    public void updateOrder(Order order) {
        Transaction transaction = null;
        try (Session session = hibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.merge(order);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void deleteOrder(Long id) {
        Transaction transaction = null;
        try (Session session = hibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Order order = session.find(Order.class, id);
            if (order != null) {
                session.remove(order);
                transaction.commit();
            }
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }
}
