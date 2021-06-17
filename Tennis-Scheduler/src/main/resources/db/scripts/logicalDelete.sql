alter table tennis_player add column deleted boolean default false;
alter table tennis_court add column deleted boolean default false;
alter table time_slot add column deleted boolean default false;
alter table reservation add column deleted boolean default false;