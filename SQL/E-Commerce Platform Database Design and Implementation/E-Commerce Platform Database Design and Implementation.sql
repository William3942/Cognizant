CREATE DATABASE IF NOT EXISTS ECommercePlatform;
USE ECommercePlatform;

CREATE TABLE Users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    role ENUM('customer', 'admin', 'seller') DEFAULT 'customer'
);

CREATE TABLE Products (
    product_id INT AUTO_INCREMENT PRIMARY KEY,
    product_name VARCHAR(100) NOT NULL,
    category VARCHAR(50),
    price DECIMAL(10,2) NOT NULL,
    stock_quantity INT DEFAULT 0
);

CREATE TABLE Orders (
    order_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    order_date DATE NOT NULL,
    total_amount DECIMAL(10,2) NOT NULL,
    order_status ENUM('pending', 'processing', 'shipped', 'delivered', 'cancelled') DEFAULT 'pending',
    FOREIGN KEY (user_id) REFERENCES Users(user_id)
);

CREATE TABLE OrderDetails (
    order_detail_id INT AUTO_INCREMENT PRIMARY KEY,
    order_id INT NOT NULL,
    product_id INT NOT NULL,
    quantity INT NOT NULL,
    unit_price DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (order_id) REFERENCES Orders(order_id),
    FOREIGN KEY (product_id) REFERENCES Products(product_id)
);

CREATE TABLE Payments (
    payment_id INT AUTO_INCREMENT PRIMARY KEY,
    order_id INT NOT NULL,
    payment_date DATE NOT NULL,
    payment_method ENUM('credit_card', 'paypal', 'bank_transfer', 'cash_on_delivery') NOT NULL,
    amount DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (order_id) REFERENCES Orders(order_id)
);

CREATE TABLE Reviews (
    review_id INT AUTO_INCREMENT PRIMARY KEY,
    product_id INT NOT NULL,
    user_id INT NOT NULL,
    review_text TEXT,
    rating INT CHECK (rating BETWEEN 1 AND 5),
    review_date DATE NOT NULL,
    FOREIGN KEY (product_id) REFERENCES Products(product_id),
    FOREIGN KEY (user_id) REFERENCES Users(user_id)
);


INSERT INTO Users (username, email, password, role) VALUES
('alice', 'alice@example.com', 'hashed_pass1', 'customer'),
('bob', 'bob@example.com', 'hashed_pass2', 'customer'),
('carol', 'carol@example.com', 'hashed_pass3', 'seller'),
('admin', 'admin@example.com', 'hashed_pass4', 'admin');


INSERT INTO Products (product_name, category, price, stock_quantity) VALUES
('Laptop', 'Electronics', 1200.00, 50),
('Smartphone', 'Electronics', 800.00, 100),
('Desk Chair', 'Furniture', 150.00, 20),
('Coffee Table', 'Furniture', 200.00, 15),
('Running Shoes', 'Apparel', 90.00, 200),
('Jeans', 'Apparel', 60.00, 150);


INSERT INTO Orders (user_id, order_date, total_amount, order_status) VALUES
(1, '2025-05-01', 1200.00, 'delivered'),
(2, '2025-05-03', 950.00, 'processing'),
(1, '2025-05-10', 150.00, 'pending');


INSERT INTO OrderDetails (order_id, product_id, quantity, unit_price) VALUES
(1, 1, 1, 1200.00),
(2, 2, 1, 800.00),
(2, 5, 2, 75.00), 
(3, 3, 1, 150.00);

INSERT INTO Payments (order_id, payment_date, payment_method, amount) VALUES
(1, '2025-05-02', 'credit_card', 1200.00),
(2, '2025-05-04', 'paypal', 950.00);

INSERT INTO Reviews (product_id, user_id, review_text, rating, review_date) VALUES
(1, 1, 'Excellent laptop, very fast.', 5, '2025-05-05'),
(2, 2, 'Good smartphone but battery life could be better.', 4, '2025-05-07'),
(3, 1, 'Comfortable chair for the office.', 4, '2025-05-11');

SELECT * FROM Products
WHERE category = 'Electronics';

SELECT * FROM Users
WHERE user_id = 1;

SELECT * FROM Orders
WHERE user_id = 1
ORDER BY order_date DESC;

SELECT p.product_name, od.quantity, od.unit_price
FROM OrderDetails od
JOIN Products p ON od.product_id = p.product_id
WHERE od.order_id = 2;

SELECT AVG(rating) AS average_rating
FROM Reviews
WHERE product_id = 1;

SELECT SUM(amount) AS total_revenue
FROM Payments
WHERE MONTH(payment_date) = 5 AND YEAR(payment_date) = 2025;

INSERT INTO Products (product_name, category, price, stock_quantity)
VALUES ('Wireless Mouse', 'Electronics', 25.00, 300);

START TRANSACTION;

INSERT INTO Orders (user_id, order_date, total_amount, order_status)
VALUES (2, CURDATE(), 60.00, 'pending');

SET @new_order_id = LAST_INSERT_ID();

INSERT INTO OrderDetails (order_id, product_id, quantity, unit_price)
VALUES (@new_order_id, 6, 1, 60.00);

INSERT INTO Payments (order_id, payment_date, payment_method, amount)
VALUES (@new_order_id, CURDATE(), 'credit_card', 60.00);

COMMIT;

UPDATE Products
SET stock_quantity = stock_quantity - 1
WHERE product_id = 6;

DELETE FROM Reviews
WHERE review_id = 3 AND user_id = 1;

SELECT p.product_id, p.product_name, SUM(od.quantity) AS total_sold
FROM OrderDetails od
JOIN Products p ON od.product_id = p.product_id
GROUP BY p.product_id, p.product_name
ORDER BY total_sold DESC
LIMIT 5;

SELECT u.user_id, u.username, SUM(o.total_amount) AS total_spent
FROM Orders o
JOIN Users u ON o.user_id = u.user_id
GROUP BY u.user_id, u.username
HAVING total_spent > 1000;

SELECT p.category, AVG(r.rating) AS avg_category_rating
FROM Reviews r
JOIN Products p ON r.product_id = p.product_id
GROUP BY p.category;

DELIMITER //

CREATE PROCEDURE UpdateOrderStatus(IN orderID INT, IN newStatus ENUM('pending', 'processing', 'shipped', 'delivered', 'cancelled'))
BEGIN
    UPDATE Orders
    SET order_status = newStatus
    WHERE order_id = orderID;
END //

DELIMITER ;

DELIMITER //

CREATE TRIGGER after_orderdetail_insert
AFTER INSERT ON OrderDetails
FOR EACH ROW
BEGIN
    UPDATE Products
    SET stock_quantity = stock_quantity - NEW.quantity
    WHERE product_id = NEW.product_id;
END //

DELIMITER ;

DELIMITER //

CREATE PROCEDURE MostActiveUsers()
BEGIN
    SELECT u.user_id, u.username, COUNT(o.order_id) AS orders_count
    FROM Users u
    LEFT JOIN Orders o ON u.user_id = o.user_id
    GROUP BY u.user_id, u.username
    ORDER BY orders_count DESC
    LIMIT 10;
END //

DELIMITER ;
