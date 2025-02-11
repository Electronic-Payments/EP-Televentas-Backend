CREATE TABLE `Televentas`.`bonus` (
  `id` BINARY(16) NOT NULL DEFAULT (UUID_TO_BIN(UUID(), 1)),
  `status` TINYINT NOT NULL DEFAULT '1',
  `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` BINARY(16) NOT NULL,
  `updated_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` BINARY(16) NULL DEFAULT NULL,
  PRIMARY KEY (`id`));


ALTER TABLE bonus
ADD CONSTRAINT fk_bonus_created_by
FOREIGN KEY (created_by) REFERENCES users(id);

ALTER TABLE bonus
ADD CONSTRAINT fk_bonus_updated_by
FOREIGN KEY (updated_by) REFERENCES users(id);

ALTER TABLE Televentas.bonus ADD user_id INT NOT NULL after id;

ALTER TABLE `Televentas`.`bonus` 
ADD COLUMN `upload_date` DATE NOT NULL AFTER `user_id`;

ALTER TABLE `Televentas`.`bonus` 
ADD COLUMN `detail` VARCHAR(100) NOT NULL AFTER `user_id`,
ADD COLUMN `import` DECIMAL(10,2) NOT NULL AFTER `detail`;

ALTER TABLE `Televentas`.`bonus` 
CHANGE COLUMN `user_id` `user_id` BINARY(16) NOT NULL ;

ALTER TABLE bonus
ADD CONSTRAINT fk_bonus_user_id
FOREIGN KEY (user_id) REFERENCES users(id);

ALTER TABLE `Televentas`.`bonus` 
DROP FOREIGN KEY `fk_bonus_created_by`;
ALTER TABLE `Televentas`.`bonus` 
CHANGE COLUMN `created_by` `created_by` BINARY(16) NULL ;
ALTER TABLE `Televentas`.`bonus` 
ADD CONSTRAINT `fk_bonus_created_by`
  FOREIGN KEY (`created_by`)
  REFERENCES `Televentas`.`users` (`id`);
