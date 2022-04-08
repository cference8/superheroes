DROP DATABASE IF EXISTS SuperHeroSightings;

CREATE DATABASE SuperHeroSightings;

USE SuperHeroSightings;

CREATE TABLE SuperPowers (
	SuperPowerId INT PRIMARY KEY auto_increment,
	`name` varchar(50)
);

CREATE TABLE Locations (
	LocationId INT PRIMARY KEY auto_increment,
    `Name` varchar(50) NOT NULL,
    `Description` varchar(150) NOT NULL,
    `Address` varchar(50) NOT NULL,
    `Longitude` decimal(9,6) NOT NULL,
    `Latitude` decimal(9,6) NOT NULL
);

CREATE TABLE Organizations (
	OrganizationId INT PRIMARY KEY auto_increment,
    `Name` varchar(50),
    `Description` varchar(50),
    `Address` varchar(50),
    `Phone` varchar(15)
);

CREATE TABLE Heroes (
	HeroId INT PRIMARY KEY auto_increment,
    `Name` varchar(50) NOT NULL,
    `Description` varchar(50) NOT NULL,
    SuperPowerId INT,
    
    foreign key (SuperPowerId) references SuperPowers(SuperPowerId)
);

CREATE TABLE Sightings (
	SightingId INT PRIMARY KEY auto_increment,
	HeroId INT,
    LocationId INT,
	`Date` date,
    
    foreign key (HeroId) references Heroes(HeroId),
    foreign key (LocationId) references Locations(LocationId)
    
);

CREATE TABLE Organization_Hero (
    OrganizationId INT,
	HeroId INT,
    primary key(OrganizationId, HeroId),
	foreign key (OrganizationId) references Organizations(OrganizationId),
    foreign key (HeroId) references Heroes(HeroId)

    
);

INSERT INTO SuperPowers (`name`) VALUES
						('flying'),
                        ('invisibilty'),
                        ('mind-reading'),
                        ('time-travel');
                        
INSERT INTO Heroes (`name`, `description`, superpowerId) VALUES
				   ('Superman', 'red cape blue spandex', '1');
                   
INSERT INTO Organizations (`name`, `description`, `address`, `phone`) VALUES
						  ('The LAIR', 'dark cave', '404 mountain top', '999-987-6543');
                          

