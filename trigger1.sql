
DELIMITER //

DROP TRIGGER IF EXISTS insertarReporte;
CREATE TRIGGER insertarReporte AFTER INSERT  ON movimientos_deudas
FOR EACH ROW

BEGIN

	

	IF NEW.id_tipo_movimiento=2 THEN
    INSERT INTO reporte SELECT DISTINCT NEW.id_movimiento_deuda,CL.id_cartera, 
CL.id_cliente, 
nombre, 
telefono, 
direccion, 
barrio, 
D.fecha_inicio, 
(SELECT MAX(fecha_pago) from pagos WHERE id_deuda=D.id_deuda) as fechaPago, 
(select proximo_pago from movimientos_deudas WHERE id_movimiento_deuda=(SELECT max(id_movimiento_deuda) FROM movimientos_deudas where id_deuda=D.id_deuda) ) as proximoPago, 
D.monto_prestado, 
D.monto_interes, 
D.cantidad_cuotas,(SELECT count(id_pago) from pagos where id_deuda=D.id_deuda) as cuotasPagadas, 
D.cantidad_cuotas-(SELECT count(id_pago) from pagos where id_deuda=D.id_deuda) as cuotasPendientes, 
(SELECT count(id_mora) from moras where id_deuda=D.id_deuda and id_estado=1) as cuotasMoras, 
(SELECT sum(monto_mora) from moras P INNER JOIN deudas DD ON DD.id_deuda=P.id_deuda WHERE DD.id_deuda=D.id_deuda and id_estado=1) as cuotaAdicional, 
(SELECT sum(monto_pagado) from pagos WHERE id_deuda=D.id_deuda and fecha_pago=fechaPago) as valorPagado,  
(SELECT monto_actual FROM movimientos_deudas WHERE id_movimiento_deuda=(SELECT max(id_movimiento_deuda) FROM movimientos_deudas where id_deuda=D.id_deuda) ) as saldo from clientes CL INNER JOIN deudas D ON CL.id_cliente=D.id_cliente INNER JOIN modalidades M  ON M.id_modalidad=D.id_modalidad INNER JOIN estados_deudas ED ON ED.id_estado_deuda=D.id_estado_deuda INNER JOIN movimientos_deudas MM ON D.id_deuda=MM.id_deuda 
WHERE (SELECT id_tipo_movimiento FROM movimientos_deudas WHERE id_movimiento_deuda=(SELECT max(id_movimiento_deuda) FROM movimientos_deudas where id_deuda=D.id_deuda))=2 AND D.id_deuda=NEW.id_deuda;
    
    END IF;

END //

DELIMITER ;