drop schema if exists cruiseships;
create schema cruiseships;
use cruiseships;

create table users(
                      id int primary key auto_increment,
                      uname varchar(50) unique,
                      ufirstname varchar(50),
                      ulastname varchar(50),
                      uemail varchar(50),
                      upassword varchar(150),
                      uscore decimal,
                      roles int
);
desc users;

create table cruise(
                       id int primary key auto_increment,
                       price decimal,
                       start_cruise date,
                       end_cruise date,
                       cruise_name varchar(50) unique,
                       ship_id int,
                       places varchar(50),
                       image varchar(50),
                       duration int,
                       statuse int
);

drop table ships;
create table ships(
                      id int primary key auto_increment,
                      ship_name varchar(50) unique,
                      passenger_capacity int,
                      route varchar(50),
                      ports_number int)
;

INSERT INTO ships(ship_name, passenger_capacity, route, ports_number)
VALUES
    ('Symphony of the Seas','120','Rim to Barcelona','6'),
    ('Harmony of the Seas','140','Caribbean islands','8'),
    ('Oasis of the Seas','130','Persian Gulf','6'),
    ('AIDAnova of the Seas','125','Northern Europe','7'),
    ('Allure of the seas','135','Asia and the Far East','9')
;

create table orders(
                       order_id int primary key auto_increment,
                       cruise_id int,
                       user_id int,
                       order_quantity int,
                       statusId int,
                       order_date varchar(200),
                       payment_amount decimal,
                       images varchar(50),
                       cruise_name varchar(50)
);
