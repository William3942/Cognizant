
# ECommercePlatform Database Documentation

---

## 1. Database Schema Overview

**Database Name**: `ECommercePlatform`

### 1.1 Users

- `user_id` (INT, PRIMARY KEY, AUTO_INCREMENT)
- `username` (VARCHAR(50))
- `email` (VARCHAR(100), UNIQUE)
- `password` (VARCHAR(255))
- `role` (ENUM: 'customer', 'admin', 'seller')

### 1.2 Products

- `product_id` (INT, PRIMARY KEY, AUTO_INCREMENT)
- `product_name` (VARCHAR(100))
- `category` (VARCHAR(50))
- `price` (DECIMAL(10,2))
- `stock_quantity` (INT)

### 1.3 Orders

- `order_id` (INT, PRIMARY KEY, AUTO_INCREMENT)
- `user_id` (INT, FOREIGN KEY REFERENCES Users(user_id))
- `order_date` (DATE)
- `total_amount` (DECIMAL(10,2))
- `order_status` (ENUM: 'pending', 'processing', 'shipped', 'delivered', 'cancelled')

### 1.4 OrderDetails

- `order_detail_id` (INT, PRIMARY KEY, AUTO_INCREMENT)
- `order_id` (INT, FOREIGN KEY REFERENCES Orders(order_id))
- `product_id` (INT, FOREIGN KEY REFERENCES Products(product_id))
- `quantity` (INT)
- `unit_price` (DECIMAL(10,2))

### 1.5 Payments

- `payment_id` (INT, PRIMARY KEY, AUTO_INCREMENT)
- `order_id` (INT, FOREIGN KEY REFERENCES Orders(order_id))
- `payment_date` (DATE)
- `payment_method` (ENUM: 'credit_card', 'paypal', 'bank_transfer', 'cash_on_delivery')
- `amount` (DECIMAL(10,2))

### 1.6 Reviews

- `review_id` (INT, PRIMARY KEY, AUTO_INCREMENT)
- `product_id` (INT, FOREIGN KEY REFERENCES Products(product_id))
- `user_id` (INT, FOREIGN KEY REFERENCES Users(user_id))
- `review_text` (TEXT)
- `rating` (INT CHECK rating BETWEEN 1 AND 5)
- `review_date` (DATE)

---

## 2. Sample Data (Examples)

### Users

```sql
INSERT INTO Users (username, email, password, role) VALUES
('alice', 'alice@example.com', 'hashed_pass1', 'customer'),
('bob', 'bob@example.com', 'hashed_pass2', 'customer');
```

### Products

```sql
INSERT INTO Products (product_name, category, price, stock_quantity) VALUES
('Laptop', 'Electronics', 1200.00, 50),
('Desk Chair', 'Furniture', 150.00, 20);
```

### Orders and OrderDetails

```sql
INSERT INTO Orders (user_id, order_date, total_amount, order_status) VALUES
(1, '2025-05-01', 1200.00, 'delivered');

INSERT INTO OrderDetails (order_id, product_id, quantity, unit_price) VALUES
(1, 1, 1, 1200.00);
```

### Payments

```sql
INSERT INTO Payments (order_id, payment_date, payment_method, amount) VALUES
(1, '2025-05-02', 'credit_card', 1200.00);
```

### Reviews

```sql
INSERT INTO Reviews (product_id, user_id, review_text, rating, review_date) VALUES
(1, 1, 'Excellent product.', 5, '2025-05-03');
```

---

## 3. Query Explanations

### Retrieve all products in a category

```sql
SELECT * FROM Products WHERE category = 'Electronics';
```

> Gets all products belonging to the 'Electronics' category.

### User details by user_id

```sql
SELECT * FROM Users WHERE user_id = 1;
```

> Returns profile information for a given user.

### Order history of a user

```sql
SELECT * FROM Orders WHERE user_id = 1 ORDER BY order_date DESC;
```

> Lists all orders made by the user.

### Products in an order

```sql
SELECT p.product_name, od.quantity, od.unit_price
FROM OrderDetails od
JOIN Products p ON od.product_id = p.product_id
WHERE od.order_id = 1;
```

> Joins OrderDetails and Products to show items in a specific order.

### Average rating of a product

```sql
SELECT AVG(rating) FROM Reviews WHERE product_id = 1;
```

> Calculates the average score for reviews of a product.

### Total revenue for a month

```sql
SELECT SUM(amount) FROM Payments WHERE MONTH(payment_date) = 5 AND YEAR(payment_date) = 2025;
```

> Summarizes monthly income from successful payments.

---

## 4. Advanced Logic (Stored Procedures and Triggers)

### Trigger to update stock automatically

```sql
CREATE TRIGGER after_orderdetail_insert
AFTER INSERT ON OrderDetails
FOR EACH ROW
BEGIN
    UPDATE Products
    SET stock_quantity = stock_quantity - NEW.quantity
    WHERE product_id = NEW.product_id;
END;
```

### Procedure to update order status

```sql
CREATE PROCEDURE UpdateOrderStatus(IN orderID INT, IN newStatus ENUM('pending', 'processing', 'shipped', 'delivered', 'cancelled'))
BEGIN
    UPDATE Orders SET order_status = newStatus WHERE order_id = orderID;
END;
```

### Most active users report

```sql
CREATE PROCEDURE MostActiveUsers()
BEGIN
    SELECT u.user_id, u.username, COUNT(o.order_id) AS orders_count
    FROM Users u
    LEFT JOIN Orders o ON u.user_id = o.user_id
    GROUP BY u.user_id
    ORDER BY orders_count DESC;
END;
```


