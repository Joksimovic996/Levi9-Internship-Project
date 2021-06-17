insert into tennis_player (date_of_birth,email_address,last_name,first_name) values
    ('1993-03-03','milan@gmail.com', 'Milankovic','Milan');
insert into tennis_player (date_of_birth,email_address,last_name,first_name) values
    ('1997-09-23','jovana@gmail.com', 'Jovanovic','Jovana');
insert into tennis_player (date_of_birth,email_address,last_name,first_name) values
    ('1990-12-12','petar@gmail.com', 'Petrovic','Petar');
insert into tennis_player (date_of_birth,email_address,last_name,first_name) values
    ('1989-06-13','danica@gmail.com', 'Danic','Danica');
insert into tennis_player (date_of_birth,email_address,last_name,first_name) values
    ('1991-09-14','nikola@gmail.com', 'Nikolic','Nikola');
insert into tennis_player (date_of_birth,email_address,last_name,first_name) values
    ('2000-07-25','ana@gmail.com', 'Antic','Ana');
insert into tennis_player (date_of_birth,email_address,last_name,first_name) values
    ('1999-06-23','sara@gmail.com', 'Saric','Sara');
insert into tennis_player (date_of_birth,email_address,last_name,first_name) values
    ('1981-01-11','viktor@gmail.com', 'Mitic','Vikor');

insert into reservation (reservation_date,tennis_player_id,payment_type,price) values (current_timestamp,1,'PAY_WITH_CREDIT_CARD',0);
insert into reservation (reservation_date,tennis_player_id,payment_type,price) values (current_timestamp,2,'PAY_WITH_CREDIT_CARD',0);
insert into reservation (reservation_date,tennis_player_id,payment_type,price) values (current_timestamp,3,'PAY_WITH_CREDIT_CARD',0);
insert into reservation (reservation_date,tennis_player_id,payment_type,price) values (current_timestamp,4,'PAY_WITH_CASH',0);
insert into reservation (reservation_date,tennis_player_id,payment_type,price) values (current_timestamp,5,'PAY_WITH_CASH',0);
insert into reservation (reservation_date,tennis_player_id,payment_type,price) values (current_timestamp,6,'PAY_WITH_CASH',0);
insert into reservation (reservation_date,tennis_player_id,payment_type,price) values (current_timestamp,7,'PAY_WITH_CREDIT_CARD',0);
insert into reservation (reservation_date,tennis_player_id,payment_type,price) values (current_timestamp,8,'PAY_WITH_CASH',0);

insert into tennis_court (tennis_court_name,court_type,price_per_minute) values ('ClayA1','CLAY_WITH_ROOF',0.5);
insert into tennis_court (tennis_court_name,court_type,price_per_minute) values ('ClayA17','CLAY_WITHOUT_ROOF',0.25);
insert into tennis_court (tennis_court_name,court_type,price_per_minute) values ('Grass6Y9','GRASS_WITH_ROOF',1);
insert into tennis_court (tennis_court_name,court_type,price_per_minute) values ('Grass012','GRASS_WITHOUT_ROOF',0.75);
insert into tennis_court (tennis_court_name,court_type,price_per_minute) values ('Court num8','HARD_WITH_ROOF',1.5);
insert into tennis_court (tennis_court_name,court_type,price_per_minute) values ('Court num9','HARD_WITHOUT_ROOF',1.25);
insert into tennis_court (tennis_court_name,court_type,price_per_minute) values ('Carpet6','CARPET_WITH_ROOF',2);
insert into tennis_court (tennis_court_name,court_type,price_per_minute) values ('CarpetE78','CARPET_WITHOUT_ROOF',0.76);

insert into time_slot (end_date_and_time,start_date_and_time,reservation_id,tennis_court_id)
values ('2021-06-02 14:00:00','2021-06-02 13:00:00',1,1);
insert into time_slot (end_date_and_time,start_date_and_time,reservation_id,tennis_court_id)
values ('2021-06-12 18:30:00','2021-06-12 19:30:00',1,2);
insert into time_slot (end_date_and_time,start_date_and_time,reservation_id,tennis_court_id)
values ('2021-06-02 19:00:00','2021-06-02 20:00:00',2,3);
insert into time_slot (end_date_and_time,start_date_and_time,reservation_id,tennis_court_id)
values ('2021-06-03 14:00:00','2021-06-03 13:00:00',3,4);
insert into time_slot (end_date_and_time,start_date_and_time,reservation_id,tennis_court_id)
values ('2021-06-05 20:00:00','2021-06-05 21:00:00',4,5);
insert into time_slot (end_date_and_time,start_date_and_time,reservation_id,tennis_court_id)
values ('2021-06-09 17:00:00','2021-06-09 18:20:00',5,6);
insert into time_slot (end_date_and_time,start_date_and_time,reservation_id,tennis_court_id)
values ('2021-06-02 19:00:00','2021-06-02 20:00:00',7,7);