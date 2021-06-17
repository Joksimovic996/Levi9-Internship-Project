drop table if exists tennis_player cascade;
drop table if exists tennis_court cascade;
drop table if exists reservation cascade;
drop table if exists time_slot cascade;

create table tennis_player
(
    id integer not null,
    first_name varchar(30),
    last_name varchar(30),
    email_address varchar(20) not null,
    date_of_birth date
);

create table tennis_court
(
    id int not null,
    court_type varchar(50) not null,
    tennis_court_name varchar(50),
    price_per_minute decimal not null
);

create table reservation
(
    id int not null,
    tennis_player_id int not null,
    reservation_date timestamp,
    payment_type varchar(50) not null,
    price decimal not null
);

create table time_slot
(
    id int not null,
    start_date_and_time timestamp,
    end_date_and_time timestamp,
    tennis_court_id int not null,
    reservation_id int not null
);

alter table tennis_player add constraint pk_player primary key (id);
alter table tennis_court add constraint pk_tennis_court primary key (id);
alter table reservation add constraint pk_reservation primary key (id);
alter table time_slot add constraint pk_time_slot primary key (id);

alter table reservation add constraint fk_reservation_player foreign key (tennis_player_id) references tennis_player (id);
alter table time_slot add constraint fk_time_slot_court foreign key (tennis_court_id) references tennis_court (id);
alter table time_slot add constraint fk_time_slot_reservation foreign key (reservation_id) references reservation (id);

alter table tennis_player add constraint uq_tennis_player_email unique (email_address);
alter table tennis_court add constraint uq_tennis_court_name unique (tennis_court_name);

create index idxpk_tennis_player on tennis_player (id);
create index idxpk_tennis_court on tennis_court (id);
create index idxpk_reservation on reservation (id);
create index idxpk_time_slot on time_slot (id);

create index idxfk_reservation_tennis_playe on reservation (tennis_player_id);
create index idxfk_time_slot_court on time_slot (tennis_court_id);
create index idxfk_time_slot_reservation on time_slot (reservation_id);

drop sequence if exists tennis_player_seq;
drop sequence if exists tennis_court_seq;
drop sequence if exists reservation_seq;
drop sequence if exists time_slot_seq;

create sequence tennis_player_seq increment 1;
create sequence tennis_court_seq increment 1;
create sequence reservation_seq increment 1;
create sequence time_slot_seq increment 1;

ALTER TABLE tennis_player ALTER COLUMN id SET DEFAULT nextval('tennis_player_seq');
ALTER TABLE tennis_court ALTER COLUMN id SET DEFAULT nextval('tennis_court_seq');
ALTER TABLE reservation ALTER COLUMN id SET DEFAULT nextval('reservation_seq');
ALTER TABLE time_slot ALTER COLUMN id SET DEFAULT nextval('time_slot_seq');