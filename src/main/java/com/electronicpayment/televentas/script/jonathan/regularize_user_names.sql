USE `Televentas`;
DROP procedure IF EXISTS `regularize_user_names`;

DELIMITER $$
USE `Televentas`$$
CREATE PROCEDURE `regularize_user_names` ()
BEGIN
	SET SQL_SAFE_UPDATES = 0;
    
    -- Caso 1: `name` tiene exactamente 3 palabras
    UPDATE `Televentas`.`users`
    SET 
        `paternal_last_name` = SUBSTRING_INDEX(SUBSTRING_INDEX(`name`, ' ', -2), ' ', 1),
        `maternal_last_name` = SUBSTRING_INDEX(`name`, ' ', -1)
    WHERE (LENGTH(`name`) - LENGTH(REPLACE(`name`, ' ', '')) + 1) = 3;
    
    UPDATE `Televentas`.`users`
	SET `name` = SUBSTRING_INDEX(`name`, ' ', 1)
	WHERE (LENGTH(`name`) - LENGTH(REPLACE(`name`, ' ', '')) + 1) = 3;
    
    -- Caso 2: nombres_apellidos tiene exactamente 4 palabras
    UPDATE `Televentas`.`users`
    SET 
        `paternal_last_name` = SUBSTRING_INDEX(SUBSTRING_INDEX(`name`, ' ', -2), ' ', 1),
        `maternal_last_name` = SUBSTRING_INDEX(`name`, ' ', -1)
    WHERE (LENGTH(`name`) - LENGTH(REPLACE(`name`, ' ', '')) + 1) = 4;
    
    UPDATE `Televentas`.`users`
	SET `name` = SUBSTRING_INDEX(`name`, ' ', 2)
	WHERE (LENGTH(`name`) - LENGTH(REPLACE(`name`, ' ', '')) + 1) = 4;

    -- Caso 3: nombres_apellidos tiene más de 4 palabras
    UPDATE `Televentas`.`users`
    SET         
        `paternal_last_name` = SUBSTRING_INDEX(SUBSTRING_INDEX(`name`, ' ', -3), ' ', 2),
        `maternal_last_name` = SUBSTRING_INDEX(`name`, ' ', -1)
    WHERE (LENGTH(`name`) - LENGTH(REPLACE(`name`, ' ', '')) + 1) > 4;
    
    UPDATE `Televentas`.`users`
	SET `name` = SUBSTRING_INDEX(`name`, ' ', LENGTH(`name`) - LENGTH(REPLACE(`name`, ' ', '')) - 2)
	WHERE (LENGTH(`name`) - LENGTH(REPLACE(`name`, ' ', '')) + 1) > 4;
END$$

DELIMITER ;

