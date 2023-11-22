-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: database:3306
-- Tiempo de generación: 22-11-2023 a las 22:52:30
-- Versión del servidor: 8.1.0
-- Versión de PHP: 8.2.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `kanban`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `list`
--

CREATE TABLE `list` (
  `id` bigint NOT NULL,
  `name` varchar(255) CHARACTER SET utf16 COLLATE utf16_unicode_ci NOT NULL,
  `id_user` bigint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf16 COLLATE=utf16_unicode_ci;

--
-- Volcado de datos para la tabla `list`
--

INSERT INTO `list` (`id`, `name`, `id_user`) VALUES
(3, 'Proyecto de DIW', 4),
(5, 'Proyecto de DEC/DES', 4),
(13, 'Proyecto empresarial EIE', 4),
(14, 'Organización de tareas', 5),
(17, 'Examen Despliegue', 4);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `task`
--

CREATE TABLE `task` (
  `id` bigint NOT NULL,
  `description` varchar(255) CHARACTER SET utf16 COLLATE utf16_unicode_ci NOT NULL,
  `creation_date` datetime NOT NULL,
  `end_date` datetime NOT NULL,
  `state` varchar(255) CHARACTER SET utf16 COLLATE utf16_unicode_ci NOT NULL,
  `id_list` bigint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf16 COLLATE=utf16_unicode_ci;

--
-- Volcado de datos para la tabla `task`
--

INSERT INTO `task` (`id`, `description`, `creation_date`, `end_date`, `state`, `id_list`) VALUES
(3, 'Realizar base de datos', '2023-11-18 22:00:00', '2023-11-19 22:00:00', 'Por hacer', 5),
(4, 'Servidor', '2023-11-12 22:00:00', '2023-11-15 22:00:00', 'Progreso', 5),
(5, 'Importar bulma', '2023-11-20 22:00:00', '2023-11-20 22:00:00', 'Terminado', 3),
(7, 'Realizar Navbar', '2023-11-16 22:00:00', '2023-11-17 22:00:00', 'Terminado', 3),
(8, 'Instalar Sass', '2023-11-21 22:00:00', '2023-11-22 22:00:00', 'Terminado', 3),
(9, 'Realizar documento', '2023-11-21 22:00:00', '2023-11-22 22:00:00', 'En Progreso', 3),
(10, 'D.A.F.O.', '2023-11-22 22:17:58', '2023-11-19 22:00:00', 'En Progreso', 13),
(11, 'Organigrama', '2023-11-22 22:18:15', '2023-11-20 22:00:00', 'Terminado', 13),
(12, 'Resumir teoria', '2023-11-22 22:18:35', '2023-11-15 22:00:00', 'En Progreso', 13),
(13, 'Estudiar tests', '2023-11-22 22:18:52', '2023-11-11 22:00:00', 'Por hacer', 13),
(14, 'Realizar Footer', '2023-11-22 22:19:25', '2023-11-21 22:00:00', 'En Progreso', 3),
(15, 'Realizar seccion de columns', '2023-11-22 22:19:52', '2023-11-19 22:00:00', 'Por hacer', 3),
(16, 'Test AWS', '2023-11-22 22:20:14', '2023-11-20 22:00:00', 'En Progreso', 17),
(17, 'Realizar Labs', '2023-11-22 22:20:53', '2023-11-18 22:00:00', 'Por hacer', 17),
(18, 'Estudiar T1-T5', '2023-11-22 22:21:30', '2023-11-17 22:00:00', 'Terminado', 17),
(19, 'Componente menu', '2023-11-22 22:21:56', '2023-10-31 22:00:00', 'Terminado', 14),
(20, 'Componente Home', '2023-11-22 22:22:12', '2023-11-01 22:00:00', 'En Progreso', 14),
(21, 'Componente footer', '2023-11-22 22:23:12', '2023-11-02 22:00:00', 'Terminado', 14),
(22, 'Componentes user', '2023-11-22 22:23:26', '2023-11-03 22:00:00', 'En Progreso', 14),
(23, 'COmponentes list', '2023-11-22 22:23:43', '2023-11-04 22:00:00', 'Por hacer', 14),
(24, 'Componentes task', '2023-11-22 22:24:00', '2023-11-05 22:00:00', 'Por hacer', 14);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `user`
--

CREATE TABLE `user` (
  `id` bigint NOT NULL,
  `email` varchar(255) CHARACTER SET utf16 COLLATE utf16_unicode_ci NOT NULL,
  `username` varchar(255) CHARACTER SET utf16 COLLATE utf16_unicode_ci NOT NULL,
  `password` varchar(256) CHARACTER SET utf16 COLLATE utf16_unicode_ci NOT NULL,
  `role` bit(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf16 COLLATE=utf16_unicode_ci;

--
-- Volcado de datos para la tabla `user`
--

INSERT INTO `user` (`id`, `email`, `username`, `password`, `role`) VALUES
(4, 'evalara@gmail.com', 'evalara', '7cc6d962e40fbc035b7a898a48d8aa8d7574a1319d4724c596b22bd06f3f16ac', b'0'),
(5, 'kanban@gmail.com', 'kanban', '7cc6d962e40fbc035b7a898a48d8aa8d7574a1319d4724c596b22bd06f3f16ac', b'1');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `list`
--
ALTER TABLE `list`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKekdufkjw5pvxdg2bp05k8csx2` (`id_user`);

--
-- Indices de la tabla `task`
--
ALTER TABLE `task`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKb9fsfd808h54y3ij7ykmw5swe` (`id_list`);

--
-- Indices de la tabla `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `list`
--
ALTER TABLE `list`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT de la tabla `task`
--
ALTER TABLE `task`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- AUTO_INCREMENT de la tabla `user`
--
ALTER TABLE `user`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=43;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `list`
--
ALTER TABLE `list`
  ADD CONSTRAINT `FKekdufkjw5pvxdg2bp05k8csx2` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`);

--
-- Filtros para la tabla `task`
--
ALTER TABLE `task`
  ADD CONSTRAINT `FKb9fsfd808h54y3ij7ykmw5swe` FOREIGN KEY (`id_list`) REFERENCES `list` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
