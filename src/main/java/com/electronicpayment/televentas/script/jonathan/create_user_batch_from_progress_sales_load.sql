USE `Televentas`;
DROP procedure IF EXISTS `create_user_batch_from_progress_sales_load`;

DELIMITER $$
USE `Televentas`$$
CREATE PROCEDURE `create_user_batch_from_progress_sales_load` ()
BEGIN
	INSERT IGNORE INTO `Televentas`.`users`
    (
        `document_type_id`,
        `document`,
        `password`,
        `name`,
        phone,
        email
    )
    SELECT
        CASE 
            WHEN tipo_documento = "DNI" THEN UUID_TO_BIN("11efe589-0d66-6190-9de9-42010a400011") 
            WHEN tipo_documento = "CE" THEN UUID_TO_BIN("11efe589-3309-87c5-9de9-42010a400011") 
        END AS tipo_documento,
        CASE
			WHEN tipo_documento = "DNI" AND LENGTH(documento) < 8 THEN LPAD(documento, 8, '0')
			WHEN tipo_documento = "CE" AND LENGTH(documento) < 12 THEN LPAD(documento, 12, '0')
            ELSE documento
		END AS documento,
        "$2a$10$rvjP9yCGBgqpI84pVYc3OeooC0fTJ0zswD5jw0BRD.PvKIlPQO2wm" AS password,
        nombres_apellidos,
        CASE 
            WHEN TRIM(telefono) = '' THEN NULL 
            ELSE telefono 
        END AS telefono,
        CASE 
            WHEN TRIM(correo) = '' THEN NULL 
            ELSE correo 
        END AS correo
    FROM
        sales_progress_load 
    WHERE documento NOT IN (SELECT document FROM users);
END$$

DELIMITER ;

