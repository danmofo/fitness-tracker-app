create table ft.body_weight (
    id int unsigned auto_increment,
    user_id int unsigned not null,
    logged_on date default (current_date),
    primary key(id),
    constraint body_weight_user_id_fk foreign key (user_id) references ft.user(id)
);

create table ft.workout (
    id int unsigned auto_increment,
    user_id int unsigned not null,
    notes text null,
    started_on datetime not null,
    finished_on datetime null,
    created_on timestamp default current_timestamp,
    updated_on timestamp default current_timestamp on update current_timestamp,
    primary key(id),
    constraint workout_user_id_fk foreign key (user_id) references ft.user(id)
);

create table ft.exercise (
    id int unsigned auto_increment,
    `name` text not null,
    brand text null,
    `type` varchar(20) not null default 'FREE_WEIGHT',
    primary key(id)
);

create table ft.workout_exercise (
    id int unsigned auto_increment,
    workout_id int unsigned not null,
    exercise_id int unsigned not null,
    `weight` int unsigned not null default 1,
    `sets` int not null default 1,
    reps int not null default 0,
    notes text null,
    equipment json default null,
    created_on timestamp default current_timestamp,
    updated_on timestamp default current_timestamp on update current_timestamp,
    primary key(id),
    constraint workout_id_fk foreign key (workout_id) references ft.workout(id),
    constraint exercise_id_fk foreign key (exercise_id) references ft.exercise(id)
);