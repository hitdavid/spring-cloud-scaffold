create table Role
(
	id bigint auto_increment
		primary key,
	name varchar(30) null,
	status int not null,
	created datetime null,
	updated datetime null,
	constraint User_uid_uindex
		unique (id)
)
;

create table User
(
	name varchar(30) null,
	password varchar(50) not null,
	phone varchar(20) not null,
	salt varchar(50) null,
	status int not null,
	created datetime null,
	updated datetime null,
	id bigint auto_increment
		primary key,
	constraint User_uid_uindex
		unique (id),
	constraint User_phone_uindex
		unique (phone)
)
;

create table User_Role
(
	id bigint auto_increment
		primary key,
	uid bigint not null,
	rid bigint not null,
	constraint User_Role_id_uindex
		unique (id)
)
;

create index User_Role_rid_index
	on User_Role (rid)
;

create index User_Role_uid_index
	on User_Role (uid)
;

