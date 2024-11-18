-- CREATE DATABASE

CREATE DATABASE IF NOT EXISTS hotel;

USE hotel;

-- CREATE TABLES

CREATE TABLE AdditionalEquipmentPricingIncrease (
    additional_equipment_id INT PRIMARY KEY AUTO_INCREMENT,
    increase FLOAT NOT NULL,
    type VARCHAR(255) NOT NULL
);

CREATE TABLE AdditionalEquipment(
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE Client (
    id INT PRIMARY KEY AUTO_INCREMENT,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    verified BOOLEAN NOT NULL,
    disabled BOOLEAN NOT NULL
);

CREATE TABLE EquipmentPricingIncrease (
    equipment_id INT PRIMARY KEY,
    increase FLOAT NOT NULL,
    type VARCHAR(255) NOT NULL
);

CREATE TABLE Equipment(
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE Room(
    id INT PRIMARY KEY AUTO_INCREMENT,
    roomSize INT NOT NULL,
    basePrice FLOAT NOT NULL,
    capacity INT NOT NULL,
    roomNumber INT NOT NULL,
    name VARCHAR(255) DEFAULT '',
    description TEXT
);

CREATE TABLE ReservationPricing(
    reservation_id INT PRIMARY KEY,
    price FLOAT NOT NULL
);

CREATE TABLE Discount(
    id INT PRIMARY KEY AUTO_INCREMENT,
    decrease FLOAT NOT NULL,
    discountType VARCHAR(255) NOT NULL
);

CREATE TABLE RoomDiscount(
    room_id INT NOT NULL,
    discount_id INT NOT NULL,
    FOREIGN KEY (room_id) REFERENCES Room(id),
    FOREIGN KEY (discount_id) REFERENCES Discount(id)
);

CREATE TABLE RoomBlockade(
    id INT PRIMARY KEY AUTO_INCREMENT,
    room_id INT NOT NULL,
    date_from TIMESTAMP NOT NULL,
    date_to TIMESTAMP,
    FOREIGN KEY (room_id) REFERENCES Room(id)
);

CREATE TABLE Reservation(
    id INT PRIMARY KEY AUTO_INCREMENT,
    client_id INT NOT NULL,
    room_id INT NOT NULL,
    checkin_date TIMESTAMP NOT NULL,
    checkout_date TIMESTAMP NOT NULL,
    FOREIGN KEY (client_id) REFERENCES Client(id),
    FOREIGN KEY (room_id) REFERENCES Room(id)
);

CREATE TABLE EquipmentInRoom(
    equipment_id INT NOT NULL,
    room_id INT NOT NULL,
    FOREIGN KEY (equipment_id) REFERENCES Equipment(id),
    FOREIGN KEY (room_id) REFERENCES Room(id)
);

CREATE TABLE AdditionalEquipmentForReservation(
    additional_equipment_id INT NOT NULL,
    reservation_id INT NOT NULL,
    FOREIGN KEY (additional_equipment_id) REFERENCES AdditionalEquipment(id),
    FOREIGN KEY (reservation_id) REFERENCES Reservation(id)
);

-- CREATE USERS

CREATE USER 'client'@'%' IDENTIFIED BY 'client_password';
CREATE USER 'owner'@'%'  IDENTIFIED BY 'owner_password';


-- GRANT PRIVILIGES FOR CLIENT

GRANT SELECT ON hotel.*                                 TO 'client'@'%';
GRANT INSERT ON hotel.Reservation                       TO 'client'@'%';
GRANT INSERT ON hotel.ReservationPricing                TO 'client'@'%';
GRANT INSERT ON hotel.AdditionalEquipmentForReservation TO 'client'@'%';
GRANT INSERT ON hotel.Client                            TO 'client'@'%';
GRANT UPDATE ON hotel.Client                            TO 'client'@'%';

-- GRANT PRIVILIGES FOR OWNER

GRANT SELECT, UPDATE ON hotel.*                                  TO 'owner'@'%';
GRANT INSERT         ON hotel.AdditionalEquipmentPricingIncrease TO 'owner'@'%';
GRANT INSERT         ON hotel.AdditionalEquipment                TO 'owner'@'%';
GRANT INSERT         ON hotel.EquipmentPricingIncrease           TO 'owner'@'%';
GRANT INSERT         ON hotel.Equipment                          TO 'owner'@'%';
GRANT INSERT         ON hotel.Room                               TO 'owner'@'%';
GRANT INSERT         ON hotel.Discount                           TO 'owner'@'%';
GRANT INSERT         ON hotel.RoomDiscount                       TO 'owner'@'%';
GRANT INSERT         ON hotel.EquipmentInRoom                    TO 'owner'@'%';
GRANT INSERT         ON hotel.AdditionalEquipmentForReservation  TO 'owner'@'%';
GRANT INSERT         ON hotel.RoomBlockade                       TO 'owner'@'%';
GRANT DELETE         ON hotel.EquipmentInRoom                    TO 'owner'@'%';
GRANT DELETE         ON hotel.AdditionalEquipmentForReservation  TO 'owner'@'%';
GRANT DELETE         ON hotel.RoomBlockade                       TO 'owner'@'%';

-- SAVE PRIVILIGES

FLUSH PRIVILEGES;

-- ADD PROCEDURES

DELIMITER //
CREATE PROCEDURE insert_equipment (IN name VARCHAR(255), IN price_increase FLOAT, IN type VARCHAR(255))
BEGIN
    DECLARE newest_id INT;

    SELECT MAX(id) INTO newest_id FROM Equipment;
    IF newest_id IS NULL THEN
        SET newest_id = 1;
    ELSE
        SET newest_id = newest_id + 1;
    END IF;

    INSERT INTO Equipment VALUES (newest_id, name);
    INSERT INTO EquipmentPricingIncrease VALUES (newest_id, price_increase, type);
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE insert_additional_equipment (IN name VARCHAR(255), IN price_increase FLOAT, IN type VARCHAR(255))
BEGIN
    DECLARE newest_id INT;

    SELECT MAX(id) INTO newest_id FROM AdditionalEquipment;
    IF newest_id IS NULL THEN
        SET newest_id = 1;
    ELSE
        SET newest_id = newest_id + 1;
    END IF;

    INSERT INTO AdditionalEquipment VALUES (newest_id, name);
    INSERT INTO AdditionalEquipmentPricingIncrease VALUES (newest_id, price_increase, type);
END //
DELIMITER ;

