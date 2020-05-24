alter table question alter column id bigint auto_increment;
alter table `user` alter column id bigint auto_increment;
alter table comment alter column commentator bigint default not null;