create sequence seq_blog201812045 increment by 1 start with 1;

create table blog201812045
(
    id  number(11)  not null primary key,
    name varchar2(30)   not null ,
    email varchar2(30) not null unique,
    title varchar2(50),
    content varchar2(100)
);
drop sequence seq_blog201812045;
drop table blog201812045;

insert into blog201812045 values (seq_blog201812045.nextval,'경성구','tjdrn990423@naver.com','title','content');
insert into blog201812045 values (seq_blog201812045.nextval,'user1','abc1@naver.com','title1','content1');
insert into blog201812045 values (seq_blog201812045.nextval,'user2','abd2@naver.com','title2','content2');
insert into blog201812045 values (seq_blog201812045.nextval,'user3','abc3@naver.com','title3','content3');

select * from blog201812045;