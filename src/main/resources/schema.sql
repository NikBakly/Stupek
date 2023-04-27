create table if not exists users
(
    id       bigint primary key,
    login    varchar not null unique,
    password varchar not null,
    email    varchar not null unique,
    balance  float,
    role     varchar DEFAULT 'USER',
    CONSTRAINT check_role_value check ( role = 'USER' OR role = 'ADMIN' OR role = 'DEVELOPER')
);

create table if not exists courses
(
    id              bigint primary key,
    name            varchar   not null,
    description     varchar,
    course_material varchar   not null,
    price           float,
    last_update     timestamp not null,
    developer_id    bigint,
    CONSTRAINT fk_developer_id_user_id foreign key (developer_id) REFERENCES users (id)
);