CREATE TABLE `EP_ENTEL_TELEVENTAS`.`document_types` (
  `id` BINARY(16) NOT NULL DEFAULT (UUID_TO_BIN(UUID(), 1)),
  `name` VARCHAR(45) NULL,
  PRIMARY KEY (`id`));

ALTER TABLE `EP_ENTEL_TELEVENTAS`.`document_types` 
ADD COLUMN `status` TINYINT NOT NULL DEFAULT '1' AFTER `name`,
ADD COLUMN `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP AFTER `status`,
ADD COLUMN `created_by` BINARY(16) NULL DEFAULT NULL AFTER `created_at`,
ADD COLUMN `updated_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP AFTER `created_by`,
ADD COLUMN `updated_by` BINARY(16) NULL DEFAULT NULL AFTER `updated_at`;

ALTER TABLE `EP_ENTEL_TELEVENTAS`.`document_types` 
CHANGE COLUMN `created_by` `created_by` BINARY(16) NOT NULL ;


ALTER TABLE document_types
ADD CONSTRAINT fk_document_types_created_by
FOREIGN KEY (created_by) REFERENCES users(id);

    
ALTER TABLE document_types
ADD CONSTRAINT fk_document_types_updated_by
FOREIGN KEY (updated_by) REFERENCES users(id);




ALTER TABLE `EP_ENTEL_TELEVENTAS`.`document_types` 
DROP FOREIGN KEY `fk_document_types_created_by`;
ALTER TABLE `EP_ENTEL_TELEVENTAS`.`document_types` 
CHANGE COLUMN `created_by` `created_by` BINARY(16) NULL ;
ALTER TABLE `EP_ENTEL_TELEVENTAS`.`document_types` 
ADD CONSTRAINT `fk_document_types_created_by`
  FOREIGN KEY (`created_by`)
  REFERENCES `EP_ENTEL_TELEVENTAS`.`users` (`id`);

ALTER TABLE `EP_ENTEL_TELEVENTAS`.`document_types` 
DROP FOREIGN KEY `fk_document_types_created_by`;
ALTER TABLE `EP_ENTEL_TELEVENTAS`.`document_types` 
CHANGE COLUMN `created_by` `created_by` BINARY(16) NULL ;
ALTER TABLE `EP_ENTEL_TELEVENTAS`.`document_types` 
ADD CONSTRAINT `fk_document_types_created_by`
  FOREIGN KEY (`created_by`)
  REFERENCES `EP_ENTEL_TELEVENTAS`.`users` (`id`);
