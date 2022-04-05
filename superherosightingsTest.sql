DROP DATABASE IF EXISTS SuperHeroSightingsTest;

CREATE DATABASE SuperHeroSightingsTest;

USE SuperHeroSightingsTest;

CREATE TABLE SuperPowers (
	SuperPowersId INT PRIMARY KEY auto_increment,
	`name` varchar(50)
);

CREATE TABLE Locations (
	LocationsId INT PRIMARY KEY auto_increment,
    `Name` varchar(50) NOT NULL,
    `Description` varchar(150) NOT NULL,
    `Address` varchar(50) NOT NULL,
    `Longitude` decimal(9,6) NOT NULL,
    `Latitude` decimal(9,6) NOT NULL
);

CREATE TABLE Organizations (
	OrganizationsId INT PRIMARY KEY auto_increment,
    `Name` varchar(50),
    `Description` varchar(50),
    `Address` varchar(50),
    `Phone` varchar(15)
);

CREATE TABLE Heroes (
	HeroesId INT PRIMARY KEY auto_increment,
    `Name` varchar(50) NOT NULL,
    `Description` varchar(50) NOT NULL,
    SuperPowersId INT,
    
    foreign key (SuperPowersId) references SuperPowers(SuperPowersId)
);

CREATE TABLE Sightings (
	SightingsId INT PRIMARY KEY auto_increment,
	HeroesId INT,
    LocationsId INT,
	`Date` date,
    
    foreign key (HeroesId) references Heroes(HeroesId),
    foreign key (LocationsId) references Locations(LocationsId)
    
);

CREATE TABLE Heroes_Organizations (
	HeroesId INT,
    OrganizationsId INT,
    primary key(HeroesId, OrganizationsId),
    foreign key (HeroesId) references Heroes(HeroesId),
    foreign key (OrganizationsId) references Organizations(OrganizationsId)
    
);