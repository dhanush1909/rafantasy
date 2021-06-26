--liquibase formatted sql

--changeset acyuta1:002-initial-user-and-security-scripts

create table tournaments (
	id bigserial not null,
	name varchar(255) not null,
	month1 int,
	month2 int,
	month3 int,
	constraint tournaments_pkey primary key (id)
);