-- phpMyAdmin SQL Dump
-- version 4.8.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: 30-Jun-2018 às 01:32
-- Versão do servidor: 10.1.33-MariaDB
-- PHP Version: 7.2.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `cadprod`
--
CREATE DATABASE IF NOT EXISTS `cadprod` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `cadprod`;

-- --------------------------------------------------------

--
-- Estrutura da tabela `admin`
--

CREATE TABLE `admin` (
  `idadmin` int(11) NOT NULL,
  `nome` varchar(45) NOT NULL,
  `login` varchar(45) NOT NULL,
  `senha` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `admin`
--

INSERT INTO `admin` (`idadmin`, `nome`, `login`, `senha`) VALUES
(1, 'Michell', 'michell', '1234'),
(24, 'admin', 'admin', 'admin'),
(27, 'Kleber', 'kleber', '1234'),
(29, 'Max', 'max', 'max'),
(31, 'Comic Sans', 'comic', 'comic'),
(32, 'Pedro', 'pedro', '123');

-- --------------------------------------------------------

--
-- Estrutura da tabela `categoria`
--

CREATE TABLE `categoria` (
  `idcategoria` int(11) NOT NULL,
  `categoria` varchar(45) NOT NULL,
  `descricao` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `categoria`
--

INSERT INTO `categoria` (`idcategoria`, `categoria`, `descricao`) VALUES
(5, 'Roupas', 'Roupas diversas'),
(6, 'Calçados', 'Calçados de varias coisas'),
(7, 'Alimentos', 'Todos os gêneros alimentícios'),
(9, 'Armamento', 'Categoria destinada a armamentos e afins'),
(10, 'Brinquedos', 'Brinquedos diversos para todas as idades'),
(12, 'Eletrônicos', 'Eletrônicos'),
(13, 'Móveis', 'Móveis diversos'),
(14, 'Automóvel', 'Automóveis diversos');

-- --------------------------------------------------------

--
-- Estrutura da tabela `produto`
--

CREATE TABLE `produto` (
  `idproduto` int(11) NOT NULL,
  `nome` varchar(45) NOT NULL,
  `preco` decimal(10,2) NOT NULL DEFAULT '0.00',
  `datacadastro` date NOT NULL,
  `idadmin` int(11) NOT NULL,
  `idcategoria` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `produto`
--

INSERT INTO `produto` (`idproduto`, `nome`, `preco`, `datacadastro`, `idadmin`, `idcategoria`) VALUES
(9, 'Camiseta G', '59.99', '2018-05-09', 1, 5),
(11, 'Calça Jeans', '12.00', '2018-06-11', 24, 5),
(12, 'Meia Nike', '26.00', '2018-06-01', 1, 5),
(17, 'Tênis Azul ', '300.00', '2018-06-01', 1, 6),
(18, 'Glock 380', '4670.00', '2018-06-01', 1, 9),
(23, 'Terno', '1360.99', '2018-06-01', 1, 5),
(28, 'Pizza', '123.00', '2018-06-01', 24, 7),
(29, 'Espetinho do tata', '16.00', '2018-06-01', 1, 7),
(30, 'Bota', '277.43', '2018-06-26', 24, 6),
(31, 'M16', '56000.00', '2018-06-04', 1, 9),
(32, 'Fal', '32000.00', '2018-06-04', 1, 9),
(38, 'Bolo de cenoura', '34.00', '2018-06-07', 24, 7),
(40, 'Capuccino', '23.50', '2018-06-09', 24, 7),
(41, 'Costela Assada', '36.99', '2018-06-09', 1, 7),
(42, 'Picanha', '34.75', '2018-06-09', 1, 7),
(43, 'Lego 1000 peças', '456.19', '2018-06-10', 1, 10),
(44, 'iPhone X', '7019.09', '2018-06-10', 1, 12),
(45, 'Galaxy S9', '3869.10', '2018-06-10', 1, 12),
(53, 'Blusa Verde', '99.00', '2018-06-26', 24, 5),
(54, 'Brusinha', '99.00', '2018-06-26', 24, 5),
(56, 'Tenis', '23.00', '2018-06-26', 24, 6),
(61, 'Meia', '333.00', '2018-06-26', 24, 5),
(65, 'Bolacha Recheada', '4.88', '2018-06-26', 24, 7),
(66, 'Brusa amarela', '55.00', '2018-06-27', 24, 5),
(67, 'Brusa azul', '87.00', '2018-06-26', 24, 5),
(69, 'Tanque de guerra', '99999.00', '2018-06-27', 24, 9),
(76, 'Fusca 74', '8000.00', '2018-06-28', 24, 14),
(77, 'Uno Zero', '67000.00', '2018-06-28', 24, 14),
(78, 'Honda Fit', '3745.99', '2018-06-28', 24, 14),
(79, 'Cozinha planejada', '12000.00', '2018-06-28', 24, 13),
(80, 'Salada de tomate', '12.00', '2018-06-28', 24, 7),
(81, 'Bolacha Mabela', '32.12', '2018-06-26', 24, 7),
(82, 'Lasanha', '77.00', '2018-06-28', 24, 7),
(83, 'alimen', '88.00', '2018-06-28', 24, 9);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`idadmin`);

--
-- Indexes for table `categoria`
--
ALTER TABLE `categoria`
  ADD PRIMARY KEY (`idcategoria`);

--
-- Indexes for table `produto`
--
ALTER TABLE `produto`
  ADD PRIMARY KEY (`idproduto`),
  ADD KEY `fk_produto_admin_idx` (`idadmin`),
  ADD KEY `fk_produto_categoria1_idx` (`idcategoria`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admin`
--
ALTER TABLE `admin`
  MODIFY `idadmin` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=35;

--
-- AUTO_INCREMENT for table `categoria`
--
ALTER TABLE `categoria`
  MODIFY `idcategoria` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT for table `produto`
--
ALTER TABLE `produto`
  MODIFY `idproduto` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=84;

--
-- Constraints for dumped tables
--

--
-- Limitadores para a tabela `produto`
--
ALTER TABLE `produto`
  ADD CONSTRAINT `fk_produto_admin` FOREIGN KEY (`idadmin`) REFERENCES `admin` (`idadmin`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_produto_categoria1` FOREIGN KEY (`idcategoria`) REFERENCES `categoria` (`idcategoria`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
