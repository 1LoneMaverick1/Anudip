package DAOImpl;

import DAO.ProductDAO;
import Model.Product;
import Utilities.hibernateUtil; 
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class ProductDAOImpl implements ProductDAO {

    @Override
    public void saveProduct(Product product) {
        Transaction transaction = null;
        try (Session session = hibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(product);  
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public Product getProductById(Long id) {
        try (Session session = hibernateUtil.getSessionFactory().openSession()) {
            return session.find(Product.class, id);
        }
    }

    @Override
    public List<Product> getAllProducts() {
        try (Session session = hibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Product", Product.class).list();
        }
    }

    @Override
    public void updateProduct(Product product) {
        Transaction transaction = null;
        try (Session session = hibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.merge(product);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void deleteProduct(Long id) {
        Transaction transaction = null;
        try (Session session = hibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Product product = session.find(Product.class, id);
            if (product != null) {
                session.remove(product);
                transaction.commit();
            }
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }
}
