CREATE DATABASE qlbh;
use qlbh;

create table catalog
(
    id   int auto_increment primary key,
    name varchar(255)
);
insert into catalog(name)
values ('a0'),
       ('b'),
       ('c');

delimiter //
create procedure PROC_InsertNewCatalog(newName varchar(255))
BEGIN
insert into catalog(name) values (newName);
end //
delimiter ;

delimiter //
create procedure PROC_GetAllCatalog()
BEGIN
select * from catalog;
end //
delimiter ;

delimiter //
create procedure PROC_DeleteCatalog(id int)
BEGIN
delete from catalog where catalog.id = id;
end //
delimiter ;


delimiter //
create procedure PROC_UpdateCatalog(idEdit int, nameEdit varchar(255))
BEGIN
update catalog set name = nameEdit where id = idEdit;
end //
delimiter ;

delimiter //
create procedure PROC_FindCatalogById(idFind int)
BEGIN
select * from catalog where id = idFind;
end //
delimiter ;


delimiter //
create procedure PROC_FindByName(nameSearch varchar(255))
BEGIN
select * from catalog where name like concat('%', nameSearch, '%');
end //
delimiter ;
# -----------------------------------------------------------------------


# -------------------------------------------------------------------------
create table product
(
    id        int auto_increment primary key,
    name      varchar(255) not null,
    price     float,
    quantity  int,
    catalogId int,
    foreign key (catalogId) references catalog (id)
);


delimiter //
create procedure PROC_InsertNewProduct(newName varchar(255), newPrice float, newQuantity int, newCatalogId int)
BEGIN
insert into product(name, price, quantity, catalogId) values (newName, newPrice, newQuantity, newCatalogId);
end //
delimiter ;


delimiter //
create procedure PROC_GetAllProduct()
BEGIN
select * from product;
end //
delimiter ;

delimiter //
create procedure PROC_DeleteProduct(idDelete int)
BEGIN
delete from product where product.id = idDelete;
end //
delimiter ;


delimiter //
create procedure PROC_UpdateProduct(idEdit int, newName varchar(255), newPrice float, newQuantity int, newCatalogId int)
BEGIN
update product
set name = newName, price = newPrice, quantity = newQuantity, catalogId = newCatalogId
where id = idEdit;
end //
delimiter ;

delimiter //
create procedure PROC_FindProductById(idFind int)
BEGIN
select * from product where id = idFind;
end //
delimiter ;


delimiter //
create procedure PROC_FindByNameProduct(nameSearch varchar(255))
BEGIN
select * from product where name like concat('%', nameSearch, '%');
end //
delimiter ;


delimiter //
create procedure PROC_FindByCatalog(idCatalogSearch int)
BEGIN
select * from product where catalogId = idCatalogSearch;
end //
delimiter ;

