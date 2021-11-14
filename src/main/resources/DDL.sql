CREATE SCHEMA `generaldb`;

create table participants_group
(
	id bigint not null
		primary key,
	creation_date datetime null,
	name varchar(255) null
);



create table participant
(
	id bigint not null
		primary key,
	address varchar(255) null,
	age int null,
	creation_date datetime null,
	mobile_number varchar(255) null,
	name varchar(255) null,
	nationalid varchar(255) null,
	group_id bigint null,
	constraint UK_nationalid
		unique (nationalid),
	constraint participant_group_id_fk
		foreign key (id) references participants_group (id)
);


create table tennis_match
(
	id bigint not null
		primary key,
	match_time datetime null,
	round int null,
	participant1_id bigint null,
	participant2_id bigint null,
	winner_id bigint null,
	constraint winner-participant-FK
		foreign key (winner_id) references participant (id),
	constraint participant1_FK
		foreign key (participant1_id) references participant (id),
	constraint participant2_FK
		foreign key (participant2_id) references participant (id)
);


