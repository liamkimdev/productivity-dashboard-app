CREATE SCHEMA test;

CREATE TABLE test."user" (
	user_id serial primary key,
	username varchar(50) not null unique,
    password_hash varchar(2048) not null,
    enabled boolean default(false),
	first_name varchar(255) not null,
    last_name varchar(255) not null
);

CREATE TABLE test.app_role (
    app_role_id serial primary key,
    "name" varchar(50) not null unique
);

CREATE TABLE test.user_role (
    user_id int not null,
    app_role_id int not null,
    constraint pk_user_role
        primary key (user_id, app_role_id),
    constraint fk_user_role_user_id
        foreign key (user_id)
        references test."user" (user_id),
	constraint fk_user_role_role_id
        foreign key (app_role_id)
        references test.app_role(app_role_id)
);

insert into test.app_role ("name") values
    ('USER'),
    ('ADMIN');

CREATE TABLE test.dashboard (
	dashboard_id serial primary key,
	dashboard_name varchar(255) not null,
	user_id int not null,
	constraint fk_dashboard_user
		foreign key (user_id)
	    references test."user" (user_id)
);

CREATE TABLE test.note_widget (
	note_widget_id serial primary key,
    title varchar(255) default null,
	dashboard_id int not null,
	constraint fk_note_widget_dashboard
		foreign key (dashboard_id)
		references test.dashboard (dashboard_id)
);

CREATE TABLE test.note (
	note_id serial primary key,
	title VARCHAR(255) default null,
	description VARCHAR(10000) default null,
	"date" timestamptz not null,
    note_widget_id int not null,
    constraint fk_note_note_widget
		foreign key (note_widget_id)
        references test.note_widget (note_widget_id)
);

CREATE OR REPLACE PROCEDURE test.set_known_good_state()
AS $$
BEGIN

    DELETE FROM test.note;
    ALTER SEQUENCE test.note_note_id_seq RESTART WITH 1;

    DELETE FROM test.note_widget;
    ALTER SEQUENCE test.note_widget_note_widget_id_seq RESTART WITH 1;

    DELETE FROM test.dashboard;
    ALTER SEQUENCE test.dashboard_dashboard_id_seq RESTART WITH 1;

    INSERT INTO test."user"
        (user_id, username, password_hash, enabled, first_name, last_name)
    VALUES
        (1, 'janeDoe', 'password', true, 'Jane', 'Doe');

    INSERT INTO test.dashboard
        (dashboard_id, dashboard_name, user_id)
    VALUES
        (1, 'Test Dashboard One', 1); -- insert note widget data

    INSERT INTO test.note_widget
        (note_widget_id, title, dashboard_id)
    VALUES
        (1, 'Test Note Widget One', 1),
        (2, 'Test Note Widget Two', 1); -- insert note data

    INSERT INTO test.note
        (note_id, title, description, "date", note_widget_id)
    VALUES
        (1, 'Title Test One', 'Description test one', '2023-03-03', 1),
        (2, 'Title Test Two', 'Description test two. Today I ate an apple. Goodbye!', '2023-03-03', 1),
        (3, 'Title Test Three', 'Description test three', '2023-03-03', 1);

 END;
$$ LANGUAGE plpgsql;