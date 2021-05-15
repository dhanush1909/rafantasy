--liquibase formatted sql

--changeset user-service:1

create table if not exists users (
	id int not null AUTO_INCREMENT,
	first_name varchar(255) not null,
	last_name varchar(255) not null,
	email varchar(255) not null,
	username varchar(255) not null,
	password varchar(255) not null,
	date_of_birth date not null,
	updated_at datetime(6) null,
	token varchar(255) null,
	is_active bit null,
	is_non_locked bit null,
	primary key (id),
	unique (username)
);

create table if not exists role (
  id int NOT NULL AUTO_INCREMENT,
  role_type varchar(255) DEFAULT NULL,
  primary key (id)
);

create table if not exists user_roles (
  user_id int NOT NULL,
  role_id int NOT NULL,
  PRIMARY KEY (user_id,role_id),
  foreign key (user_id) references users (id),
  foreign key (role_id) references role (id)
);

INSERT INTO role(role_type) values ('ROLE_ADMIN');
INSERT INTO role(role_type) values ('ROLE_USER');
INSERT INTO users(id, date_of_birth, email, username, first_name, last_name, password, is_active, is_non_locked) values (1,'1996-01-01','admin@rf.com','admin','admin','admin','$2y$12$NQBrrgiV2AXg6tMhQOzkQ.na743eQ4bifo8tOF9lrvOk5Bm5WEaiu', true, true);
INSERT INTO user_roles(user_id, role_id) values (1,1);
INSERT INTO user_roles(user_id, role_id) values (1,2);

create table if not exists image (
	id int not null AUTO_INCREMENT,
	user_id int not null,
	image_bytes blob,
	primary key (id)
);