create table question
(
    id int auto_increment primary key,
    title varchar(50),
    description text,
    get_create bigint,
    get_modified bigint,
    creator int,
    comment_count int default 0,
    view_count int default 0,
    like_count int default 0,
    tag varchar(256)
);