package com.Business.ECom;

import DAOImpl.ProductDAOImpl;
import DAOImpl.UserDAOImpl;
import DAOImpl.OrderDAOImpl;
import Model.Product;
import Model.User;
import Model.Order;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ProductDAOImpl productDAO = new ProductDAOImpl();
        UserDAOImpl userDAO = new UserDAOImpl();
        OrderDAOImpl orderDAO = new OrderDAOImpl();

        int mainChoice;
        while (true) {
            System.out.println("\n=== Main Menu ===");
            System.out.println("1. Manage Products");
            System.out.println("2. Manage Users");
            System.out.println("3. Manage Orders");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            mainChoice = Integer.parseInt(scanner.nextLine());
            
            switch (mainChoice) {
                case 1:
                    manageProducts(scanner, productDAO);
                    break;
                case 2:
                    manageUsers(scanner, userDAO);
                    break;
                case 3:
                    manageOrders(scanner, orderDAO, productDAO, userDAO);
                    break;
                case 4:
                    System.out.println("Exiting application. Goodbye!");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void manageProducts(Scanner scanner, ProductDAOImpl productDAO) {
        int choice;
        while (true) {
            System.out.println("\n=== Manage Products ===");
            System.out.println("1. Create Product");
            System.out.println("2. List All Products");
            System.out.println("3. Update Product");
            System.out.println("4. Delete Product");
            System.out.println("5. Back to Main Menu");
            System.out.print("Enter your choice: ");
            choice = Integer.parseInt(scanner.nextLine());
            
            switch (choice) {
                case 1:
                    Product product = new Product();
                    System.out.print("Enter product name: ");
                    product.setName(scanner.nextLine());
                    System.out.print("Enter product description: ");
                    product.setDescription(scanner.nextLine());
                    System.out.print("Enter product price: ");
                    product.setPrice(Double.parseDouble(scanner.nextLine()));
                    System.out.print("Enter product stock: ");
                    product.setStock(Integer.parseInt(scanner.nextLine()));
                    productDAO.saveProduct(product);
                    System.out.println("Product created successfully!");
                    break;
                case 2:
                    System.out.println("\n--- All Products ---");
                    productDAO.getAllProducts().forEach(System.out::println);
                    break;
                case 3:
                    System.out.print("Enter product id to update: ");
                    Long prodId = Long.parseLong(scanner.nextLine());
                    Product prodToUpdate = productDAO.getProductById(prodId);
                    if (prodToUpdate != null) {
                        System.out.print("Enter new name (current: " + prodToUpdate.getName() + "): ");
                        prodToUpdate.setName(scanner.nextLine());
                        System.out.print("Enter new description (current: " + prodToUpdate.getDescription() + "): ");
                        prodToUpdate.setDescription(scanner.nextLine());
                        System.out.print("Enter new price (current: " + prodToUpdate.getPrice() + "): ");
                        prodToUpdate.setPrice(Double.parseDouble(scanner.nextLine()));
                        System.out.print("Enter new stock (current: " + prodToUpdate.getStock() + "): ");
                        prodToUpdate.setStock(Integer.parseInt(scanner.nextLine()));
                        productDAO.updateProduct(prodToUpdate);
                        System.out.println("Product updated successfully!");
                    } else {
                        System.out.println("Product not found!");
                    }
                    break;
                case 4:
                    System.out.print("Enter product id to delete: ");
                    Long delProdId = Long.parseLong(scanner.nextLine());
                    productDAO.deleteProduct(delProdId);
                    System.out.println("Product deleted successfully (if it existed)!");
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void manageUsers(Scanner scanner, UserDAOImpl userDAO) {
        int choice;
        while (true) {
            System.out.println("\n=== Manage Users ===");
            System.out.println("1. Create User");
            System.out.println("2. List All Users");
            System.out.println("3. Update User");
            System.out.println("4. Delete User");
            System.out.println("5. Back to Main Menu");
            System.out.print("Enter your choice: ");
            choice = Integer.parseInt(scanner.nextLine());
            
            switch (choice) {
                case 1:
                    User user = new User();
                    System.out.print("Enter user name: ");
                    user.setName(scanner.nextLine());
                    System.out.print("Enter user email: ");
                    user.setEmail(scanner.nextLine());
                    System.out.print("Enter user password: ");
                    user.setPassword(scanner.nextLine());
                    System.out.print("Enter user role (ADMIN/USER): ");
                    user.setRole(User.Role.valueOf(scanner.nextLine().toUpperCase()));
                    userDAO.saveUser(user);
                    System.out.println("User created successfully!");
                    break;
                case 2:
                    System.out.println("\n--- All Users ---");
                    userDAO.getAllUsers().forEach(System.out::println);
                    break;
                case 3:
                    System.out.print("Enter user id to update: ");
                    Long userId = Long.parseLong(scanner.nextLine());
                    User userToUpdate = userDAO.getUserById(userId);
                    if (userToUpdate != null) {
                        System.out.print("Enter new name (current: " + userToUpdate.getName() + "): ");
                        userToUpdate.setName(scanner.nextLine());
                        System.out.print("Enter new email (current: " + userToUpdate.getEmail() + "): ");
                        userToUpdate.setEmail(scanner.nextLine());
                        System.out.print("Enter new password (current: " + userToUpdate.getPassword() + "): ");
                        userToUpdate.setPassword(scanner.nextLine());
                        System.out.print("Enter new role (ADMIN/USER) (current: " + userToUpdate.getRole() + "): ");
                        userToUpdate.setRole(User.Role.valueOf(scanner.nextLine().toUpperCase()));
                        userDAO.updateUser(userToUpdate);
                        System.out.println("User updated successfully!");
                    } else {
                        System.out.println("User not found!");
                    }
                    break;
                case 4:
                    System.out.print("Enter user id to delete: ");
                    Long delUserId = Long.parseLong(scanner.nextLine());
                    userDAO.deleteUser(delUserId);
                    System.out.println("User deleted successfully (if it existed)!");
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void manageOrders(Scanner scanner, OrderDAOImpl orderDAO, ProductDAOImpl productDAO, UserDAOImpl userDAO) {
        int choice;
        while (true) {
            System.out.println("\n=== Manage Orders ===");
            System.out.println("1. Create Order");
            System.out.println("2. List All Orders");
            System.out.println("3. Update Order");
            System.out.println("4. Delete Order");
            System.out.println("5. Back to Main Menu");
            System.out.print("Enter your choice: ");
            choice = Integer.parseInt(scanner.nextLine());
            
            switch (choice) {
                case 1:
                    Order order = new Order();
                    System.out.print("Enter user id for the order: ");
                    Long userId = Long.parseLong(scanner.nextLine());
                    User user = userDAO.getUserById(userId);
                    if (user == null) {
                        System.out.println("User not found!");
                        break;
                    }
                    order.setUser(user);

                    System.out.print("Enter product id for the order: ");
                    Long productId = Long.parseLong(scanner.nextLine());
                    Product product = productDAO.getProductById(productId);
                    if (product == null) {
                        System.out.println("Product not found!");
                        break;
                    }
                    order.setProduct(product);

                    System.out.print("Enter quantity: ");
                    int quantity = Integer.parseInt(scanner.nextLine());
                    order.setQuantity(quantity);
                    order.setTotalPrice(product.getPrice() * quantity);
                    System.out.print("Enter order status: ");
                    order.setStatus(scanner.nextLine());
                    orderDAO.saveOrder(order);
                    System.out.println("Order created successfully!");
                    break;
                case 2:
                    System.out.println("\n--- All Orders ---");
                    orderDAO.getAllOrders().forEach(System.out::println);
                    break;
                case 3:
                    System.out.print("Enter order id to update: ");
                    Long orderId = Long.parseLong(scanner.nextLine());
                    Order orderToUpdate = orderDAO.getOrderById(orderId);
                    if (orderToUpdate != null) {
                        System.out.print("Enter new order status (current: " + orderToUpdate.getStatus() + "): ");
                        orderToUpdate.setStatus(scanner.nextLine());
                        orderDAO.updateOrder(orderToUpdate);
                        System.out.println("Order updated successfully!");
                    } else {
                        System.out.println("Order not found!");
                    }
                    break;
                case 4:
                    System.out.print("Enter order id to delete: ");
                    Long delOrderId = Long.parseLong(scanner.nextLine());
                    orderDAO.deleteOrder(delOrderId);
                    System.out.println("Order deleted successfully (if it existed)!");
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
