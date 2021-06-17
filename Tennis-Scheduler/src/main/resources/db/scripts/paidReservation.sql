alter table reservation add column paid boolean;
alter table reservation alter column paid set default false;