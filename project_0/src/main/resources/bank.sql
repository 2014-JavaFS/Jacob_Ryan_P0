-- CRUD - Create, Read, Update, Delete (soft/hard)
-- Sub-languages
	/*
	 * DDL - data definition language (structure/tables)
	 * 		- Keywords: create, alter, drop
	 * DML - data manipulation language (affecting the records in our tables)
	 * 		- Keywords: update, insert and delete (JDBC has ONE method for all 3 of these)
	 * DQL - data query language
	 * 		- Keywords: select
	 * TCL - Transaction Control Language
	 * 		- ***NOTE*** Often you relinquish this control to DBeaver & JDBC
	 * 		- Keywords: commit, rollback, savepoint
	 * DCL - Data Control Language
	 * 		- ***NOTE*** Rare to use, but can be helpful with security
	 * 		- Keywords: GRANT & REVOKE
	 */

-- shortcut: ctrl+enter will run a single SQL statement
-- drops

drop table bank_Account;

drop table user_Account;


-- CREATE - Keywords: create, insert, alter
create table bank_Account(
    account_number integer primary key,
    user_number_ID integer unique,
    account_type integer,
    account_balance decimal(15, 2),
    account_type_name varchar(32)
);

create table user_Account(
    email varchar(320) unique,
    user_number_ID integer primary key,
    password varchar(64) not null,
    First_Name varchar(64),
    Last_Name varchar(64)
);

--data propagation for testing
insert into
    bank_Account(account_number, user_number_ID, account_type, account_balance)
values (00000001, 00000001, 1, 0.00);

insert into
    bank_Account(account_number, user_number_ID, account_type, account_balance)
values (00000002, 00000002, 1, 0.00);

insert into
    bank_Account(account_number, user_number_ID, account_type, account_balance)
values (00000003, 00000003, 1, 0.00);

insert into
    bank_Account(account_number, user_number_ID, account_type, account_balance)
values (00000004, 00000004, 1, 0.00);

insert into
    user_Account(email, user_number_ID, password)
values ('Example1@gmail.com', 00000001, 'password1');

insert into
    user_Account(email, user_number_ID, password)
values ('Example2@gmail.com', 00000002, 'password1');

insert into
    user_Account(email, user_number_ID, password)
values ('Example3@gmail.com', 00000003, 'password1');

insert into
    user_Account(email, user_number_ID, password)
values ('Example4@gmail.com', 00000004, 'password1');

-- Reading Data
select * from bank_Account;
select * from user_Account;

-- Setting up the relationship from Bank to User accounts
-- This is setup in the oppisite way as needed currently
alter table user_Account
add constraint fk_user_number_ID
FOREIGN KEY (user_number_ID) references bank_Account(user_number_ID);


