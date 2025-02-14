CREATE TABLE `EP_ENTEL_TELEVENTAS`.`sales_progress` (
  `id` BINARY(16) NOT NULL DEFAULT (UUID_TO_BIN(UUID(), 1)),
  `user_id` BINARY(16) NOT NULL,
  `valid_quantity` INT NULL,
  `active_quantity` INT NULL,
  `active_percentage` VARCHAR(10) NULL,
  `delivery_quantity` INT NULL,
  `delivery_percentage` VARCHAR(10) NULL,
  `portability_quantity` INT NULL,
  `multi_quantity` INT NULL,
  PRIMARY KEY (`id`));

ALTER TABLE `EP_ENTEL_TELEVENTAS`.`sales_progress` 
ADD COLUMN `status` TINYINT NOT NULL DEFAULT '1' AFTER `multi_quantity`,
ADD COLUMN `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP AFTER `status`,
ADD COLUMN `created_by` BINARY(16) NULL DEFAULT NULL AFTER `created_at`,
ADD COLUMN `updated_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP AFTER `created_by`,
ADD COLUMN `updated_by` BINARY(16) NULL DEFAULT NULL AFTER `updated_at`;

ALTER TABLE sales_progress
ADD CONSTRAINT fk_sales_user_id
FOREIGN KEY (user_id) REFERENCES users(id);

ALTER TABLE sales_progress
ADD CONSTRAINT fk_sales_created_by
FOREIGN KEY (created_by) REFERENCES users(id);

ALTER TABLE sales_progress
ADD CONSTRAINT fk_sales_updated_by
FOREIGN KEY (updated_by) REFERENCES users(id);

ALTER TABLE `EP_ENTEL_TELEVENTAS`.`sales_progress` 
ADD COLUMN `upload_date` DATE NOT NULL AFTER `multi_quantity`;
