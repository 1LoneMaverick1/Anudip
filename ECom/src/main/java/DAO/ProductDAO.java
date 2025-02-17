package DAO;

import Model.Product;
import java.util.List;

public interface ProductDAO {
    void saveProduct(Product product);
    Product getProductById(Long id);
    List<Product> getAllProducts();
    void updateProduct(Product product);
    void deleteProduct(Long id);
}

