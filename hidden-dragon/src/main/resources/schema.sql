
SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema cmpe172
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `cmpe172` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `cmpe172` ;

-- -----------------------------------------------------
-- Table `cmpe172`.`person`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cmpe172`.`cart` ;
DROP TABLE IF EXISTS `cmpe172`.`cart_item` ;
DROP TABLE IF EXISTS `cmpe172`.`drug_store_command` ;
DROP TABLE IF EXISTS `cmpe172`.`hibernate_sequence` ;
DROP TABLE IF EXISTS `cmpe172`.`item` ;
DROP TABLE IF EXISTS `cmpe172`.`user` ;


create table cart (id integer not null, user_id integer, primary key (id)) engine=InnoDB;
create table cart_item (id integer not null, quantity integer, cart_id integer, item_id integer, primary key (id)) engine=InnoDB;
create table drug_store_command (id bigint not null, action varchar(255), address varchar(255), authid varchar(255), authstatus varchar(255), captureid varchar(255), capturestatus varchar(255), card_type varchar(255), cardcvv varchar(255), cardexpmon varchar(255), cardexpyear varchar(255), cardnum varchar(255), city varchar(255), cost varchar(255), email varchar(255), firstname varchar(255), lastname varchar(255), notes varchar(255), ordernumber varchar(255), password varchar(255), phone varchar(255), state varchar(255), transactionamount varchar(255), transactioncurrency varchar(255), user_id integer, username varchar(255), zip varchar(255), primary key (id)) engine=InnoDB;
create table hibernate_sequence (next_val bigint) engine=InnoDB;
insert into hibernate_sequence values ( 1 );
create table item (id integer not null, image varchar(255), name varchar(255), price integer, stock integer, primary key (id)) engine=InnoDB;
create table user (id integer not null, password varchar(255), username varchar(255), primary key (id)) engine=InnoDB;
alter table item add constraint UK_lcsp6a1tpwb8tfywqhrsm2uvg unique (name);
alter table user add constraint UK_sb8bbouer5wak8vyiiy4pf2bx unique (username);
alter table cart_item add constraint FK1uobyhgl1wvgt1jpccia8xxs3 foreign key (cart_id) references cart (id);
alter table cart_item add constraint FKdljf497fwm1f8eb1h8t6n50u9 foreign key (item_id) references item (id);
