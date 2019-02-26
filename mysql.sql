CREATE SCHEMA `new_schema` DEFAULT CHARACTER SET utf8 ;

CREATE TABLE `boku_no_hero_academia`.`classrooms` (
  `id` INT NOT NULL,
  `name` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`));
  
CREATE TABLE `boku_no_hero_academia`.`quirks` (
  `id` INT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`));

CREATE TABLE `boku_no_hero_academia`.`teachers` (
  `id` INT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `birthday` DATE NOT NULL,
  `classroom_id` INT NOT NULL,
  `quirk_id` INT NOT NULL,
  PRIMARY KEY (`id`));
  
ALTER TABLE `boku_no_hero_academia`.`teachers` 
ADD INDEX `fk_teachers_1_idx` (`classroom_id` ASC);
ALTER TABLE `boku_no_hero_academia`.`teachers` 
ADD CONSTRAINT `fk_teachers_1`
  FOREIGN KEY (`classroom_id`)
  REFERENCES `boku_no_hero_academia`.`classrooms` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;
  
  
ALTER TABLE `boku_no_hero_academia`.`teachers` 
ADD INDEX `fk_teachers_2_idx` (`quirk_id` ASC);
ALTER TABLE `boku_no_hero_academia`.`teachers` 
ADD CONSTRAINT `fk_teachers_2`
  FOREIGN KEY (`quirk_id`)
  REFERENCES `boku_no_hero_academia`.`quirks` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;


CREATE TABLE `boku_no_hero_academia`.`heros` (
  `id` INT NOT NULL,
  `name` VARCHAR(20) NOT NULL,
  `birthday` DATETIME NOT NULL,
  `quirk_id` INT(11) NOT NULL,
  `classroom_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`));


ALTER TABLE `boku_no_hero_academia`.`heros` 
ADD INDEX `fk_heros_1_idx` (`classroom_id` ASC);
ALTER TABLE `boku_no_hero_academia`.`heros` 
ADD CONSTRAINT `fk_heros_1`
  FOREIGN KEY (`classroom_id`)
  REFERENCES `boku_no_hero_academia`.`classrooms` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;
  

ALTER TABLE `boku_no_hero_academia`.`heros` 
ADD INDEX `fk_heros_2_idx` (`quirk_id` ASC);
ALTER TABLE `boku_no_hero_academia`.`heros` 
ADD CONSTRAINT `fk_heros_2`
  FOREIGN KEY (`quirk_id`)
  REFERENCES `boku_no_hero_academia`.`quirks` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;
  
