create table ft.user (
    id int unsigned auto_increment,
    email varchar(50) not null,
    `password` varchar(255) not null,
    created_on timestamp default current_timestamp,
    updated_on timestamp default current_timestamp on update current_timestamp,
    primary key(id),
    unique (email)
);