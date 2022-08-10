use master
DROP DATABASE J6Lab8
GO

CREATE DATABASE J6Lab8
GO
USE J6Lab8
GO

CREATE TABLE Accounts (
	username VARCHAR(30) PRIMARY KEY,
	password VARCHAR(20),
	email VARCHAR(50),
	image VARCHAR(MAX)
)
GO

CREATE TABLE Roles (
	rid VARCHAR(20) PRIMARY KEY,
	name VARCHAR(50) 
)

CREATE TABLE Authorities (
	id INT IDENTITY(1,1) PRIMARY KEY,
	username VARCHAR(30),
	rid VARCHAR(20)
)
GO

CREATE TABLE Products (
	id INT IDENTITY(1,1) PRIMARY KEY,
	brand VARCHAR(20),
	name VARCHAR(50),
	type VARCHAR(20),
	price FLOAT,
	discount FLOAT,
	quantity INT,
	image VARCHAR(MAX)
)
GO

CREATE TABLE Carts (
	CID INT IDENTITY(1,1),
	id as 'CART' + CAST(CID as VARCHAR(10)) PERSISTED PRIMARY KEY,
	image VARCHAR(MAX),
	name VARCHAR(50),
	price FLOAT,
	quantity INT,
	username VARCHAR(30),
	productid INT
)
GO


CREATE TABLE Brands (
	bid VARCHAR(20) PRIMARY KEY,
	name VARCHAR(50)
)
GO

CREATE TABLE ProductTypes (
    tid VARCHAR(20) PRIMARY KEY,
	name VARCHAR(50)
)
GO

CREATE TABLE Receipts (
	RID INT IDENTITY(1,1) PRIMARY KEY,
	image VARCHAR(250),
	name VARCHAR(50),
	price FLOAT,
	quantity INT,
	address VARCHAR(MAX),
	phoneNumber VARCHAR(12),
	username VARCHAR(30),
	checkoutDate VARCHAR(25)
)
GO


ALTER TABLE Products
	ADD CONSTRAINT FK_BID_1 FOREIGN KEY (brand) REFERENCES Brands (bid),
	CONSTRAINT FK_PTID_1 FOREIGN KEY (type) REFERENCES ProductTypes (tid)


ALTER TABLE Carts
	ADD CONSTRAINT FK_UID_1 FOREIGN KEY (username) REFERENCES Accounts (username),
	CONSTRAINT FK_PID_1 FOREIGN KEY (productid) REFERENCES Products (id)
GO


ALTER TABLE Receipts
	ADD CONSTRAINT FK_UIDB_1 FOREIGN KEY (username) REFERENCES Accounts (username)
GO

ALTER TABLE Authorities
	ADD CONSTRAINT FK_UID FOREIGN KEY (username) REFERENCES Accounts (username),
	CONSTRAINT FK_RID FOREIGN KEY (rid) REFERENCES Roles (rid) 


INSERT INTO Accounts VALUES
('admin', '123', 'chitps17931@fpt.edu.vn', ''),
('user', '123', 'chitps17931@fpt.edu.vn', '')
GO

INSERT INTO Roles VALUES
('ADMIN', 'Admin'),
('USER', 'User'),
('GUEST', 'Guest')
GO

INSERT INTO Authorities (username, rid) VALUES
('admin', 'ADMIN'),
('user', 'USER')


INSERT INTO Brands (bid, name) VALUES
('Apple', 'Apple Inc.'),
('DELL', 'Dell Inc.'),
('ASUS', 'AsusTek Computer Inc.'),
('HP', 'Hewlett-Packard Development Company'),
('Acer', 'Acer Inc.'),
('Razer', 'Razer Inc.'),
('Microsoft', 'Microsoft'),
('MSI', 'Micro-Star International')
GO

INSERT INTO ProductTypes (tid, name) VALUES
('UtB', 'Ultrabook'),
('NoB', 'Notebook'),
('Gm', 'Gaming'),
('NeB', 'Netbook')
GO

INSERT INTO Products
	(brand, name, type, price, discount, quantity, image)
VALUES
('Apple', 'Macbook Air', 'UtB', 1099, 1, 1, 'mac.png'),
('DELL', 'Inspiron 5570', 'NoB', 800, 1, 1, 'xps.png'),
('Apple', 'Macbook Pro', 'UtB', 1399, 0.8, 1, 'mac-pro-16-1.png'),
('DELL', 'Latitude 5590', 'UtB', 1298, 0.8, 1, 'latitude-5590.png'),
('HP', '250 G6', 'NoB', 575, 0.9, 1, 'hp.png'),
('DELL', 'XPS 13', 'UtB', 1499, 1, 1, 'xps13.png')
GO

SELECT * FROM Carts
SELECT * FROM Receipts
SELECT * FROM Products
SELECT * FROM Authorities
SELECT * FROM Accounts