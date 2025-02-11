USE `Televentas`;
DROP procedure IF EXISTS `create_bonus_batch_from_bonus_load`;

DELIMITER $$
USE `Televentas`$$
CREATE PROCEDURE `create_bonus_batch_from_bonus_load` (IN p_upload_date DATE)
BEGIN
	INSERT INTO `Televentas`.`bonus`
	(
		`user_id`,
		`detail`,
		`import`,
		`upload_date`
    )
    SELECT
		u.id,
        detalle,
        importe,
        p_upload_date        
	FROM
		bonus_load bl
        JOIN users u ON u.document = bl.documento;
END$$

DELIMITER ;

