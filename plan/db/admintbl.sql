create database ApartmentManagement;
use ApartmentManagement;
create table admintbl(
	adminId varchar(10) primary key,
    adminName varchar(40),
    adminMail varchar(40),
    adminPass varchar(100)
)
select * from admintbl;
delete from admintbl where adminName='nam vu'