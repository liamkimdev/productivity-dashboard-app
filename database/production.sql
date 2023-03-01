CREATE TABLE "user" (
	user_id serial primary key,
	username varchar(50) not null unique,
    password_hash varchar(2048) not null,
    enabled boolean default(false),
	first_name varchar(255) not null,
    last_name varchar(255) not null
);

CREATE TABLE app_role (
    app_role_id serial primary key,
    "name" varchar(50) not null unique
);

CREATE TABLE user_role (
    user_id int not null,
    app_role_id int not null,
    constraint pk_user_role
        primary key (user_id, app_role_id),
    constraint fk_user_role_user_id
        foreign key (user_id)
        references "user" (user_id),
	constraint fk_user_role_role_id
        foreign key (app_role_id)
        references app_role(app_role_id)
);

insert into app_role ("name") values
    ('USER'),
    ('ADMIN');
	
CREATE TABLE dashboard (
	dashboard_id serial primary key,
	dashboard_name varchar(255) not null,
	user_id int not null,
	constraint fk_dashboard_user
		foreign key (user_id)
	references "user" (user_id)
);

--need to update the database!
CREATE TABLE note (
	note_id serial primary key, 
	title VARCHAR(255) default null,
	description VARCHAR(10000) default null,
	"timestamp" timestamptz not null,
    note_widget_id int not null,
    constraint fk_note_note_widget
		foreign key (note_widget_id)
        references note_widget (note_widget_id)
    
);

CREATE TABLE note_widget (
	note_widget_id serial primary key, 
    title varchar(255) default null,
	dashboard_id int not null,
	constraint fk_note_widget_dashboard
		foreign key (dashboard_id)
		references dashboard (dashboard_id)
);