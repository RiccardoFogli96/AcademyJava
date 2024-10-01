CREATE DATABASE mydatabase;
CREATE TABLE `author` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(92) NOT NULL,
  `surname` varchar(92) NOT NULL,
  `biography` varchar(92) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `book` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(92) NOT NULL,
  `description` varchar(92) DEFAULT NULL,
  PRIMARY KEY (`id`),
  `book_type` varchar(92) NOT NULL
);