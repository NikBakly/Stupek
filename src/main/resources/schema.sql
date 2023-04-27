create table if not exists users
(
    id       bigint primary key,
    login    varchar not null unique,
    password varchar not null,
    email    varchar not null unique,
    balance  real,
    role     varchar DEFAULT 'USER',
    CONSTRAINT fk_user_id foreign key (id) references users_courses (user_id),
    CONSTRAINT check_role_value check ( role = 'USER' OR role = 'ADMIN' OR role = 'DEVELOPER')
);

create table if not exists users_courses
(
    user_id   bigint primary key,
    course_id bigint primary key,
    foreign key (user_id) REFERENCES users (id),
    foreign key (course_id) REFERENCES courses (id)
);

create table if not exists courses
(
    id              bigint primary key,
    name            varchar   not null,
    description     varchar,
    course_material varchar   not null,
    price           real,
    last_update     timestamp not null,
    developer_id    bigint,
    CONSTRAINT fk_course_id foreign key (id) REFERENCES users_courses (course_id),
    CONSTRAINT fk_developer_id_user_id foreign key (developer_id) REFERENCES users (id)
);