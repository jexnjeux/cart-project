insert into `member` (`username`, `password`, `role`, `name`, `phone`)
values ('test1234', 'test1234', 'ROLE_USER', '김코딩', '010-1111-2222'),
       ('test6655', 'test6655', 'ROLE_USER', '김사과', '010-6666-2222');

insert into `product` (`category`, `name`, `price`, `stock`, `status`, `discount_rate`)
values ('CLOTHING', '울 해링턴 자켓', '257000', '100', 'ON_SALE', 5);

insert into `cart` (`count`, `member_id`)
values (0, 2L);

insert into `cart_item` (`quantity`, `cart_id`, `product_id`)
values (1, 1, 1);
