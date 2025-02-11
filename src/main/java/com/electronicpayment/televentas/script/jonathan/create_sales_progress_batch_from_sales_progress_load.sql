USE `Televentas`;
DROP procedure IF EXISTS `create_sales_progress_batch_from_sales_progress_load`;

DELIMITER $$
USE `Televentas`$$
CREATE PROCEDURE `create_sales_progress_batch_from_sales_progress_load` (IN p_upload_date DATE)
BEGIN
	INSERT INTO `Televentas`.`sales_progress`
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
		sales_progress_load spl
        JOIN users u ON u.document = spl.documento;
END$$

DELIMITER ;

