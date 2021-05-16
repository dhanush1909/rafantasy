--liquibase formatted sql

--changeset acyuta1:001-initial-user-and-security-scripts

create table users (
	id bigserial not null,
	first_name varchar(255) not null,
	last_name varchar(255) not null,
	email varchar(255) not null,
	username varchar(255) not null,
	password varchar(255) not null,
	date_of_birth date not null,
	updated_at timestamp null,
	token varchar(255) null,
	is_active boolean null,
	is_non_locked boolean null,
	constraint users_pkey primary key (id),
	constraint users_username_unique unique (username)
);

create table if not exists role (
  id bigserial NOT NULL,
  role_type varchar(255) DEFAULT NULL,
  constraint role_pkey primary key (id)
);

create table if not exists user_roles (
  user_id bigint NOT NULL,
  role_id bigint NOT NULL,
  constraint user_roles_pkey primary key (user_id,role_id),
  constraint fk_user_roles_users foreign key (user_id) references users(id),
  constraint fk_user_roles_role foreign key (role_id) references role(id)
);

INSERT INTO role(role_type) values ('ROLE_ADMIN');
INSERT INTO role(role_type) values ('ROLE_USER');
INSERT INTO users(id, date_of_birth, email, username, first_name, last_name, password, is_active, is_non_locked) values (1,'1996-01-01','admin@rf.com','admin','admin','admin','$2y$12$NQBrrgiV2AXg6tMhQOzkQ.na743eQ4bifo8tOF9lrvOk5Bm5WEaiu', true, true);
INSERT INTO user_roles(user_id, role_id) values (1,1);
INSERT INTO user_roles(user_id, role_id) values (1,2);

create table if not exists refresh_token (
    id bigserial not null,
    user_id bigint not null,
    token varchar(255) not null,
    expires_in timestamp not null,
    constraint refresh_token_pkey primary key (id),
    constraint fk_refresh_token_users foreign key (user_id) references users(id)
    );