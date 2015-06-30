CREATE SCHEMA IF NOT EXISTS `univ`;
CREATE  TABLE `univ`.`student` (
  `id` BIGINT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(45) NULL ,
  `course`VARCHAR(45) NULL ,
  `date` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  
  
  PRIMARY KEY (`id`) );
  
INSERT INTO `univ`.`student` (`name`, `course`) VALUES ('Petrov', 'Math');
