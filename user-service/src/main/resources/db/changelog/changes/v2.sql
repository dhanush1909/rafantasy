--liquibase formatted sql

--changeset user-service:2

create table if not exists refresh_token (
    id int not null AUTO_INCREMENT,
    user_id int not null,
    token varchar(255) not null,
    expires_in datetime not null,
    primary key (id),
    foreign key (user_id) references users (id)
    );









