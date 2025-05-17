-- Create Database
CREATE DATABASE SocialMediaPlatform;

CREATE TABLE Users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    date_of_birth DATE,
    profile_picture VARCHAR(255)
);

CREATE TABLE Posts (
    post_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    post_text TEXT,
    post_date DATETIME DEFAULT CURRENT_TIMESTAMP,
    media_url VARCHAR(255),
    FOREIGN KEY (user_id) REFERENCES Users(user_id)
);

CREATE TABLE Comments (
    comment_id INT AUTO_INCREMENT PRIMARY KEY,
    post_id INT,
    user_id INT,
    comment_text TEXT,
    comment_date DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (post_id) REFERENCES Posts(post_id),
    FOREIGN KEY (user_id) REFERENCES Users(user_id)
);

CREATE TABLE Likes (
    like_id INT AUTO_INCREMENT PRIMARY KEY,
    post_id INT,
    user_id INT,
    like_date DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (post_id) REFERENCES Posts(post_id),
    FOREIGN KEY (user_id) REFERENCES Users(user_id)
);

CREATE TABLE Follows (
    follower_id INT,
    following_id INT,
    follow_date DATETIME DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (follower_id, following_id),
    FOREIGN KEY (follower_id) REFERENCES Users(user_id),
    FOREIGN KEY (following_id) REFERENCES Users(user_id)
);

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

CREATE TABLE Notifications (
    notification_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    notification_text TEXT,
    notification_date DATETIME DEFAULT CURRENT_TIMESTAMP,
    is_read BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (user_id) REFERENCES Users(user_id)
);

INSERT INTO Users (username, email, password, date_of_birth, profile_picture) VALUES
('alice', 'alice@example.com', 'pass1', '1990-01-01', 'pic1.jpg'),
('bob', 'bob@example.com', 'pass2', '1992-02-02', 'pic2.jpg'),
('carol', 'carol@example.com', 'pass3', '1994-03-03', 'pic3.jpg');

INSERT INTO Posts (user_id, post_text, media_url) VALUES
(1, 'Hello World!', 'img1.jpg'),
(2, 'Check out this pic.', 'img2.jpg');

INSERT INTO Comments (post_id, user_id, comment_text) VALUES
(1, 2, 'Nice post!'),
(2, 1, 'Awesome!');

INSERT INTO Likes (post_id, user_id) VALUES
(1, 2), (2, 1);

INSERT INTO Follows (follower_id, following_id) VALUES
(1, 2), (2, 3);

INSERT INTO Messages (sender_id, receiver_id, message_text) VALUES
(1, 2, 'Hi Bob!'),
(2, 1, 'Hey Alice!');

INSERT INTO Notifications (user_id, notification_text) VALUES
(1, 'You have a new message'),
(2, 'Alice liked your post');

SELECT p.*, u.username FROM Posts p JOIN Users u ON p.user_id = u.user_id ORDER BY post_date DESC;

SELECT c.comment_text, u.username FROM Comments c JOIN Users u ON c.user_id = u.user_id WHERE c.post_id = 1;
SELECT COUNT(*) AS likes_count FROM Likes WHERE post_id = 1;

SELECT u.username FROM Follows f JOIN Users u ON f.follower_id = u.user_id WHERE f.following_id = 1;

SELECT * FROM Messages WHERE receiver_id = 1 AND is_read = FALSE;

SELECT p.post_id, COUNT(l.like_id) AS like_count FROM Posts p JOIN Likes l ON p.post_id = l.post_id GROUP BY p.post_id ORDER BY like_count DESC;

SELECT * FROM Notifications WHERE user_id = 1 ORDER BY notification_date DESC;

INSERT INTO Posts (user_id, post_text, media_url) VALUES (1, 'New adventure!', 'adventure.jpg');

INSERT INTO Comments (post_id, user_id, comment_text) VALUES (1, 3, 'Love this!');

UPDATE Users SET profile_picture = 'newpic.jpg' WHERE user_id = 1;

DELETE FROM Likes WHERE post_id = 1 AND user_id = 2;

SELECT u.username, COUNT(f.follower_id) AS followers FROM Users u JOIN Follows f ON u.user_id = f.following_id GROUP BY u.user_id ORDER BY followers DESC;

SELECT u.username, COUNT(DISTINCT p.post_id) + COUNT(DISTINCT c.comment_id) + COUNT(DISTINCT l.like_id) AS activity_score
FROM Users u
LEFT JOIN Posts p ON u.user_id = p.user_id
LEFT JOIN Comments c ON u.user_id = c.user_id
LEFT JOIN Likes l ON u.user_id = l.user_id
GROUP BY u.user_id ORDER BY activity_score DESC;

SELECT AVG(comment_count) FROM (
    SELECT COUNT(*) AS comment_count FROM Comments GROUP BY post_id
) AS counts;

DELIMITER //
CREATE TRIGGER notify_new_message AFTER INSERT ON Messages
FOR EACH ROW
BEGIN
    INSERT INTO Notifications (user_id, notification_text) VALUES (NEW.receiver_id, 'You have a new message');
END;//
DELIMITER ;

DELIMITER //
CREATE TRIGGER update_post_count AFTER INSERT ON Posts
FOR EACH ROW
BEGIN
    UPDATE Users SET profile_picture = profile_picture WHERE user_id = NEW.user_id;
END;//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE RecommendFollows(IN userId INT)
BEGIN
    SELECT DISTINCT u.user_id, u.username FROM Users u
    WHERE u.user_id != userId
    AND u.user_id NOT IN (SELECT following_id FROM Follows WHERE follower_id = userId);
END;//
DELIMITER ;
