/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  Claudivan Moreira
 * Created: 09/04/2017
 */

create table cards (
    id INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 1, INCREMENT BY 1) PRIMARY KEY,
    name varchar(100),
    flag_of_card varchar(100),
    limit varchar (10),
    invoice_due_date varchar (10),
    invoice_close_date varchar (10)
);

create table categories (
    id INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 1, INCREMENT BY 1) PRIMARY KEY,
    name varchar(100),
    category_type varchar(100)
);

create table dreams (
    id INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 1, INCREMENT BY 1) PRIMARY KEY,
    name varchar(100),
    amount_value varchar(100),
    saved_value varchar(100)
);