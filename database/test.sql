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

CREATE TABLE note (
	note_id serial primary key,
	title VARCHAR(255) default null,
	description VARCHAR(10000) default null,
	"date" timestamptz not null,
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

delimiter //
create procedure set_known_good_state()
begin

    delete from note;
    alter table note auto_increment = 1;

    delete from note_widget;
    alter table note_widget auto_increment = 1;

    delete from dashboard;
    alter table dashboard auto_increment = 1;

--    delete from "user";
--    alter table "user" auto_increment = 1;

-- passwords are set to "P@ssw0rd!"

    insert into user
        (user_id, username, password_hash, enabled, first_name, last_name)
    values
        (1, "janeDoe", );

    insert into dashboard
        (dashboard_id, dashboard_name, user_id)
    values
        (1, "Test Dashboard One", 1);

    insert into note_widget
        (note_widget_id, title, dashboard_id)
    values
         (1, "Test Note Widget One", 1);
         (1, "Test Note Widget Two", 2);

    insert into note
        (note_id, title, description, "date", note_widget_id)
    values
        (1, "Title Test One", "Description test one", "2023-03-03", 1);
        (2, "Title Test Two", "Description test two. Today I ate an apple. Goodbye!", "2023-03-03", 1);
        (3, "Title Test Three", "Description test three", "2023-03-03", 1);

end //

delimiter ;
