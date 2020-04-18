INSERT INTO EVENT (id,age_limit,capacity,happening_time,location,name,owner_ID,save_user_data)
VALUES ( 1,18,50000,'2022-03-14','Saku Suurhall','Raju Reede',33,1 );

INSERT INTO EVENT (id,age_limit,capacity,happening_time,location,name,owner_ID,save_user_data)
VALUES ( 2,16,20000,'2022-04-24','Lauluväljak','RockEvent2022',14,0 );


INSERT INTO EVENT_TICKET (id,name,price,eventid,place,ticket_type)
VALUES (1,'tavapilet',49.99,1,'main',1);

INSERT INTO EVENT_TICKET (id,name,price,eventid,place,ticket_type)
VALUES (2,'noortepilet',29.99,1,'main',2);

INSERT INTO EVENT_TICKET (id,name,price,eventid,place,ticket_type)
VALUES (3,'seeniorpilet',19.99,1,'main',3);


INSERT INTO EVENT_TICKET (id,name,price,eventid,place,ticket_type)
VALUES (4,'early bird',39.99,1,'main',4);



INSERT INTO EVENT_TICKET (id,name,price,eventid,place,ticket_type)
VALUES (5,'tavapilet',109.99,2,'main',1);

INSERT INTO EVENT_TICKET (id,name,price,eventid,place,ticket_type)
VALUES (6,'noortepilet',89.99,2,'main',2);

INSERT INTO EVENT_TICKET (id,name,price,eventid,place,ticket_type)
VALUES (7,'seeniorpilet',59.99,2,'main',3);


INSERT INTO EVENT_TICKET (id,name,price,eventid,place,ticket_type)
VALUES (8,'early bird',79.99,2,'main',4);

INSERT INTO Owner_table (id,name,email,iban,event_id)
VALUES (10,'Peeter Moos','peeter.moos@gmail.com','ee2345234',234);

