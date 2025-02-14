USE `EP_ENTEL_TELEVENTAS`;
DROP procedure IF EXISTS `create_bonus_batch_from_bonus_load`;

USE `EP_ENTEL_TELEVENTAS`;
DROP procedure IF EXISTS `EP_ENTEL_TELEVENTAS`.`create_bonus_batch_from_bonus_load`;
;

DELIMITER $$
USE `EP_ENTEL_TELEVENTAS`$$
CREATE DEFINER=`rootDev`@`%` PROCEDURE `create_bonus_batch_from_bonus_load`(IN p_upload_date DATE)
BEGIN
	INSERT INTO `EP_ENTEL_TELEVENTAS`.`bonus`
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
		EP_ENTEL_TELEVENTAS_LOAD.bonus_load bl
        JOIN users u ON u.document = bl.documento;
END$$

DELIMITER ;
;

