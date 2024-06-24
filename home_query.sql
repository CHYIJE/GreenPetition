-- 회원 Table Create SQL

create database green;
USE green ;
-- drop table user;
-- drop table petition;
-- drop table comment;
-- drop table vote;


-- -----------------------------------------------------
-- Table `green`.`user`
-- -----------------------------------------------------
CREATE TABLE user (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` VARCHAR(50) NOT NULL COMMENT '유저 이름',
  `acc_id` VARCHAR(50) NOT NULL COMMENT '유저 id',
  `acc_pw` VARCHAR(50) NOT NULL COMMENT '유저 password',
  `isAdmin` ENUM('Y', 'N') NOT NULL DEFAULT 'N' COMMENT '관리자 계정 여부',
  PRIMARY KEY (`id`),
  unique (`acc_id`));



-- -----------------------------------------------------
-- Table `green`.`petition`
-- -----------------------------------------------------
CREATE TABLE petition (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` INT NOT NULL COMMENT '유저 id',
  `category` ENUM('facility', 'teacher') NOT NULL COMMENT '카테고리',
  `title` VARCHAR(50) NOT NULL COMMENT '글 제목',
  `content` VARCHAR(1000) NULL DEFAULT NULL,
  `date` datetime NOT NULL COMMENT '작성일',
  `agree` INT NOT NULL DEFAULT '0' COMMENT '찬',
  `disagree` INT NOT NULL DEFAULT '0' COMMENT '반',
  PRIMARY KEY (`id`),
  CONSTRAINT `petition_ibfk_1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`));




-- -----------------------------------------------------
-- Table `green`.`comment`
-- -----------------------------------------------------
CREATE TABLE comment(
  `id` INT NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` INT NOT NULL COMMENT '유저 id',
  `petition_id` INT NOT NULL COMMENT '글 id',
  `comment` VARCHAR(255) NOT NULL COMMENT '글 내용',
  `date` datetime NOT NULL COMMENT '댓글입력날짜',
  PRIMARY KEY (`id`),
  CONSTRAINT `comment_ibfk_1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`),
  CONSTRAINT `comment_ibfk_2`
    FOREIGN KEY (`petition_id`)
    REFERENCES `petition` (`id`));
  


-- -----------------------------------------------------
-- Table `green`.`vote`
-- -----------------------------------------------------
CREATE TABLE vote (
  `id` INT NOT NULL AUTO_INCREMENT,
  `petition_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `vote_ibfk_1`
    FOREIGN KEY (`petition_id`)
    REFERENCES `petition` (`id`),
  CONSTRAINT `vote_ibfk_2`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`));