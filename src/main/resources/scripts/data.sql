/*insert data into item table*/
INSERT INTO ITEM (bid, name, description, type, brand, price, quantity) VALUES ('b001', 'Little
Prince', 'a book for all ages', 'book', 'Penguin', 20, 100);
INSERT INTO ITEM (bid, name, description, type, brand, price, quantity) VALUES ('c001', 'iPad', 'a
device for personal use', 'computer', 'Apple', 500, 100);
INSERT INTO ITEM (bid, name, description, type, brand, price, quantity) VALUES ('d001', 'laptopm', 'a
device for personal use', 'computer', 'Apple', 1500, 100);

/*populate the address table*/
INSERT INTO Address (street, province, country, zip, phone) VALUES ('123 Yonge St', 'ON',
'Canada', 'K1E 6T5' ,'647-123-4567');
INSERT INTO Address (street, province, country, zip, phone) VALUES ('445 Avenue rd', 'ON',
'Canada', 'M1C 6K5' ,'416-123-8569');
INSERT INTO Address (street, province, country, zip, phone) VALUES ('789 Keele St.', 'ON',
'Canada', 'K3C 9T5' ,'416-123-9568');

/*populate user table */
INSERT INTO Users (username, fname, lname, password) VALUES ('bb123', 'Bob', 'Bobbbyy', 'password');

--/*
--* Inserting data for table 'PO'
--*/
INSERT INTO purchase_order (user_username, status, address_id) VALUES ('bb123', 'PROCESSED', 1);
INSERT INTO purchase_order (user_username, status, address_id) VALUES ('bb123', 'DENIED', 1);
INSERT INTO purchase_order (user_username, status, address_id) VALUES ('bb123', 'ORDERED', 1);
--
--/*
----*Inserting data for table 'POitem'
--*/
INSERT INTO purchase_order_item (po_id, item_bid, price) VALUES (1, 'b001', 20);
INSERT INTO purchase_order_item (po_id, item_bid, price) VALUES (2, 'c001', 500);
--
--/*
--* data for table 'VisitEvent'
--*/
INSERT INTO visit_event (id, ipaddress, day, item_bid, eventtype) VALUES (1, '1.23.4.5','12202022', 'b001',
'VIEW');
INSERT INTO visit_event (id, ipaddress, day, item_bid, eventtype) VALUES (2, '1.23.4.5', '12242022', 'b001',
'CART');
INSERT INTO visit_event (id, ipaddress,day, item_bid, eventtype) VALUES (3,'1.23.4.5', '12252022', 'b001',
'PURCHASE');