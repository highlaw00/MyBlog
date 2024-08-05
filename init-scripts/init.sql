USE blogdb;

DROP TABLE if EXISTS member;
DROP TABLE if EXISTS article;
DROP TABLE if EXISTS comment;

CREATE TABLE member (
                        member_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        username VARCHAR(50) NOT NULL,
                        password VARCHAR(255) NOT NULL,
                        intro VARCHAR(255)
);

CREATE TABLE article (
                         article_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         member_id BIGINT NOT NULL,
                         title VARCHAR(255) NOT NULL,
                         contents TEXT NOT NULL,
                         CONSTRAINT article_member_fk FOREIGN KEY (member_id) REFERENCES member(member_id) ON DELETE CASCADE
);

CREATE TABLE comment (
                         comment_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         member_id BIGINT NOT NULL,
                         article_id BIGINT NOT NULL,
                         contents VARCHAR(511) NOT NULL,
                         CONSTRAINT comment_member_fk FOREIGN KEY(member_id) REFERENCES member(member_id) ON DELETE CASCADE,
                         CONSTRAINT comment_article_fk FOREIGN KEY (article_id) REFERENCES article(article_id) ON DELETE CASCADE
);

INSERT INTO member(username, password, intro) VALUES('test1', '12345', 'This is test1');
INSERT INTO member(username, password, intro) VALUES('test2', '12345', 'This is test2');
INSERT INTO member(username, password, intro) VALUES('test3', '12345', 'This is test3');

INSERT INTO article(member_id, title, contents) VALUES(1, 'Article1: this is first article.', 'This is the contents of the first article.');
INSERT INTO article(member_id, title, contents) VALUES(2, 'Article2: this is first article.', 'This is the contents of the first article.');
INSERT INTO article(member_id, title, contents) VALUES(3, 'Article3: this is first article.', 'This is the contents of the first article.');