
# SocialMediaPlatform - Base de Datos

## Esquema de la Base de Datos

### Tabla: Users
```sql
CREATE TABLE Users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    date_of_birth DATE,
    profile_picture VARCHAR(255)
);
```

### Tabla: Posts
```sql
CREATE TABLE Posts (
    post_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    post_text TEXT,
    post_date DATETIME DEFAULT CURRENT_TIMESTAMP,
    media_url VARCHAR(255),
    FOREIGN KEY (user_id) REFERENCES Users(user_id)
);
```

### Tabla: Comments
```sql
CREATE TABLE Comments (
    comment_id INT AUTO_INCREMENT PRIMARY KEY,
    post_id INT,
    user_id INT,
    comment_text TEXT,
    comment_date DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (post_id) REFERENCES Posts(post_id),
    FOREIGN KEY (user_id) REFERENCES Users(user_id)
);
```

### Tabla: Likes
```sql
CREATE TABLE Likes (
    like_id INT AUTO_INCREMENT PRIMARY KEY,
    post_id INT,
    user_id INT,
    like_date DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (post_id) REFERENCES Posts(post_id),
    FOREIGN KEY (user_id) REFERENCES Users(user_id)
);
```

### Tabla: Follows
```sql
CREATE TABLE Follows (
    follower_id INT,
    following_id INT,
    follow_date DATETIME DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (follower_id, following_id),
    FOREIGN KEY (follower_id) REFERENCES Users(user_id),
    FOREIGN KEY (following_id) REFERENCES Users(user_id)
);
```

### Tabla: Messages
```sql
CREATE TABLE Messages (
    message_id INT AUTO_INCREMENT PRIMARY KEY,
    sender_id INT,
    receiver_id INT,
    message_text TEXT,
    message_date DATETIME DEFAULT CURRENT_TIMESTAMP,
    is_read BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (sender_id) REFERENCES Users(user_id),
    FOREIGN KEY (receiver_id) REFERENCES Users(user_id)
);
```

### Tabla: Notifications
```sql
CREATE TABLE Notifications (
    notification_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    notification_text TEXT,
    notification_date DATETIME DEFAULT CURRENT_TIMESTAMP,
    is_read BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (user_id) REFERENCES Users(user_id)
);
```

## Datos de Ejemplo

```sql
INSERT INTO Users (username, email, password, date_of_birth, profile_picture) VALUES
('alice', 'alice@example.com', 'pass1', '1990-01-01', 'pic1.jpg'),
('bob', 'bob@example.com', 'pass2', '1992-02-02', 'pic2.jpg'),
('carol', 'carol@example.com', 'pass3', '1994-03-03', 'pic3.jpg');
```

```sql
INSERT INTO Posts (user_id, post_text, media_url) VALUES
(1, 'Hello World!', 'img1.jpg'),
(2, 'Check out this pic.', 'img2.jpg');
```

```sql
INSERT INTO Comments (post_id, user_id, comment_text) VALUES
(1, 2, 'Nice post!'),
(2, 1, 'Awesome!');
```

## Consultas SQL

### 1. Publicaciones de la línea de tiempo de un usuario
```sql
SELECT p.*, u.username 
FROM Posts p 
JOIN Users u ON p.user_id = u.user_id 
ORDER BY post_date DESC;
```

### 2. Comentarios y "me gusta" de una publicación
```sql
SELECT c.comment_text, u.username 
FROM Comments c 
JOIN Users u ON c.user_id = u.user_id 
WHERE c.post_id = 1;

SELECT COUNT(*) AS likes_count 
FROM Likes 
WHERE post_id = 1;
```

### 3. Seguidores de un usuario
```sql
SELECT u.username 
FROM Follows f 
JOIN Users u ON f.follower_id = u.user_id 
WHERE f.following_id = 1;
```

### 4. Mensajes no leídos
```sql
SELECT * 
FROM Messages 
WHERE receiver_id = 1 AND is_read = FALSE;
```

### 5. Publicaciones con más "me gusta"
```sql
SELECT p.post_id, COUNT(l.like_id) AS like_count 
FROM Posts p 
JOIN Likes l ON p.post_id = l.post_id 
GROUP BY p.post_id 
ORDER BY like_count DESC;
```

### 6. Últimas notificaciones
```sql
SELECT * 
FROM Notifications 
WHERE user_id = 1 
ORDER BY notification_date DESC;
```

## Modificación de Datos

### Agregar publicación
```sql
INSERT INTO Posts (user_id, post_text, media_url) 
VALUES (1, 'New adventure!', 'adventure.jpg');
```

### Comentar en publicación
```sql
INSERT INTO Comments (post_id, user_id, comment_text) 
VALUES (1, 3, 'Love this!');
```

### Actualizar perfil
```sql
UPDATE Users 
SET profile_picture = 'newpic.jpg' 
WHERE user_id = 1;
```

### Eliminar "me gusta"
```sql
DELETE FROM Likes 
WHERE post_id = 1 AND user_id = 2;
```

## Consultas Complejas

### Usuarios con más seguidores
```sql
SELECT u.username, COUNT(f.follower_id) AS followers 
FROM Users u 
JOIN Follows f ON u.user_id = f.following_id 
GROUP BY u.user_id 
ORDER BY followers DESC;
```

### Usuarios más activos
```sql
SELECT u.username, COUNT(DISTINCT p.post_id) + COUNT(DISTINCT c.comment_id) + COUNT(DISTINCT l.like_id) AS activity_score
FROM Users u
LEFT JOIN Posts p ON u.user_id = p.user_id
LEFT JOIN Comments c ON u.user_id = c.user_id
LEFT JOIN Likes l ON u.user_id = l.user_id
GROUP BY u.user_id 
ORDER BY activity_score DESC;
```

### Promedio de comentarios por publicación
```sql
SELECT AVG(comment_count) 
FROM (
    SELECT COUNT(*) AS comment_count 
    FROM Comments 
    GROUP BY post_id
) AS counts;
```

## Triggers y Procedimientos

### Notificar nuevo mensaje
```sql
DELIMITER //
CREATE TRIGGER notify_new_message AFTER INSERT ON Messages
FOR EACH ROW
BEGIN
    INSERT INTO Notifications (user_id, notification_text) 
    VALUES (NEW.receiver_id, 'You have a new message');
END;//
DELIMITER ;
```

### Recomendación de usuarios para seguir
```sql
DELIMITER //
CREATE PROCEDURE RecommendFollows(IN userId INT)
BEGIN
    SELECT DISTINCT u.user_id, u.username 
    FROM Users u
    WHERE u.user_id != userId
    AND u.user_id NOT IN (
        SELECT following_id FROM Follows WHERE follower_id = userId
    );
END;//
DELIMITER ;
```
