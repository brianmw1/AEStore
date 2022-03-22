/*uncomment DROP TABLE if you want to remove the tables and reinitialize*/

--DROP TABLE VisitEvent;
--DROP TABLE POItem;
--DROP TABLE PO;
--DROP TABLE Address;
--DROP TABLE Item;

/* create Item table*/
CREATE TABLE Item(
--bid VARCHAR(20) NOT NULL PRIMARY KEY,
name VARCHAR(60) NOT NULL,
description VARCHAR(60) NOT NULL,
itype VARCHAR(60) NOT NULL,
brand VARCHAR(60) NOT NULL,
quantity INT NOT NULL,
price INT NOT NULL
);
/*create an addtess table*/
CREATE TABLE Address (
id INT NOT NULL,
street VARCHAR(100) NOT NULL,
province VARCHAR(20) NOT NULL,
country VARCHAR(20) NOT NULL,
zip VARCHAR(20) NOT NULL,
phone VARCHAR(20),
PRIMARY KEY(id)
);

/* create Purchase Order(PO) table */
/* Purchase Order
* lname: last name
* fname: first name
* id: purchase order id
* status: status of purchase: Processed, Denied, Ordered
*/
CREATE TABLE PO (
id INT NOT NULL,
lname VARCHAR(20) NOT NULL,
fname VARCHAR(20) NOT NULL,
status VARCHAR(20)NOT NULL,
address INT NOT NULL,
PRIMARY KEY(id),
FOREIGN KEY (address) REFERENCES Address (id)
);

/*create table Purchase Ordert Item, contains items on each order*/
CREATE TABLE POItem (
id INT NOT NULL,
bid VARCHAR(20) NOT NULL,
price INT NOT NULL,
PRIMARY KEY(id,bid),
FOREIGN KEY(id) REFERENCES PO(id),
FOREIGN KEY(bid) REFERENCES Item(bid)
);

/* visit to website
* ipaddress: varchar
* day: date
* bid: unique identifier of item
* eventtype: status of purchase
*/
CREATE TABLE VisitEvent (
ipaddress varchar (20) NOT NULL,
day varchar(8) NOT NULL,
bid varchar(20) not null,
eventtype varchar(20) NOT NULL,
FOREIGN KEY(bid) REFERENCES Item(bid)
);