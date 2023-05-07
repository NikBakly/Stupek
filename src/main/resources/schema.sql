create table if not exists persons
(
    id          bigint primary key,
    login       varchar   not null unique,
    password    varchar   not null,
    email       varchar   not null unique,
    balance     real,
    role        varchar DEFAULT 'USER',
    status      varchar DEFAULT 'ACTIVE',
    last_update timestamp not null,
    CONSTRAINT check_role_value check ( role = 'USER' OR role = 'ADMIN' OR role = 'DEVELOPER'),
    CONSTRAINT check_status_value check (status = 'ACTIVE' OR status = 'BANNED' )
);

create table if not exists courses
(
    id              bigint primary key,
    name            varchar   not null,
    description     varchar,
    course_material varchar   not null,
    price           real,
    last_update     timestamp not null,
    is_open         boolean DEFAULT TRUE,
    developer_id    bigint,
    CONSTRAINT fk_developer_id_user_id foreign key (developer_id) REFERENCES persons (id)
);

create table if not exists users_courses
(
    user_id   bigint,
    course_id bigint,
    primary key (user_id, course_id),
    foreign key (user_id) REFERENCES persons (id) ON DELETE CASCADE,
    foreign key (course_id) REFERENCES courses (id) ON DELETE CASCADE
);
