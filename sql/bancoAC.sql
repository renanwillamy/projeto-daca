-- phpMyAdmin SQL Dump
-- version 4.5.0.2
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Tempo de geração: 15/11/2015 às 18:03
-- Versão do servidor: 10.0.17-MariaDB
-- Versão do PHP: 5.5.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `bancoAC`
--

-- --------------------------------------------------------

--
-- Estrutura para tabela `Caixa`
--

CREATE TABLE `Caixa` (
  `ID` int(11) NOT NULL,
  `aberto` bit(1) NOT NULL,
  `dataAbertura` datetime DEFAULT NULL,
  `dataFechamento` datetime DEFAULT NULL,
  `valorFinal` double NOT NULL,
  `valorInicial` double NOT NULL,
  `pontoDeRecebimento_id` int(11) DEFAULT NULL,
  `usuario_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura para tabela `Categoria`
--

CREATE TABLE `Categoria` (
  `id` int(11) NOT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `CategoriaPai` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Fazendo dump de dados para tabela `Categoria`
--

INSERT INTO `Categoria` (`id`, `nome`, `CategoriaPai`) VALUES
(3, 'Eletrônicos', NULL),
(5, 'Notebooks', NULL),
(6, 'Eletrodomésticos', NULL),
(15, 'Tvs', NULL),
(16, 'Dvds', NULL),
(17, 'VideoGames', NULL),
(25, 'SmartPhones', NULL);

-- --------------------------------------------------------

--
-- Estrutura para tabela `Categoria_Produto`
--

CREATE TABLE `Categoria_Produto` (
  `Categoria_id` int(11) NOT NULL,
  `produtos_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura para tabela `Cliente`
--

CREATE TABLE `Cliente` (
  `id` int(11) NOT NULL,
  `RG` varchar(255) DEFAULT NULL,
  `bloqueado` bit(1) NOT NULL,
  `cpf` varchar(255) DEFAULT NULL,
  `informacoes` varchar(255) DEFAULT NULL,
  `limiteCredito` double NOT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `sexo` varchar(255) DEFAULT NULL,
  `endereco_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Fazendo dump de dados para tabela `Cliente`
--

INSERT INTO `Cliente` (`id`, `RG`, `bloqueado`, `cpf`, `informacoes`, `limiteCredito`, `nome`, `sexo`, `endereco_id`) VALUES
(1, '3354633', b'0', '055.448.833-33', NULL, 0, 'Renan Willamy', 'm', 18),
(2, '33546512', b'0', '088.991.899-44', NULL, 0, 'Jose Bezerra de Oliveira', 'm', 19),
(3, '3376988', b'0', '833.544.677-65', NULL, 0, 'Rodrigo de Oliveira', 'm', 20),
(5, '55461251', b'0', '022.546.879-22', NULL, 0, 'Eliane Lima dos Santos', 'f', 22),
(6, '33241123', b'0', '765.488.787-87', NULL, 0, 'Emanoel de Lima Brito', 'm', 26);

-- --------------------------------------------------------

--
-- Estrutura para tabela `Config`
--

CREATE TABLE `Config` (
  `id` int(11) NOT NULL,
  `showErrorMessages` bit(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura para tabela `Contato`
--

CREATE TABLE `Contato` (
  `ID` int(11) NOT NULL,
  `celular` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `telefone` varchar(255) DEFAULT NULL,
  `cliente_id` int(11) DEFAULT NULL,
  `empresa_id` int(11) DEFAULT NULL,
  `fornecedor_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Fazendo dump de dados para tabela `Contato`
--

INSERT INTO `Contato` (`ID`, `celular`, `email`, `telefone`, `cliente_id`, `empresa_id`, `fornecedor_id`) VALUES
(12, '(83)99888-3388', 'espacodigital@gmail.com', '(83)3351-5542', NULL, NULL, 17),
(13, '(83)99922-9933', 'renanwillamy@gmail.com', '(83)3351-1156', 1, NULL, NULL),
(14, '(83)99878-7888', 'jose@gmail.com', '(83)3354-2654', 2, NULL, NULL),
(15, '(83)99923-2211', 'rodrigo@gmail.com', '', 3, NULL, NULL),
(17, '(83)89878-7888', 'eliane@gmail.com', '(83)3351-4425', 5, NULL, NULL),
(19, '(83)99655-4343', 'magazineluiza@gmail.com', '(11)3354-6987', NULL, NULL, 18),
(21, '(83)99878-6777', 'mane@gmail.com', '(83)3354-7887', 6, NULL, NULL),
(22, '(83)99779-8989', 'eletroshop@gmail.com', '(83)1189-8279', NULL, NULL, 20),
(24, '(83)99877-4389', 'magazinelaiz@gmail.com', '(11)3352-8982', NULL, NULL, 21);

-- --------------------------------------------------------

--
-- Estrutura para tabela `Empresa`
--

CREATE TABLE `Empresa` (
  `id` int(11) NOT NULL,
  `cnpj` varchar(255) DEFAULT NULL,
  `cpf` varchar(255) DEFAULT NULL,
  `inscricaoEstadual` varchar(255) DEFAULT NULL,
  `nomeFantasia` varchar(255) DEFAULT NULL,
  `razaoSocial` varchar(255) DEFAULT NULL,
  `endereco_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura para tabela `Endereco`
--

CREATE TABLE `Endereco` (
  `id` int(11) NOT NULL,
  `bairro` varchar(255) DEFAULT NULL,
  `cep` varchar(255) DEFAULT NULL,
  `cidade` varchar(255) DEFAULT NULL,
  `complemento` varchar(255) DEFAULT NULL,
  `estado` varchar(255) DEFAULT NULL,
  `logradouro` varchar(255) DEFAULT NULL,
  `numResidencia` int(11) NOT NULL,
  `tipoLogradouro` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Fazendo dump de dados para tabela `Endereco`
--

INSERT INTO `Endereco` (`id`, `bairro`, `cep`, `cidade`, `complemento`, `estado`, `logradouro`, `numResidencia`, `tipoLogradouro`) VALUES
(17, 'Centro', '58500-000', 'Santa Rita', NULL, 'PB', 'Joao Barbosa de Oliveira', 223, NULL),
(18, 'Centro', '58500-000', 'Monteiro', NULL, 'PB', 'José Augusto Gomes', 113, NULL),
(19, 'Centro', '58500-000', 'Monteiro', NULL, 'PB', 'Joao Pereira do Nascimento', 11, NULL),
(20, 'Centro', '42342-342', 'Flores', NULL, 'AC', 'Rua tal', 321, NULL),
(22, 'Centro', '58500-000', 'Monteiro', NULL, 'PB', 'Rua Sicrando do braz', 87, NULL),
(24, 'Centro', '58642-155', 'São Paulo', NULL, 'SP', 'Rua Tal', 22, NULL),
(26, 'Centro', '58500-000', 'Monteiro', NULL, 'PB', 'Rua Beltrano', 10, NULL),
(27, 'Centro', '58500-000', 'Monteiro', NULL, 'PB', 'Rua do Braz', 102, NULL),
(29, 'Centro', '58500-000', 'Monteiro', NULL, 'PB', 'Rua Sicrano', 22, NULL);

-- --------------------------------------------------------

--
-- Estrutura para tabela `Fornecedor`
--

CREATE TABLE `Fornecedor` (
  `id` int(11) NOT NULL,
  `cnpj` varchar(255) DEFAULT NULL,
  `cpf` varchar(255) DEFAULT NULL,
  `nomeFantasia` varchar(255) NOT NULL,
  `observacoes` varchar(255) DEFAULT NULL,
  `razaoSocial` varchar(255) NOT NULL,
  `endereco_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Fazendo dump de dados para tabela `Fornecedor`
--

INSERT INTO `Fornecedor` (`id`, `cnpj`, `cpf`, `nomeFantasia`, `observacoes`, `razaoSocial`, `endereco_id`) VALUES
(17, '76.398.223/0001-76', NULL, 'Espaço Digital', NULL, 'Maria Cavalcante de Lima da Silva', 17),
(18, '58.993.232/0001-88', NULL, 'Magazine Luiza', NULL, 'Pedro da Silva', 24),
(20, '64.324.504/0001-98', NULL, 'Eletroshop', NULL, 'Gabriel Ferreira de Melo', 27),
(21, '48.654.654/0001-33', NULL, 'Magazine Laiz', NULL, 'Fulano de Tal', 29);

-- --------------------------------------------------------

--
-- Estrutura para tabela `ItensDoPedido`
--

CREATE TABLE `ItensDoPedido` (
  `id` int(11) NOT NULL,
  `desconto` double NOT NULL,
  `quantidade` double NOT NULL,
  `und` varchar(255) DEFAULT NULL,
  `valor` double NOT NULL,
  `valorCusto` double NOT NULL,
  `pedido_id` int(11) DEFAULT NULL,
  `produto_id` int(11) DEFAULT NULL,
  `valorTotal` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Fazendo dump de dados para tabela `ItensDoPedido`
--

INSERT INTO `ItensDoPedido` (`id`, `desconto`, `quantidade`, `und`, `valor`, `valorCusto`, `pedido_id`, `produto_id`, `valorTotal`) VALUES
(13, 0, 5, NULL, 10.25, 0, 7, 4, 51.25),
(14, 0, 1, NULL, 199.99, 0, 7, 7, 199.99),
(18, 0, 1, NULL, 199.99, 0, 11, 7, 199.99),
(19, 0, 2, NULL, 10.25, 0, 12, 4, 20.5),
(20, 0, 1, NULL, 199.99, 0, 12, 7, 199.99),
(21, 0, 5, NULL, 10.25, 0, 11, 4, 51.25),
(22, 0, 1, NULL, 1550, 0, 13, 11, 1550),
(23, 0, 1, NULL, 199.99, 0, 13, 7, 199.99),
(24, 0, 1, NULL, 1550, 0, 11, 11, 1550),
(25, 0, 1, NULL, 2600, 0, 14, 12, 2600),
(26, 0, 2, NULL, 10.25, 0, 15, 4, 20.5),
(27, 0, 2, NULL, 35.99, 0, 15, 14, 71.98);

-- --------------------------------------------------------

--
-- Estrutura para tabela `Pagamento`
--

CREATE TABLE `Pagamento` (
  `TIPO` varchar(31) NOT NULL,
  `id` int(11) NOT NULL,
  `dataInicial` datetime DEFAULT NULL,
  `dataPagamento` datetime DEFAULT NULL,
  `desconto` double NOT NULL,
  `juros` double NOT NULL,
  `status` varchar(255) DEFAULT NULL,
  `tipoDePgto` varchar(255) DEFAULT NULL,
  `valorPagamento` double NOT NULL,
  `valorPago` double NOT NULL,
  `prazo` int(11) DEFAULT NULL,
  `agencia` varchar(255) DEFAULT NULL,
  `banco` varchar(255) DEFAULT NULL,
  `conta` varchar(255) DEFAULT NULL,
  `numeroCheque` varchar(255) DEFAULT NULL,
  `situacao` varchar(255) DEFAULT NULL,
  `titular` varchar(255) DEFAULT NULL,
  `caixa_ID` int(11) DEFAULT NULL,
  `pagamento_id` int(11) DEFAULT NULL,
  `pedido_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Fazendo dump de dados para tabela `Pagamento`
--

INSERT INTO `Pagamento` (`TIPO`, `id`, `dataInicial`, `dataPagamento`, `desconto`, `juros`, `status`, `tipoDePgto`, `valorPagamento`, `valorPago`, `prazo`, `agencia`, `banco`, `conta`, `numeroCheque`, `situacao`, `titular`, `caixa_ID`, `pagamento_id`, `pedido_id`) VALUES
('AV', 7, '2015-11-14 00:19:27', '2015-11-14 00:19:27', 0, 0, 'LIQUIDADO', 'AVISTA', 220.49, 220.49, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 12),
('AV', 10, '2015-11-14 12:29:31', '2015-11-14 12:29:31', 0, 0, 'LIQUIDADO', 'AVISTA', 251.24, 251.24, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 7),
('AV', 13, '2015-11-14 12:35:05', '2015-11-14 12:35:05', 0, 0, 'LIQUIDADO', 'AVISTA', 1749.99, 1749.99, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 13),
('AV', 15, '2015-11-14 14:12:28', '2015-11-14 14:12:28', 0, 0, 'LIQUIDADO', 'AVISTA', 1801.24, 1801.24, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 11),
('AV', 16, '2015-11-14 16:52:12', '2015-11-14 16:52:12', 0, 0, 'LIQUIDADO', 'AVISTA', 2600, 2600, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 14),
('AV', 17, '2015-11-15 12:59:20', '2015-11-15 12:59:20', 0, 0, 'LIQUIDADO', 'AVISTA', 92.48, 92.48, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 15);

-- --------------------------------------------------------

--
-- Estrutura para tabela `Pedido`
--

CREATE TABLE `Pedido` (
  `id` int(11) NOT NULL,
  `dataDoPedido` datetime DEFAULT NULL,
  `descontoPorCento` double NOT NULL,
  `descontoReais` double NOT NULL,
  `cliente_id` int(11) DEFAULT NULL,
  `valorTotal` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Fazendo dump de dados para tabela `Pedido`
--

INSERT INTO `Pedido` (`id`, `dataDoPedido`, `descontoPorCento`, `descontoReais`, `cliente_id`, `valorTotal`) VALUES
(7, '2015-11-14 12:29:35', 0, 0, 2, 251.24),
(11, '2015-11-14 14:12:36', 0, 0, 3, 1801.24),
(12, '2015-11-14 00:19:55', 0, 0, 3, 220.49),
(13, '2015-11-14 12:36:00', 0, 0, 3, 1749.99),
(14, '2015-11-14 16:52:57', 0, 0, 5, 2600),
(15, '2015-11-15 13:00:22', 0, 0, 6, 92.48);

-- --------------------------------------------------------

--
-- Estrutura para tabela `Permissoes`
--

CREATE TABLE `Permissoes` (
  `id` int(11) NOT NULL,
  `caixa` bit(1) NOT NULL,
  `consultarClientesInadimplentes` bit(1) NOT NULL,
  `consultarTitulos` bit(1) NOT NULL,
  `gerarRelatorios` bit(1) NOT NULL,
  `manterCategorias` bit(1) NOT NULL,
  `manterCliente` bit(1) NOT NULL,
  `manterEmpresa` bit(1) NOT NULL,
  `manterFornecedor` bit(1) NOT NULL,
  `manterFuncionarios` bit(1) NOT NULL,
  `manterProdutos` bit(1) NOT NULL,
  `manterUsuario` bit(1) NOT NULL,
  `vendaInterna` bit(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura para tabela `PontoDeRecebimento`
--

CREATE TABLE `PontoDeRecebimento` (
  `id` int(11) NOT NULL,
  `descricao` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura para tabela `Produto`
--

CREATE TABLE `Produto` (
  `id` int(11) NOT NULL,
  `ativo` bit(1) NOT NULL,
  `codigoDeBarras` varchar(255) DEFAULT NULL,
  `estoqueMinimo` int(11) NOT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `precoCusto` double NOT NULL,
  `precoVenda` double NOT NULL,
  `quantidade` double NOT NULL,
  `und` varchar(255) DEFAULT NULL,
  `categoria_id` int(11) DEFAULT NULL,
  `fornecedor_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Fazendo dump de dados para tabela `Produto`
--

INSERT INTO `Produto` (`id`, `ativo`, `codigoDeBarras`, `estoqueMinimo`, `nome`, `precoCusto`, `precoVenda`, `quantidade`, `und`, `categoria_id`, `fornecedor_id`) VALUES
(4, b'1', '123456789', 0, 'Dvd Samsung', 3.05, 10.25, 16.5, 'pct', 16, 17),
(7, b'1', '1254212', 0, 'DVD LG', 150.99, 199.99, -1, 'und', 16, 17),
(9, b'1', '4324423', 0, 'Notebook HP dv5', 1650, 1950, 10, 'und', 5, 17),
(10, b'0', '4243242', 0, 'Play Station 3', 900, 1200, 0, 'pct', 17, 17),
(11, b'1', '232434', 0, 'Tv LG 42 pl', 1200, 1550, 4, 'und', 15, 17),
(12, b'1', '99542145412', 0, 'Atari Video game', 2000, 2600, 1, 'und', 17, 18),
(13, b'1', '125421', 0, 'Notebook Dell ldw1', 1850, 2500, 5, 'und', 5, 18),
(14, b'1', '12354', 0, 'Mouse Óptico', 25.99, 35.99, 28, 'und', 3, 17),
(15, b'0', '110920291', 0, 'Teclado Multilaser', 20, 35.99, 20, 'und', 3, 20);

-- --------------------------------------------------------

--
-- Estrutura para tabela `Usuario`
--

CREATE TABLE `Usuario` (
  `id` int(11) NOT NULL,
  `login` varchar(255) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `senha` varchar(255) DEFAULT NULL,
  `endereco_id` int(11) DEFAULT NULL,
  `permissoes_id` int(11) DEFAULT NULL,
  `acesso` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Fazendo dump de dados para tabela `Usuario`
--

INSERT INTO `Usuario` (`id`, `login`, `nome`, `senha`, `endereco_id`, `permissoes_id`, `acesso`) VALUES
(1, 'maria', 'Maria José', '123', NULL, NULL, 'admin'),
(13, 'jsilva2', 'João da Silva', 'f6e0a1e2ac41945a9aa7ff8a8aaa0cebc12a3bcc981a929ad5cf810a090e11ae', NULL, NULL, 'visitante'),
(22, 'renanwb', 'Renan Willamy', '5994471abb01112afcc18159f6cc74b4f511b99806da59b3caf5a9c173cacfc5', NULL, NULL, 'admin'),
(23, 'guilherme2', 'Guilherme de Oliveira', '123', NULL, NULL, 'visitante'),
(24, 'cleiton2', 'Cleiton da Silva', '1234', NULL, NULL, 'visitante'),
(25, 'jbs', 'Jose Bezerra da Silva', '3ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4', NULL, NULL, 'visitante'),
(26, 'admin', 'Administrador', '5994471abb01112afcc18159f6cc74b4f511b99806da59b3caf5a9c173cacfc5', 17, NULL, 'admin'),
(27, 'rgabriel', 'Ruan Gabriel', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', NULL, NULL, 'visitante'),
(28, 'jc', 'Joel Cavalcante', '6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b', NULL, NULL, 'admin'),
(29, 'visitante', 'Visitante2', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', NULL, NULL, 'visitante'),
(30, 'rodolfo', 'Rodolfo de Oliveira', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', NULL, NULL, 'visitante');

--
-- Índices de tabelas apagadas
--

--
-- Índices de tabela `Caixa`
--
ALTER TABLE `Caixa`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_gd0mxkaqb64tyb66trsrche7q` (`pontoDeRecebimento_id`),
  ADD KEY `FK_cs6s7dua9v4j22r91xulwl4o2` (`usuario_id`);

--
-- Índices de tabela `Categoria`
--
ALTER TABLE `Categoria`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `nome` (`nome`),
  ADD KEY `FK_g5eb3f2klm27ca0s040j4ry3r` (`CategoriaPai`);

--
-- Índices de tabela `Categoria_Produto`
--
ALTER TABLE `Categoria_Produto`
  ADD UNIQUE KEY `UK_iqd3ayo75njdhh8hpgrg747hp` (`produtos_id`),
  ADD KEY `FK_o0tobvikmtq6py3hafmk5ne1q` (`Categoria_id`);

--
-- Índices de tabela `Cliente`
--
ALTER TABLE `Cliente`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_qxiooc3mm81acqe8lwcxvkbjd` (`endereco_id`);

--
-- Índices de tabela `Config`
--
ALTER TABLE `Config`
  ADD PRIMARY KEY (`id`);

--
-- Índices de tabela `Contato`
--
ALTER TABLE `Contato`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_9h78awfoy08gk6x471nqxbnkq` (`cliente_id`),
  ADD KEY `FK_2tm7255auggu1wsfgdfchi8e5` (`empresa_id`),
  ADD KEY `FK_jndolte2ki8pv6kfmsraluwsy` (`fornecedor_id`);

--
-- Índices de tabela `Empresa`
--
ALTER TABLE `Empresa`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_qsj0om3x0g7ww0hdxd6xt1bui` (`endereco_id`);

--
-- Índices de tabela `Endereco`
--
ALTER TABLE `Endereco`
  ADD PRIMARY KEY (`id`);

--
-- Índices de tabela `Fornecedor`
--
ALTER TABLE `Fornecedor`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_m9jk0j6dahmt3tyxb59u2lx1r` (`endereco_id`);

--
-- Índices de tabela `ItensDoPedido`
--
ALTER TABLE `ItensDoPedido`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_sqktchjvkhlghxqtduk70fgo6` (`pedido_id`),
  ADD KEY `FK_68sindqgam3q84md4wax907n6` (`produto_id`);

--
-- Índices de tabela `Pagamento`
--
ALTER TABLE `Pagamento`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_cv7dq9y81hj1hqlaqgebaxw98` (`caixa_ID`),
  ADD KEY `FK_qc4c61n7dkjjk1kgj37tia34e` (`pagamento_id`),
  ADD KEY `FK_h83thw20w3y2oqriku8o7lntw` (`pedido_id`);

--
-- Índices de tabela `Pedido`
--
ALTER TABLE `Pedido`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_nmx283i28kpfbnjwln34xu8lm` (`cliente_id`);

--
-- Índices de tabela `Permissoes`
--
ALTER TABLE `Permissoes`
  ADD PRIMARY KEY (`id`);

--
-- Índices de tabela `PontoDeRecebimento`
--
ALTER TABLE `PontoDeRecebimento`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_qxr3p9e3huwpj17i6ygk8dn02` (`descricao`);

--
-- Índices de tabela `Produto`
--
ALTER TABLE `Produto`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_7roeds87qp6pp2g07rv86t8cb` (`categoria_id`),
  ADD KEY `FK_lrtcw8w6lnpretansc85oml99` (`fornecedor_id`);

--
-- Índices de tabela `Usuario`
--
ALTER TABLE `Usuario`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `login` (`login`),
  ADD KEY `FK_67jhsvufl4c83nul9wxtvmv3y` (`endereco_id`),
  ADD KEY `FK_h5g5t2noa73mnwuhlwbo6c1hm` (`permissoes_id`);

--
-- AUTO_INCREMENT de tabelas apagadas
--

--
-- AUTO_INCREMENT de tabela `Caixa`
--
ALTER TABLE `Caixa`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de tabela `Categoria`
--
ALTER TABLE `Categoria`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;
--
-- AUTO_INCREMENT de tabela `Cliente`
--
ALTER TABLE `Cliente`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
--
-- AUTO_INCREMENT de tabela `Config`
--
ALTER TABLE `Config`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de tabela `Contato`
--
ALTER TABLE `Contato`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;
--
-- AUTO_INCREMENT de tabela `Empresa`
--
ALTER TABLE `Empresa`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de tabela `Endereco`
--
ALTER TABLE `Endereco`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=30;
--
-- AUTO_INCREMENT de tabela `Fornecedor`
--
ALTER TABLE `Fornecedor`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;
--
-- AUTO_INCREMENT de tabela `ItensDoPedido`
--
ALTER TABLE `ItensDoPedido`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=28;
--
-- AUTO_INCREMENT de tabela `Pagamento`
--
ALTER TABLE `Pagamento`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;
--
-- AUTO_INCREMENT de tabela `Pedido`
--
ALTER TABLE `Pedido`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;
--
-- AUTO_INCREMENT de tabela `Permissoes`
--
ALTER TABLE `Permissoes`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de tabela `PontoDeRecebimento`
--
ALTER TABLE `PontoDeRecebimento`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de tabela `Produto`
--
ALTER TABLE `Produto`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;
--
-- AUTO_INCREMENT de tabela `Usuario`
--
ALTER TABLE `Usuario`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;
--
-- Restrições para dumps de tabelas
--

--
-- Restrições para tabelas `Caixa`
--
ALTER TABLE `Caixa`
  ADD CONSTRAINT `FK_cs6s7dua9v4j22r91xulwl4o2` FOREIGN KEY (`usuario_id`) REFERENCES `Usuario` (`id`),
  ADD CONSTRAINT `FK_gd0mxkaqb64tyb66trsrche7q` FOREIGN KEY (`pontoDeRecebimento_id`) REFERENCES `PontoDeRecebimento` (`id`);

--
-- Restrições para tabelas `Categoria`
--
ALTER TABLE `Categoria`
  ADD CONSTRAINT `FK_g5eb3f2klm27ca0s040j4ry3r` FOREIGN KEY (`CategoriaPai`) REFERENCES `Categoria` (`id`);

--
-- Restrições para tabelas `Categoria_Produto`
--
ALTER TABLE `Categoria_Produto`
  ADD CONSTRAINT `FK_iqd3ayo75njdhh8hpgrg747hp` FOREIGN KEY (`produtos_id`) REFERENCES `Produto` (`id`),
  ADD CONSTRAINT `FK_o0tobvikmtq6py3hafmk5ne1q` FOREIGN KEY (`Categoria_id`) REFERENCES `Categoria` (`id`);

--
-- Restrições para tabelas `Cliente`
--
ALTER TABLE `Cliente`
  ADD CONSTRAINT `FK_qxiooc3mm81acqe8lwcxvkbjd` FOREIGN KEY (`endereco_id`) REFERENCES `Endereco` (`id`);

--
-- Restrições para tabelas `Contato`
--
ALTER TABLE `Contato`
  ADD CONSTRAINT `FK_2tm7255auggu1wsfgdfchi8e5` FOREIGN KEY (`empresa_id`) REFERENCES `Empresa` (`id`),
  ADD CONSTRAINT `FK_9h78awfoy08gk6x471nqxbnkq` FOREIGN KEY (`cliente_id`) REFERENCES `Cliente` (`id`),
  ADD CONSTRAINT `FK_jndolte2ki8pv6kfmsraluwsy` FOREIGN KEY (`fornecedor_id`) REFERENCES `Fornecedor` (`id`);

--
-- Restrições para tabelas `Empresa`
--
ALTER TABLE `Empresa`
  ADD CONSTRAINT `FK_qsj0om3x0g7ww0hdxd6xt1bui` FOREIGN KEY (`endereco_id`) REFERENCES `Endereco` (`id`);

--
-- Restrições para tabelas `Fornecedor`
--
ALTER TABLE `Fornecedor`
  ADD CONSTRAINT `FK_m9jk0j6dahmt3tyxb59u2lx1r` FOREIGN KEY (`endereco_id`) REFERENCES `Endereco` (`id`);

--
-- Restrições para tabelas `ItensDoPedido`
--
ALTER TABLE `ItensDoPedido`
  ADD CONSTRAINT `FK_68sindqgam3q84md4wax907n6` FOREIGN KEY (`produto_id`) REFERENCES `Produto` (`id`),
  ADD CONSTRAINT `FK_sqktchjvkhlghxqtduk70fgo6` FOREIGN KEY (`pedido_id`) REFERENCES `Pedido` (`id`);

--
-- Restrições para tabelas `Pagamento`
--
ALTER TABLE `Pagamento`
  ADD CONSTRAINT `FK_cv7dq9y81hj1hqlaqgebaxw98` FOREIGN KEY (`caixa_ID`) REFERENCES `Caixa` (`ID`),
  ADD CONSTRAINT `FK_h83thw20w3y2oqriku8o7lntw` FOREIGN KEY (`pedido_id`) REFERENCES `Pedido` (`id`),
  ADD CONSTRAINT `FK_qc4c61n7dkjjk1kgj37tia34e` FOREIGN KEY (`pagamento_id`) REFERENCES `Pagamento` (`id`);

--
-- Restrições para tabelas `Pedido`
--
ALTER TABLE `Pedido`
  ADD CONSTRAINT `FK_nmx283i28kpfbnjwln34xu8lm` FOREIGN KEY (`cliente_id`) REFERENCES `Cliente` (`id`);

--
-- Restrições para tabelas `Produto`
--
ALTER TABLE `Produto`
  ADD CONSTRAINT `FK_7roeds87qp6pp2g07rv86t8cb` FOREIGN KEY (`categoria_id`) REFERENCES `Categoria` (`id`),
  ADD CONSTRAINT `FK_lrtcw8w6lnpretansc85oml99` FOREIGN KEY (`fornecedor_id`) REFERENCES `Fornecedor` (`id`);

--
-- Restrições para tabelas `Usuario`
--
ALTER TABLE `Usuario`
  ADD CONSTRAINT `FK_67jhsvufl4c83nul9wxtvmv3y` FOREIGN KEY (`endereco_id`) REFERENCES `Endereco` (`id`),
  ADD CONSTRAINT `FK_h5g5t2noa73mnwuhlwbo6c1hm` FOREIGN KEY (`permissoes_id`) REFERENCES `Permissoes` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
