create schema ecommerce;
use ecommerce;

create table user(
  id bigint primary key auto_increment not null,
  username varchar(50) not null,
  password varchar(61) not null,
  role varchar(50) not null
);

create table customer(
  id bigint primary key auto_increment not null,
  name varchar(50) not null,
  address varchar(150) not null,
  contactinfo varchar(50) not null,
  ssn varchar(50) not null
);

create table item(
  id bigint primary key auto_increment not null,
  name varchar(50) not null,
  description varchar(150) not null,
  price double not null
);

create table stock(
  id bigint primary key auto_increment not null,
  item_id bigint not null,
  available bigint not null,
  foreign key (item_id) references item(id)
);

create table orderdetail(
  id bigint primary key auto_increment not null,
  datetime datetime not null,
  paymentmode varchar(50) not null,
  total double not null,
  incart boolean,
  customer_id bigint references customer(id)
);

create table order_item(
  order_id bigint references orderdetail(id),
  item_id bigint references customer(id),
  unique (order_id, item_id)
);

commit;
