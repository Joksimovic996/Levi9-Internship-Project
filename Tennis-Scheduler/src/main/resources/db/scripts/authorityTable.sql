drop table if exists authority cascade;
drop sequence if exists authority_seq;

create table authority
(
    id int not null,
    name varchar(50)
);

alter table authority add constraint pk_authority primary key (id);
create index idxpk_authority on authority (id);
create sequence authority_seq increment 1;
ALTER TABLE authority ALTER COLUMN id SET DEFAULT nextval('authority_seq');