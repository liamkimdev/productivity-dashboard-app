CREATE TABLE app_user (
	user_id serial primary key,
	username varchar(50) not null unique,
    password_hash varchar(2048) not null,
    enabled boolean default(false),
);

CREATE TABLE app_role (
    app_role_id serial primary key,
    "name" varchar(50) not null unique
);

CREATE TABLE app_user_role (
    user_id int not null,
    app_role_id int not null,
    constraint pk_user_role
        primary key (user_id, app_role_id),
    constraint fk_user_role_user_id
        foreign key (user_id)
        references app_user (user_id),
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
	    references app_user (user_id)
);

CREATE TABLE note_widget (
	note_widget_id serial primary key,
    title varchar(255) default null,
	dashboard_id int not null,
	constraint fk_note_widget_dashboard
		foreign key (dashboard_id)
		references dashboard (dashboard_id)
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

CREATE OR REPLACE PROCEDURE set_known_good_state()
AS $$
BEGIN

    DELETE FROM note;
    ALTER SEQUENCE note_note_id_seq RESTART WITH 1;

    DELETE FROM note_widget;
    ALTER SEQUENCE note_widget_note_widget_id_seq RESTART WITH 1;

    DELETE FROM dashboard;
    ALTER SEQUENCE dashboard_dashboard_id_seq RESTART WITH 1;

    DELETE FROM app_user;
    ALTER SEQUENCE app_user_user_id_seq RESTART WITH 1;


    INSERT INTO app_user
        (username, password_hash, enabled)
    VALUES
        ('therealjohnsmith', '$2a$10$ntB7CsRKQzuLoKY3rfoAQen5nNyiC/U60wBsWnnYrtQQi8Z3IZzQa', 1),
        ('wonderlander1865','$2a$10$ntB7CsRKQzuLoKY3rfoAQen5nNyiC/U60wBsWnnYrtQQi8Z3IZzQa', 1),
        ('neverinfilm','$2a$10$ntB7CsRKQzuLoKY3rfoAQen5nNyiC/U60wBsWnnYrtQQi8Z3IZzQa', 1),
        ('veryunf0rtunatevi','$2a$10$ntB7CsRKQzuLoKY3rfoAQen5nNyiC/U60wBsWnnYrtQQi8Z3IZzQa', 1),
        ('atticusfinchlaw','$2a$10$ntB7CsRKQzuLoKY3rfoAQen5nNyiC/U60wBsWnnYrtQQi8Z3IZzQa', 1),
        ('bigsleeper893','$2a$10$ntB7CsRKQzuLoKY3rfoAQen5nNyiC/U60wBsWnnYrtQQi8Z3IZzQa', 1),
        ('keeponrunning','$2a$10$ntB7CsRKQzuLoKY3rfoAQen5nNyiC/U60wBsWnnYrtQQi8Z3IZzQa', 1);

    INSERT INTO app_user_role
        (user_id, app_role_id)
    VALUES
        (1, 2),
        (2, 1),
        (3, 1),
        (4, 1),
        (5, 1),
        (6, 1),
        (7, 2);

    INSERT INTO dashboard
        (dashboard_name, user_id)
    VALUES
        ('Test Dashboard One', 1); -- insert note widget data

    INSERT INTO note_widget
        (title, dashboard_id)
    VALUES
        ('Test Note Widget One', 1),
        ('Test Note Widget Two', 1); -- insert note data

    INSERT INTO note
        (title, description, "date", note_widget_id)
    VALUES
        ('Title Test One', 'Description test one', '2023-03-03', 1),
        ('Title Test Two', 'Description test two. Today I ate an apple. Goodbye!', '2023-03-03', 1),
        ('Title Test Three', 'Description test three', '2023-03-04', 1);

 END;
$$ LANGUAGE plpgsql;