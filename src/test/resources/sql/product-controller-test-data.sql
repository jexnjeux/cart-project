insert into `member` (`username`, `password`, `role`, `name`, `phone` )
values ('test1234', 'test1234', 'ROLE_USER', '김코딩', '010-1111-2222'),
       ('test3333', 'test3333', 'ROLE_ADMIN', '김관리', '010-3333-3333'),
       ('test4444', 'test4444', 'ROLE_ADMIN', '김상품', '010-4444-4444');

insert into `PRODUCT` (`category`, `name`, `price`, `stock`, `status`, `discount_rate`, `created_by`)
values ('CLOTHING', '울 해링턴 자켓', '257000', '100', 'ON_SALE', 5, 2),
       ('CLOTHING', '오픈 카라 자켓', '237000', '100', 'ON_SALE', 5, 2),
       ('CLOTHING', '니트 베스트', '2359500', '100', 'ON_SALE', 5, 2),
       ('CLOTHING', 'Check maxi skirt', '298000', '10', 'ON_SALE', 10, 3),
       ('CLOTHING', 'corduroy carpenter pants', '159000', '3', 'ON_SALE', 10, 3),
       ('CLOTHING', 'corduroy carpenter half pants', '139000', '11', 'ON_SALE', 10, 3),
       ('CLOTHING', 'wool blend classic pola knit', '69000', '30', 'ON_SALE', 20, 3),
       ('CLOTHING', 'velvet knit jogger pants', '199000', '100', 'ON_SALE', 10, 3),
       ('CLOTHING', 'wool knit pullover', '95000', '120', 'ON_SALE', 0, 3),
       ('CLOTHING', '와이드 슬랙스', '97000', '100', 'ON_SALE', 0, 3);