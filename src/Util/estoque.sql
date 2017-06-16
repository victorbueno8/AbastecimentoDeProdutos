DROP DATABASE Estoque;
CREATE DATABASE Estoque;
USE Estoque;

CREATE TABLE Fornecedor(
nome		VARCHAR(32),
localidade	VARCHAR(32),
PRIMARY KEY(nome)
);
SELECT * FROM Fornecedor;

CREATE TABLE Produto(
codBarras	VARCHAR(32),
nome		VARCHAR(32),
fabricante	VARCHAR(32),
descricao	VARCHAR(32),
preco		double,
PRIMARY KEY (codBarras),
FOREIGN KEY (fabricante) REFERENCES Fornecedor(nome)
);
SELECT * FROM Produto;

CREATE TABLE ProdutoEstoque(
codBarras	VARCHAR(32),
quantidade	INTEGER,
faixaNormal INTEGER,
precoTotal	DOUBLE,
PRIMARY KEY(codBarras),
FOREIGN KEY(codBarras) REFERENCES Produto(codBarras)
);
SELECT * FROM ProdutoEstoque;

CREATE TABLE Venda(
id			INTEGER,
codBarras	VARCHAR(32),
unidades	INTEGER,
PRIMARY KEY(id),
FOREIGN KEY(codBarras) REFERENCES ProdutoEstoque(codBarras)
);
SELECT * FROM Venda;

CREATE TABLE Pedido(
id			INTEGER,
codBarras	VARCHAR(32),
quantidade	INTEGER,
fornecedor	VARCHAR(32),
descricao	VARCHAR(96),
dataEnvio	DATETIME,
PRIMARY KEY(id),
FOREIGN KEY(codBarras) REFERENCES Produto(codBarras),
FOREIGN KEY(fornecedor) REFERENCES Fornecedor(nome)
);
SELECT * FROM PEDIDO;

CREATE TABLE HistoricoRecebimento(
id			INTEGER,
codBarras	VARCHAR(32),
quantidade	INTEGER,
fornecedor	VARCHAR(32),
descricao	VARCHAR(96),
dataEnvio	DATETIME,
dataRecibo	DATETIME,
qtdRecebida	INTEGER,
descricaoRecebimento	VARCHAR(96),
PRIMARY KEY(id),
FOREIGN KEY(codBarras) REFERENCES Produto(codBarras),
FOREIGN KEY(fornecedor) REFERENCES Fornecedor(nome)
);
select * from HistoricoRecebimento;
