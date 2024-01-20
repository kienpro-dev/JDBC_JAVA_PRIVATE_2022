CREATE DATABASE GYM_MANAGEMENT
GO

USE GYM_MANAGEMENT
GO

CREATE TABLE Account(
	username varchar(25) primary key,
	password varchar(25),
	role varchar(10)
)
GO

CREATE TABLE Equipment(
	id varchar(25) primary key,
	name varchar(25),
	durability int
)
GO

CREATE TABLE Member(
	id varchar(25) primary key,
	name varchar(25),
	age int,
	dayStart date,
	dayEnd date,
	username varchar(25) FOREIGN KEY REFERENCES Account(username)
)
GO

CREATE TABLE PersonalTrainer(
	id varchar(25) primary key,
	name varchar(25),
	gender varchar(10),
	hirePrice int
)
GO

CREATE TABLE MemberPT(
	memId varchar(25) FOREIGN KEY REFERENCES Member(id),
	ptId varchar(25) FOREIGN KEY REFERENCES PersonalTrainer(id),
	timeLeft int
)
GO

CREATE TABLE RepairCenter(
	id varchar(25) primary key,
	name varchar(25),
	price int
)
GO

CREATE TABLE RepairEquipment(
	eId varchar(25) FOREIGN KEY REFERENCES Equipment(id),
	rcId varchar(25) FOREIGN KEY REFERENCES RepairCenter(id),
	timeLeft int
)
GO