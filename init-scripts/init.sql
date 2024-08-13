USE blogdb;

DROP TABLE if EXISTS member;
DROP TABLE if EXISTS article;
DROP TABLE if EXISTS comment;

CREATE TABLE member (
                        member_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        username VARCHAR(50) NOT NULL,
                        password VARCHAR(255) NOT NULL,
                        intro VARCHAR(255),
                        role VARCHAR(255) NOT NULL,
                        created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                        updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE article (
                         article_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         member_id BIGINT NOT NULL,
                         title VARCHAR(255) NOT NULL,
                         contents TEXT NOT NULL,
                         created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                         updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                         CONSTRAINT article_member_fk FOREIGN KEY (member_id) REFERENCES member(member_id) ON DELETE CASCADE
);

CREATE TABLE comment (
                         comment_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         member_id BIGINT NOT NULL,
                         article_id BIGINT NOT NULL,
                         contents VARCHAR(511) NOT NULL,
                         created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                         updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                         CONSTRAINT comment_member_fk FOREIGN KEY(member_id) REFERENCES member(member_id) ON DELETE CASCADE,
                         CONSTRAINT comment_article_fk FOREIGN KEY (article_id) REFERENCES article(article_id) ON DELETE CASCADE
);

-- INSERT INTO member(username, password, intro) VALUES('test1', '12345', 'This is test1');
-- INSERT INTO member(username, password, intro) VALUES('test2', '12345', 'This is test2');
-- INSERT INTO member(username, password, intro) VALUES('test3', '12345', 'This is test3');
--
-- INSERT INTO article(member_id, title, contents) VALUES(1, 'Article1: this is first article.', 'This is the contents of the first article.');
-- INSERT INTO article(member_id, title, contents) VALUES(2, 'Article2: this is first article.', 'This is the contents of the first article.');
-- INSERT INTO article(member_id, title, contents) VALUES(3, 'Article3: this is first article.', 'This is the contents of the first article.');
--
-- INSERT INTO comment(member_id, article_id, contents) VALUES(1, 1, 'Comment about article1 - by member 1');
-- INSERT INTO comment(member_id, article_id, contents) VALUES(2, 1, 'Comment about article1 - by member 2');
-- INSERT INTO comment(member_id, article_id, contents) VALUES(3, 1, 'Comment about article1 - by member 3');
-- INSERT INTO comment(member_id, article_id, contents) VALUES(1, 2, 'Comment about article2 - by member 1');
-- INSERT INTO comment(member_id, article_id, contents) VALUES(2, 2, 'Comment about article2 - by member 2');
-- INSERT INTO comment(member_id, article_id, contents) VALUES(3, 2, 'Comment about article2 - by member 3');

-- 회원 데이터 삽입
-- 테스트 회원 비밀번호: (password1, password2, ..., password5) user명 뒤의 숫자를 붙이면 됩니다
-- 테스트 어드민 비밀번호: password
INSERT INTO member (username, password, intro, role) VALUES
                                                   ('user1', '$2a$10$F.giPc.tGJ91y02jpz7//.y4mAQhQqgg1JiTuMaF/fc2k8mTM1MqC', 'intro for user1', 'ROLE_USER'),
                                                   ('user2', '$2a$10$hzM6lHtGhLnjszqOskRfFuCQZOjKY2iVILzVnHOi0ET09lAo48Vo6', 'intro for user2', 'ROLE_USER'),
                                                   ('user3', '$2a$10$3EK6idjy7pFyLgLzJR1aw.c/iGthMAyU9eY5dYmLBxIYf2UYsFrKi', 'intro for user3', 'ROLE_USER'),
                                                   ('user4', '$2a$10$UDb3iE6WO6vTXsXg9y50E.SVrzwdsFKtfWaWJnX9KO1XnMSb9Ytq.', 'intro for user4', 'ROLE_USER'),
                                                   ('user5', '$2a$10$I5PFMZmZW4LsWgBlX1tnGeE5KohnaoNrtx0fBwAat5Q12gGZfDzyy', 'intro for user5', 'ROLE_USER'),
                                                   ('admin', '$2a$10$xoqLR5fXki8ddsCnrYqhH.FtnqTHLOQ3yjECrCPme9IUsmxKgaJiC', 'intro for admin', 'ROLE_ADMIN');

-- 게시글 데이터 삽입
DELIMITER $$

CREATE PROCEDURE insert_articles()
BEGIN
    DECLARE i INT DEFAULT 1;
    DECLARE j INT DEFAULT 1;
    DECLARE memberId INT;

    -- 5명의 회원에 대해 각 회원당 50개의 게시글을 생성
    WHILE i <= 5 DO
        SET memberId = i;
        WHILE j <= 50 DO
            INSERT INTO article (member_id, title, contents) VALUES
            (memberId, CONCAT('Title ', j, ' for user', i), CONCAT('Contents for article ', j, ' by user', i));
            SET j = j + 1;
END WHILE;
        SET i = i + 1;
        SET j = 1;
END WHILE;

    -- 특정 게시글 (1번 게시글)에 100개의 댓글 추가
    SET j = 1;
    WHILE j <= 100 DO
        INSERT INTO comment (member_id, article_id, contents) VALUES
        (FLOOR(1 + (RAND() * 5)), 1, CONCAT('Comment ', j, ' for article 1'));
        SET j = j + 1;
END WHILE;
END$$

DELIMITER ;

CALL insert_articles();

-- 댓글 데이터 삽입 (각 게시글에 10개 이하의 댓글 추가)
DELIMITER $$

CREATE PROCEDURE insert_comments()
BEGIN
    DECLARE i INT DEFAULT 1;
    DECLARE j INT DEFAULT 1;
    DECLARE k INT DEFAULT 1;
    DECLARE totalArticles INT;

    -- 전체 게시글 수를 조회
SELECT COUNT(*) INTO totalArticles FROM article;

-- 각 게시글에 대해 10개 이하의 댓글을 추가
WHILE i <= totalArticles DO
        SET j = FLOOR(1 + (RAND() * 10));
        SET k = 1;
        WHILE k <= j DO
            INSERT INTO comment (member_id, article_id, contents) VALUES
            (FLOOR(1 + (RAND() * 5)), i, CONCAT('Comment ', k, ' for article ', i));
            SET k = k + 1;
END WHILE;
        SET i = i + 1;
END WHILE;
END$$

DELIMITER ;

CALL insert_comments();

