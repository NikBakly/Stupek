create table if not exists persons
(
    id          bigint GENERATED BY DEFAULT AS IDENTITY not null,
    login       varchar                                 not null unique,
    password    varchar                                 not null,
    email       varchar                                 not null unique,
    balance     real    DEFAULT 0,
    role        varchar DEFAULT 'USER',
    status      varchar DEFAULT 'ACTIVE',
    last_update timestamp                               not null,
    CONSTRAINT pk_person PRIMARY KEY (id),
    CONSTRAINT check_role_value check ( role = 'USER' OR role = 'ADMIN' OR role = 'DEVELOPER'),
    CONSTRAINT check_status_value check (status = 'ACTIVE' OR status = 'BANNED' )
);

create table if not exists courses
(
    id              bigint GENERATED BY DEFAULT AS IDENTITY not null,
    name            varchar                                 not null,
    description     varchar,
    course_material varchar                                 not null,
    last_update     timestamp                               not null,
    is_open         boolean DEFAULT TRUE,
    developer_id    bigint                                  not null,
    CONSTRAINT pk_course PRIMARY KEY (id),
    CONSTRAINT fk_developer_id_user_id foreign key (developer_id) REFERENCES persons (id)
);
