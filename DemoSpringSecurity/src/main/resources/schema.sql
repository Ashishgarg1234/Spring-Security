create table users (username varchar2(50),password varchar2(50),enabled boolean);

create table authorities (username varchar2(50),authority varchar2(50),foreign key (username) references users(username));