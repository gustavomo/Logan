-- phpMyAdmin SQL Dump
-- version 4.7.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 24-09-2017 a las 02:27:36
-- Versión del servidor: 10.1.25-MariaDB
-- Versión de PHP: 7.1.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `bd_logan`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `carteras`
--

CREATE TABLE `carteras` (
  `id_cartera` int(4) NOT NULL,
  `monto_maximo` int(16) NOT NULL,
  `capital_inicial` int(16) NOT NULL,
  `fecha_inicio` date NOT NULL,
  `id_interes_deuda` varchar(2) NOT NULL,
  `id_interes_mora` varchar(2) NOT NULL,
  `id_cobrador` varchar(32) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `carteras`
--

INSERT INTO `carteras` (`id_cartera`, `monto_maximo`, `capital_inicial`, `fecha_inicio`, `id_interes_deuda`, `id_interes_mora`, `id_cobrador`) VALUES
(3, 500000, 15000000, '2017-09-23', '1', '2', '1144199371');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `clientes`
--

CREATE TABLE `clientes` (
  `id_cliente` varchar(32) NOT NULL,
  `nombre` varchar(64) NOT NULL,
  `telefono` varchar(16) NOT NULL,
  `direccion` varchar(32) NOT NULL,
  `barrio` varchar(64) NOT NULL,
  `identificacion_ref` varchar(32) NOT NULL,
  `nombre_ref` varchar(128) NOT NULL,
  `telefono_ref` varchar(16) NOT NULL,
  `direccion_ref` varchar(32) NOT NULL,
  `barrio_ref` varchar(64) NOT NULL,
  `id_estado` varchar(2) NOT NULL,
  `id_cartera` int(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `clientes`
--

INSERT INTO `clientes` (`id_cliente`, `nombre`, `telefono`, `direccion`, `barrio`, `identificacion_ref`, `nombre_ref`, `telefono_ref`, `direccion_ref`, `barrio_ref`, `id_estado`, `id_cartera`) VALUES
('1144097401', 'Carlos Andres Bueno Espitia', '335654', 'cra 85 #45-85', 'Limonar', '1144097402', 'Daniel', '1564564', 'cra 84 #48-87', 'Caney', '1', 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cobradores`
--

CREATE TABLE `cobradores` (
  `id_cobrador` varchar(32) NOT NULL,
  `nombre` varchar(64) NOT NULL,
  `telefono` varchar(16) NOT NULL,
  `direccion` varchar(32) NOT NULL,
  `barrio` varchar(64) NOT NULL,
  `id_estado` varchar(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `cobradores`
--

INSERT INTO `cobradores` (`id_cobrador`, `nombre`, `telefono`, `direccion`, `barrio`, `id_estado`) VALUES
('1144199371', 'Gustavo Adrian Moreno Becoche', '1565555', 'cra 84 # 456-5', 'Mariano Ramos', '1');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `deudas`
--

CREATE TABLE `deudas` (
  `id_deuda` int(4) NOT NULL,
  `monto_prestado` int(16) NOT NULL,
  `monto_interes` int(16) NOT NULL,
  `cantidad_cuotas` int(4) NOT NULL,
  `fecha_solicitud` date NOT NULL,
  `fecha_inicio` date NOT NULL,
  `observacion` varchar(128) DEFAULT NULL,
  `id_modalidad` varchar(2) NOT NULL,
  `id_estado_deuda` varchar(2) NOT NULL,
  `id_cliente` varchar(32) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `deudas`
--

INSERT INTO `deudas` (`id_deuda`, `monto_prestado`, `monto_interes`, `cantidad_cuotas`, `fecha_solicitud`, `fecha_inicio`, `observacion`, `id_modalidad`, `id_estado_deuda`, `id_cliente`) VALUES
(28, 400000, 60000, 2, '2017-09-23', '2017-09-23', '', '3', '1', '1144097401');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `estados`
--

CREATE TABLE `estados` (
  `id_estado` varchar(2) NOT NULL,
  `descripcion` varchar(16) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `estados`
--

INSERT INTO `estados` (`id_estado`, `descripcion`) VALUES
('1', 'Activo'),
('2', 'Desactivado');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `estados_deudas`
--

CREATE TABLE `estados_deudas` (
  `id_estado_deuda` varchar(2) NOT NULL,
  `descripcion` varchar(16) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `estados_deudas`
--

INSERT INTO `estados_deudas` (`id_estado_deuda`, `descripcion`) VALUES
('1', 'En deuda'),
('2', 'Cancelada');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `gastos_operacionales`
--

CREATE TABLE `gastos_operacionales` (
  `id_gasto_operacional` int(8) NOT NULL,
  `monto_gasto` int(16) NOT NULL,
  `fecha_gasto` date NOT NULL,
  `id_tipo_gasto` int(2) NOT NULL,
  `id_cartera` int(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `gastos_operacionales`
--

INSERT INTO `gastos_operacionales` (`id_gasto_operacional`, `monto_gasto`, `fecha_gasto`, `id_tipo_gasto`, `id_cartera`) VALUES
(8, 50000, '2017-09-23', 1, 3),
(9, 25000, '2017-09-23', 1, 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `intereses`
--

CREATE TABLE `intereses` (
  `id_interes` varchar(2) NOT NULL,
  `taza_interes` int(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `intereses`
--

INSERT INTO `intereses` (`id_interes`, `taza_interes`) VALUES
('1', 15),
('2', 10);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `modalidades`
--

CREATE TABLE `modalidades` (
  `id_modalidad` varchar(2) NOT NULL,
  `descripcion` varchar(16) NOT NULL,
  `cantidad_cuotas` int(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `modalidades`
--

INSERT INTO `modalidades` (`id_modalidad`, `descripcion`, `cantidad_cuotas`) VALUES
('1', 'Diario', 30),
('2', 'Semanal', 4),
('3', 'Quincenal', 2),
('4', 'Mensual', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `moras`
--

CREATE TABLE `moras` (
  `id_mora` int(8) NOT NULL,
  `monto_mora` int(16) NOT NULL,
  `cuota_mora` int(4) NOT NULL,
  `fecha_mora` date NOT NULL,
  `id_estado` varchar(2) NOT NULL,
  `id_deuda` int(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `movimientos_deudas`
--

CREATE TABLE `movimientos_deudas` (
  `id_movimiento_deuda` int(8) NOT NULL,
  `cuota_vieja` int(2) NOT NULL,
  `cuota_actual` int(2) NOT NULL,
  `monto_vieja` int(16) NOT NULL,
  `monto_actual` int(16) NOT NULL,
  `proximo_pago` date DEFAULT NULL,
  `id_tipo_movimiento` varchar(2) NOT NULL,
  `id_deuda` int(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `movimientos_deudas`
--

INSERT INTO `movimientos_deudas` (`id_movimiento_deuda`, `cuota_vieja`, `cuota_actual`, `monto_vieja`, `monto_actual`, `proximo_pago`, `id_tipo_movimiento`, `id_deuda`) VALUES
(58, 0, 2, 0, 460000, '2017-10-01', '1', 28),
(59, 2, 1, 460000, 230000, '2017-10-09', '2', 28);

--
-- Disparadores `movimientos_deudas`
--
DELIMITER $$
CREATE TRIGGER `actualizarMovimiento` AFTER UPDATE ON `movimientos_deudas` FOR EACH ROW BEGIN 

		IF  (NEW.monto_actual!=OLD.monto_actual) THEN

			DELETE FROM reporte WHERE idMovimiento= OLD.id_movimiento_deuda;

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

	END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `insertarReporte` AFTER INSERT ON `movimientos_deudas` FOR EACH ROW BEGIN

	

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

END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pagos`
--

CREATE TABLE `pagos` (
  `id_pago` int(8) NOT NULL,
  `monto_pagado` int(16) NOT NULL,
  `fecha_pago` date NOT NULL,
  `id_deuda` int(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `pagos`
--

INSERT INTO `pagos` (`id_pago`, `monto_pagado`, `fecha_pago`, `id_deuda`) VALUES
(16, 230000, '2017-09-23', 28);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `prueba`
--

CREATE TABLE `prueba` (
  `asd` varchar(60) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `reporte`
--

CREATE TABLE `reporte` (
  `idMovimiento` varchar(60) NOT NULL,
  `id_cartera` varchar(60) NOT NULL,
  `id_cliente` varchar(60) NOT NULL,
  `nombre` varchar(320) NOT NULL,
  `telefono` varchar(60) NOT NULL,
  `direccion` varchar(60) NOT NULL,
  `barrio` varchar(60) NOT NULL,
  `fecha_inicio` date NOT NULL,
  `fechaPago` date NOT NULL,
  `proximoPago` date NOT NULL,
  `monto_prestado` varchar(60) NOT NULL,
  `monto_interes` varchar(60) NOT NULL,
  `cantidad_cuotas` varchar(60) NOT NULL,
  `cuotasPagadas` varchar(60) NOT NULL,
  `cuotasPendientes` varchar(60) NOT NULL,
  `cuotaMora` varchar(60) NOT NULL,
  `cuotaAdicional` varchar(60) NOT NULL,
  `valorPagado` varchar(60) NOT NULL,
  `saldo` varchar(60) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `reporte`
--

INSERT INTO `reporte` (`idMovimiento`, `id_cartera`, `id_cliente`, `nombre`, `telefono`, `direccion`, `barrio`, `fecha_inicio`, `fechaPago`, `proximoPago`, `monto_prestado`, `monto_interes`, `cantidad_cuotas`, `cuotasPagadas`, `cuotasPendientes`, `cuotaMora`, `cuotaAdicional`, `valorPagado`, `saldo`) VALUES
('59', '3', '1144097401', 'Carlos Andres Bueno Espitia', '335654', 'cra 85 #45-85', 'Limonar', '2017-09-23', '2017-09-23', '2017-10-09', '400000', '60000', '2', '1', '1', '0', '', '230000', '230000');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipos_gastos`
--

CREATE TABLE `tipos_gastos` (
  `id_tipo_gasto` int(2) NOT NULL,
  `descripcion` varchar(128) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `tipos_gastos`
--

INSERT INTO `tipos_gastos` (`id_tipo_gasto`, `descripcion`) VALUES
(1, 'Agua'),
(2, 'Energia');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipos_movimientos`
--

CREATE TABLE `tipos_movimientos` (
  `id_tipo_movimiento` varchar(2) NOT NULL,
  `descripcion` varchar(32) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `tipos_movimientos`
--

INSERT INTO `tipos_movimientos` (`id_tipo_movimiento`, `descripcion`) VALUES
('1', 'Inicio'),
('2', 'Pago'),
('3', 'Mora'),
('4', 'Renovación Cupo'),
('5', 'Activación Mora'),
('6', 'Des-activación Mora');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `carteras`
--
ALTER TABLE `carteras`
  ADD PRIMARY KEY (`id_cartera`),
  ADD KEY `id_interes_deuda` (`id_interes_deuda`),
  ADD KEY `id_interes_mora` (`id_interes_mora`),
  ADD KEY `id_cobrador` (`id_cobrador`);

--
-- Indices de la tabla `clientes`
--
ALTER TABLE `clientes`
  ADD PRIMARY KEY (`id_cliente`),
  ADD KEY `id_estado` (`id_estado`),
  ADD KEY `id_cartera` (`id_cartera`);

--
-- Indices de la tabla `cobradores`
--
ALTER TABLE `cobradores`
  ADD PRIMARY KEY (`id_cobrador`),
  ADD KEY `id_estado` (`id_estado`);

--
-- Indices de la tabla `deudas`
--
ALTER TABLE `deudas`
  ADD PRIMARY KEY (`id_deuda`),
  ADD KEY `id_modalidad` (`id_modalidad`),
  ADD KEY `id_estado_cuenta` (`id_estado_deuda`),
  ADD KEY `id_cliente` (`id_cliente`);

--
-- Indices de la tabla `estados`
--
ALTER TABLE `estados`
  ADD PRIMARY KEY (`id_estado`);

--
-- Indices de la tabla `estados_deudas`
--
ALTER TABLE `estados_deudas`
  ADD PRIMARY KEY (`id_estado_deuda`);

--
-- Indices de la tabla `gastos_operacionales`
--
ALTER TABLE `gastos_operacionales`
  ADD PRIMARY KEY (`id_gasto_operacional`),
  ADD KEY `id_tipo_gasto` (`id_tipo_gasto`),
  ADD KEY `id_cartera` (`id_cartera`);

--
-- Indices de la tabla `intereses`
--
ALTER TABLE `intereses`
  ADD PRIMARY KEY (`id_interes`);

--
-- Indices de la tabla `modalidades`
--
ALTER TABLE `modalidades`
  ADD PRIMARY KEY (`id_modalidad`);

--
-- Indices de la tabla `moras`
--
ALTER TABLE `moras`
  ADD PRIMARY KEY (`id_mora`),
  ADD KEY `id_estado` (`id_estado`),
  ADD KEY `id_deuda` (`id_deuda`);

--
-- Indices de la tabla `movimientos_deudas`
--
ALTER TABLE `movimientos_deudas`
  ADD PRIMARY KEY (`id_movimiento_deuda`),
  ADD KEY `id_tipo_movimiento` (`id_tipo_movimiento`),
  ADD KEY `id_deuda` (`id_deuda`);

--
-- Indices de la tabla `pagos`
--
ALTER TABLE `pagos`
  ADD PRIMARY KEY (`id_pago`),
  ADD KEY `id_deuda` (`id_deuda`);

--
-- Indices de la tabla `tipos_gastos`
--
ALTER TABLE `tipos_gastos`
  ADD PRIMARY KEY (`id_tipo_gasto`);

--
-- Indices de la tabla `tipos_movimientos`
--
ALTER TABLE `tipos_movimientos`
  ADD PRIMARY KEY (`id_tipo_movimiento`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `carteras`
--
ALTER TABLE `carteras`
  MODIFY `id_cartera` int(4) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT de la tabla `deudas`
--
ALTER TABLE `deudas`
  MODIFY `id_deuda` int(4) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=29;
--
-- AUTO_INCREMENT de la tabla `gastos_operacionales`
--
ALTER TABLE `gastos_operacionales`
  MODIFY `id_gasto_operacional` int(8) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
--
-- AUTO_INCREMENT de la tabla `moras`
--
ALTER TABLE `moras`
  MODIFY `id_mora` int(8) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `movimientos_deudas`
--
ALTER TABLE `movimientos_deudas`
  MODIFY `id_movimiento_deuda` int(8) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=60;
--
-- AUTO_INCREMENT de la tabla `pagos`
--
ALTER TABLE `pagos`
  MODIFY `id_pago` int(8) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;
--
-- AUTO_INCREMENT de la tabla `tipos_gastos`
--
ALTER TABLE `tipos_gastos`
  MODIFY `id_tipo_gasto` int(2) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `carteras`
--
ALTER TABLE `carteras`
  ADD CONSTRAINT `carteras_ibfk_1` FOREIGN KEY (`id_interes_deuda`) REFERENCES `intereses` (`id_interes`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `carteras_ibfk_2` FOREIGN KEY (`id_cobrador`) REFERENCES `cobradores` (`id_cobrador`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `carteras_ibfk_3` FOREIGN KEY (`id_interes_mora`) REFERENCES `intereses` (`id_interes`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `clientes`
--
ALTER TABLE `clientes`
  ADD CONSTRAINT `clientes_ibfk_1` FOREIGN KEY (`id_estado`) REFERENCES `estados` (`id_estado`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `clientes_ibfk_2` FOREIGN KEY (`id_cartera`) REFERENCES `carteras` (`id_cartera`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `cobradores`
--
ALTER TABLE `cobradores`
  ADD CONSTRAINT `cobradores_ibfk_1` FOREIGN KEY (`id_estado`) REFERENCES `estados` (`id_estado`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `deudas`
--
ALTER TABLE `deudas`
  ADD CONSTRAINT `deudas_ibfk_1` FOREIGN KEY (`id_modalidad`) REFERENCES `modalidades` (`id_modalidad`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `deudas_ibfk_2` FOREIGN KEY (`id_cliente`) REFERENCES `clientes` (`id_cliente`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `deudas_ibfk_3` FOREIGN KEY (`id_estado_deuda`) REFERENCES `estados_deudas` (`id_estado_deuda`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `gastos_operacionales`
--
ALTER TABLE `gastos_operacionales`
  ADD CONSTRAINT `gastos_operacionales_ibfk_1` FOREIGN KEY (`id_cartera`) REFERENCES `carteras` (`id_cartera`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `gastos_operacionales_ibfk_2` FOREIGN KEY (`id_tipo_gasto`) REFERENCES `tipos_gastos` (`id_tipo_gasto`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `moras`
--
ALTER TABLE `moras`
  ADD CONSTRAINT `moras_ibfk_1` FOREIGN KEY (`id_estado`) REFERENCES `estados` (`id_estado`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `moras_ibfk_2` FOREIGN KEY (`id_deuda`) REFERENCES `deudas` (`id_deuda`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `movimientos_deudas`
--
ALTER TABLE `movimientos_deudas`
  ADD CONSTRAINT `movimientos_deudas_ibfk_2` FOREIGN KEY (`id_tipo_movimiento`) REFERENCES `tipos_movimientos` (`id_tipo_movimiento`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `movimientos_deudas_ibfk_3` FOREIGN KEY (`id_deuda`) REFERENCES `deudas` (`id_deuda`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `pagos`
--
ALTER TABLE `pagos`
  ADD CONSTRAINT `pagos_ibfk_1` FOREIGN KEY (`id_deuda`) REFERENCES `deudas` (`id_deuda`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
