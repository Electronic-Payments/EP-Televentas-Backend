USE `EP_ENTEL_TELEVENTAS`;
DROP procedure IF EXISTS `create_sales_progress_batch_from_sales_progress_load`;

USE `EP_ENTEL_TELEVENTAS`;
DROP procedure IF EXISTS `EP_ENTEL_TELEVENTAS`.`create_sales_progress_batch_from_sales_progress_load`;
;

DELIMITER $$
USE `EP_ENTEL_TELEVENTAS`$$
CREATE DEFINER=`rootDev`@`%` PROCEDURE `create_sales_progress_batch_from_sales_progress_load`(IN p_upload_date DATE)
BEGIN
	INSERT INTO `EP_ENTEL_TELEVENTAS`.`sales_progress`
	(
		`user_id`,
		`valid_quantity`,
		`active_quantity`,
		`active_percentage`,
		`delivery_quantity`,
		`delivery_percentage`,
		`portability_quantity`,
		`multi_quantity`,
        upload_date
    )
    SELECT
		u.id,
        q_validas,
        q_activas,
        porcen_activas,
        q_entregas,
        porcen_entregas,
        q_porta,
        q_multi,
        p_upload_date
	FROM
		EP_ENTEL_TELEVENTAS_LOAD.sales_progress_load spl
        JOIN users u ON u.document = spl.documento;
END$$

DELIMITER ;
;

