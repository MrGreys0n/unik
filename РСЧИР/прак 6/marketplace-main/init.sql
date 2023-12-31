CREATE DATABASE marketplace;



CREATE TABLE IF NOT EXISTS books(
    id int PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
    author VARCHAR(50) NOT NULL,
    number_of_seller int,
    type_of_product VARCHAR(30),
    price int,
    name VARCHAR(50) NOT NULL
);

CREATE TABLE if not exists clients (
    id int PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
    name VARCHAR(50) NOT NULL,
    email text NOT NULL,
    login VARCHAR(30) NOT NULL,
    password text NOT NULL
);

CREATE TABLE if not exists phones (
    id int PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
    producer VARCHAR(50) NOT NULL,
    battery_capacity int,
    number_of_seller int,
    type_of_product VARCHAR(30) NOT NULL,
    price int,
    name VARCHAR(50) NOT NULL
);

CREATE TABLE if not exists washingmachines (
    id int PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
    producer VARCHAR(50) NOT NULL,
    tank_capacity int,
    number_of_seller int,
    type_of_product VARCHAR(30) NOT NULL,
    price int,
    name VARCHAR(50) NOT NULL
);