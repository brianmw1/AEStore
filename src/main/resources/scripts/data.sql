/*insert data into item table*/
INSERT INTO ITEM (bid, name, description, type, brand, price, quantity) VALUES ('b001', 'Little
Prince', 'a book for all ages', 'book', 'Penguin', 20, 100);
INSERT INTO ITEM (bid, name, description, type, brand, price, quantity) VALUES ('c001', 'iPad', 'a
device for personal use', 'computer', 'Apple', 500, 100);
INSERT INTO ITEM (bid, name, description, type, brand, price, quantity) VALUES ('d001', 'laptopm', 'a
device for personal use', 'computer', 'Apple', 1500, 100);

--/*populate the address table*/
--INSERT INTO Address (id, street, province, country, zip, phone) VALUES (1, '123 Yonge St', 'ON',
--'Canada', 'K1E 6T5' ,'647-123-4567');
--INSERT INTO Address (id, street, province, country, zip, phone) VALUES (2, '445 Avenue rd', 'ON',
--'Canada', 'M1C 6K5' ,'416-123-8569');
--INSERT INTO Address (id, street, province, country, zip, phone) VALUES (3, '789 Keele St.', 'ON',
--'Canada', 'K3C 9T5' ,'416-123-9568');
--
--/*
--* Inserting data for table 'PO'
--*/
--INSERT INTO PO (id, lname, fname, status, address) VALUES (1, 'John', 'White', 'PROCESSED', 1);
--INSERT INTO PO (id, lname, fname, status, address) VALUES (2, 'Peter', 'Black', 'DENIED', 2);
--INSERT INTO PO (id, lname, fname, status, address) VALUES (3, 'Andy', 'Green', 'ORDERED', 3);
--
--/*
----*Inserting data for table 'POitem'
--*/
--INSERT INTO POItem (id, bid, price) VALUES (1, 'b001', 20);
--INSERT INTO POItem (id, bid, price) VALUES (2, 'c001', 500);
--
--/*
--* data for table 'VisitEvent'
--*/
--INSERT INTO VisitEvent (ipaddress, day, bid, eventtype) VALUES ('1.23.4.5','12202022', 'b001',
--'VIEW');
--INSERT INTO VisitEvent (ipaddress, day, bid, eventtype) VALUES ('1.23.4.5', '12242022', 'b001',
--'CART');
--INSERT INTO VisitEvent (ipaddress,day, bid, eventtype) VALUES ('1.23.4.5', '12252022', 'b001',
--'PURCHASE');