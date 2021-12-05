
-- MySQL Sample Data / BIOS SCHEMA

-- Item

insert into item ( name,  price, stock ) 
values	('Mask', 1.00, 10) ;

insert into item ( name,  price, stock ) 
values	('Bandaid', 2.00, 50) ;

insert into item ( name,  price, stock ) 
values	('Advil', 3.50, 150) ;

insert into item ( name,  price, stock ) 
values	('Neosporin', 4.00, 75) ;

-- User

insert into user ( username, password ) 
values	('micheal23', 'quangster') ;

insert into user ( username, password ) 
values	('aaron33', 'tester12') ;

insert into user ( username, password ) 
values	('richard_ok', 'okok123') ;

-- Cart

insert into cart ( user_id ) 
values	(1) ;

insert into cart ( user_id ) 
values	(2) ;

insert into cart ( user_id ) 
values	(3) ;

-- Cart_item

insert into cart_item ( cart_id, item_id, quantity ) 
values	(1 , 1, 10) ;

insert into cart_item ( cart_id, item_id, quantity ) 
values	(1 , 2, 22) ;

insert into cart_item ( cart_id, item_id, quantity ) 
values	(1 , 3, 30) ;

