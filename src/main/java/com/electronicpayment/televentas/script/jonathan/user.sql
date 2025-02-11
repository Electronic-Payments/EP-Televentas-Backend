CREATE TABLE `Televentas`.`users` (
  `id` BINARY(16) NOT NULL DEFAULT (UUID_TO_BIN(UUID(), 1)),
  `document` VARCHAR(20) NOT NULL,
  `status` TINYINT NOT NULL DEFAULT 1,
  `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` BINARY(16) NULL,
  `updated_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` BINARY(16) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `document_UNIQUE` (`document` ASC) VISIBLE);


ALTER TABLE `Televentas`.`users` 
ADD INDEX `fk_users_created_by_idx` (`created_by` ASC) VISIBLE,
ADD INDEX `fk_users_updated_by_idx` (`updated_by` ASC) VISIBLE;
;
ALTER TABLE `Televentas`.`users` 
ADD CONSTRAINT `fk_users_created_by`
  FOREIGN KEY (`created_by`)
  REFERENCES `Televentas`.`users` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION,
ADD CONSTRAINT `fk_users_updated_by`
  FOREIGN KEY (`updated_by`)
  REFERENCES `Televentas`.`users` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE `Televentas`.`users` 
ADD COLUMN `password` VARCHAR(255) NOT NULL AFTER `document`;

ALTER TABLE `Televentas`.`users` 
ADD COLUMN `document_type_id` BINARY(16) NULL AFTER `id`;

    ALTER TABLE users
ADD CONSTRAINT fk_users_document_type_id
FOREIGN KEY (document_type_id) REFERENCES document_types(id);

ALTER TABLE `Televentas`.`users` 
ADD COLUMN `name` VARCHAR(150) NULL AFTER `password`,
ADD COLUMN `paternal_last_name` VARCHAR(150) NULL AFTER `name`,
ADD COLUMN `maternal_last_name` VARCHAR(150) NULL AFTER `paternal_last_name`;

ALTER TABLE `Televentas`.`users` 
ADD COLUMN `phone` INT NULL AFTER `maternal_last_name`,
ADD COLUMN `email` VARCHAR(60) NULL AFTER `phone`,
ADD COLUMN `department` VARCHAR(60) NULL AFTER `email`;

ALTER TABLE `Televentas`.`users` 
ADD COLUMN `password_changed` TINYINT NULL DEFAULT 0 AFTER `password`;
