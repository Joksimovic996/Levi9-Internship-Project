ALTER TABLE reservation
ALTER COLUMN id  TYPE bigint;

ALTER TABLE tennis_court
ALTER COLUMN id  TYPE bigint,
ALTER COLUMN tennis_court_name TYPE varchar(250);

ALTER TABLE tennis_player
ALTER COLUMN id  TYPE bigint,
ALTER COLUMN first_name TYPE varchar(250),
ALTER COLUMN last_name TYPE varchar(250),
ALTER COLUMN email_address TYPE varchar(250);

ALTER TABLE time_slot
ALTER COLUMN id TYPE bigint;