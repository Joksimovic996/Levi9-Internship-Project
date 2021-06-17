insert into tennis_player (date_of_birth,email_address,last_name,first_name,username,password,last_password_reset_date,deleted) values
('1987-05-22','goatg@gmail.com', 'Djokovic','Novak','nole','$2a$10$zXbA7dkM7YGL5ch89ykM6uZUhqkV7ZqLg7fVvEu02A7JntjB/8Nfa',current_timestamp,false);
insert into tennis_player (date_of_birth,email_address,last_name,first_name,username,password,last_password_reset_date,deleted) values
('1986-06-03','notgoat1@gmail.com', 'Nadal','Rafael','nadal','$2a$10$S9/RDVGcW/cWroqIRLM5hOR2pnJDWQFqDUHa6/ZkJg8JacxpHHQ3.',current_timestamp,false);
insert into tennis_player (date_of_birth,email_address,last_name,first_name,username,password,last_password_reset_date,deleted) values
('1981-08-08','notgoat2@gmail.com', 'Federer','Roger','roger','$2a$10$6ue3ZCG0QLv.1N/Wceeyf.qo6fGhKn2Llliz5/uPCeg985vD67iH2',current_timestamp,false);

insert into tennis_court (tennis_court_name,court_type,price_per_minute) values ('Philippe-Chatrier','CLAY_WITH_ROOF',1.5);
insert into tennis_court (tennis_court_name,court_type,price_per_minute) values ('Centre Court','GRASS_WITHOUT_ROOF',1.75);
insert into tennis_court (tennis_court_name,court_type,price_per_minute) values ('Rod Laver Arena','HARD_WITH_ROOF',1);

insert into player_authority values (1,1);
insert into player_authority values (2,2);
insert into player_authority values (3,2);

