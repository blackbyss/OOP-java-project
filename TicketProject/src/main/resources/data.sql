INSERT INTO EVENT (id,age_limit,capacity,happening_time,location,name,owner_ID,save_user_data)
VALUES ( 1,18,50000,'2022-03-14','Saku Suurhall','Raju Reede',10,1 );

INSERT INTO EVENT (id,age_limit,capacity,happening_time,location,name,owner_ID,save_user_data)
VALUES ( 2,16,20000,'2022-04-24','Lauluväljak','RockEvent2022',14,0 );


INSERT INTO EVENT_TICKET (id,name,price,eventid,ticket_type)
VALUES (1,'tavapilet',49.99,1,1);

INSERT INTO EVENT_TICKET (id,name,price,eventid,ticket_type)
VALUES (2,'noortepilet',29.99,1,2);

INSERT INTO EVENT_TICKET (id,name,price,eventid,ticket_type)
VALUES (3,'seeniorpilet',19.99,1,3);


INSERT INTO EVENT_TICKET (id,name,price,eventid,ticket_type)
VALUES (4,'early bird',39.99,1,4);



INSERT INTO EVENT_TICKET (id,name,price,eventid,ticket_type)
VALUES (5,'tavapilet',109.99,2,1);

INSERT INTO EVENT_TICKET (id,name,price,eventid,ticket_type)
VALUES (6,'noortepilet',89.99,2,2);

INSERT INTO EVENT_TICKET (id,name,price,eventid,ticket_type)
VALUES (7,'seeniorpilet',59.99,2,3);


INSERT INTO EVENT_TICKET (id,name,price,eventid,ticket_type)
VALUES (8,'early bird',79.99,2,4);

INSERT INTO Owner (id,name,email,iban,eventID,family_name, address, age, county, index,ticket_count,account_balance)
VALUES (10,'Peeter','peeter.moos@gmail.com','ee2345234',1,'Moos','address',18, 'county', '12345',1,125000);

INSERT INTO Owner (id,name,email,iban,eventID,family_name, address, age, county, index,ticket_count,account_balance)
VALUES (11,'Jaanus','jaanus.põld@gmail.com','ee555555',2,'jaanus','address',19, 'county', '67890',1,125000);

