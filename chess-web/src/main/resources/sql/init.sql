create table if not exists chess_info
(
    id integer
        constraint chess_info_pk
            primary key autoincrement,
    game_id int,
    user_id int,
    complete int default 0,
    winner varchar(10),
    add_time timestamp default (datetime('now','localtime')),
    upd_time timestamp default (datetime('now','localtime')),
    isactive int default 1
);

create index if not exists game_id_index
    on chess_info (game_id);

create table if not exists chess_location
(
    id integer
        constraint chess_location_pk
            primary key autoincrement,
    x int,
    y int,
    role int,
    game_id int,
    add_time timestamp default (datetime('now', 'localtime')),
    upd_time timestamp default (datetime('now', 'localtime')),
    isactive int default 1
);

create index if not exists location_game_id_index
    on chess_location (role, game_id);

