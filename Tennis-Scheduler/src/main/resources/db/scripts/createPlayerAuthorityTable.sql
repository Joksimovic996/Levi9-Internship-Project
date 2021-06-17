drop table if exists player_authority cascade;

create table player_authority
(
    player_id int not null,
    authority_id int not null
);

alter table player_authority add constraint pk_player_authority primary key (player_id,authority_id);
alter table player_authority add constraint fk_player_authority_p foreign key (player_id) references tennis_player (id);
alter table player_authority add constraint fk_player_authority_k foreign key (authority_id) references authority (id);


insert into player_authority (player_id,authority_id) values (1,1);
insert into player_authority (player_id,authority_id) values (2,2);