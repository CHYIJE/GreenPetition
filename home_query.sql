create database green;
use green;

-- 회원 Table Create SQL
-- 테이블 생성 SQL - 회원
CREATE TABLE 회원
(
    `ID`    INT            NOT NULL    AUTO_INCREMENT, 
    `회원이름`  VARCHAR(50)    NOT NULL, 
    `아이디`   VARCHAR(50)    NOT NULL, 
    `비번`    VARCHAR(50)    NOT NULL, 
     PRIMARY KEY (ID)
);


-- 청원글카테고리 Table Create SQL
-- 테이블 생성 SQL - 청원글카테고리
CREATE TABLE 청원글카테고리
(
    `카테고리ID`  INT            NOT NULL    AUTO_INCREMENT, 
    `카테고리이름`  VARCHAR(50)    NOT NULL, 
     PRIMARY KEY (카테고리ID)
);


-- 전체 청원글 Table Create SQL
-- 테이블 생성 SQL - 전체 청원글
CREATE TABLE 전체 청원글
(
    `ID`      INT            NOT NULL    AUTO_INCREMENT, 
    `작성자`     VARCHAR(50)    NOT NULL, 
    `제목`      VARCHAR(50)    NOT NULL, 
    `내용`      VARCHAR(50)    NOT NULL, 
    `추천`      INT            NULL, 
    `카테고리ID`  INT            NOT NULL, 
     PRIMARY KEY (ID)
);

-- Foreign Key 설정 SQL - 전체 청원글(작성자) -> 회원(회원이름)
ALTER TABLE 전체 청원글
    ADD CONSTRAINT FK_전체 청원글_작성자_회원_회원이름 FOREIGN KEY (작성자)
        REFERENCES 회원 (회원이름) ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Foreign Key 삭제 SQL - 전체 청원글(작성자)
-- ALTER TABLE 전체 청원글
-- DROP FOREIGN KEY FK_전체 청원글_작성자_회원_회원이름;

-- Foreign Key 설정 SQL - 전체 청원글(카테고리ID) -> 청원글카테고리(카테고리ID)
ALTER TABLE 전체 청원글
    ADD CONSTRAINT FK_전체 청원글_카테고리ID_청원글카테고리_카테고리ID FOREIGN KEY (카테고리ID)
        REFERENCES 청원글카테고리 (카테고리ID) ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Foreign Key 삭제 SQL - 전체 청원글(카테고리ID)
-- ALTER TABLE 전체 청원글
-- DROP FOREIGN KEY FK_전체 청원글_카테고리ID_청원글카테고리_카테고리ID;


-- 청원글자세히 Table Create SQL
-- 테이블 생성 SQL - 청원글자세히
CREATE TABLE 청원글자세히
(
    `작성자`    VARCHAR(50)     NOT NULL, 
    `제목`     VARCHAR(50)     NOT NULL, 
    `내용`     VARCHAR(50)     NOT NULL, 
    `댓글ID`   INT             NULL, 
    `댓글작성자`  VARCHAR(50)     NULL, 
    `댓글`     VARCHAR(200)    NULL, 
     PRIMARY KEY (작성자)
);

-- Foreign Key 설정 SQL - 청원글자세히(작성자) -> 전체 청원글(작성자)
ALTER TABLE 청원글자세히
    ADD CONSTRAINT FK_청원글자세히_작성자_전체 청원글_작성자 FOREIGN KEY (작성자)
        REFERENCES 전체 청원글 (작성자) ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Foreign Key 삭제 SQL - 청원글자세히(작성자)
-- ALTER TABLE 청원글자세히
-- DROP FOREIGN KEY FK_청원글자세히_작성자_전체 청원글_작성자;

-- Foreign Key 설정 SQL - 청원글자세히(댓글작성자) -> 회원(회원이름)
ALTER TABLE 청원글자세히
    ADD CONSTRAINT FK_청원글자세히_댓글작성자_회원_회원이름 FOREIGN KEY (댓글작성자)
        REFERENCES 회원 (회원이름) ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Foreign Key 삭제 SQL - 청원글자세히(댓글작성자)
-- ALTER TABLE 청원글자세히
-- DROP FOREIGN KEY FK_청원글자세히_댓글작성자_회원_회원이름;

select * from 회원;
-- select * from 