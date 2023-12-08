# 커머스 프로젝트 🛍️
커머스 프로젝트는 간편하고 효율적인 온라인 쇼핑 경험을 제공합니다. 사용자들은 회원가입을 통해 개인 계정을 생성하고, 다양한 상품을 등록하고 관리할 수 있습니다. 또한, 장바구니를 통해 손쉽게 상품을 선택하고 관리할 수 있습니다.

## 주요 기능
- 상품 등록: 로그인 후 새로운 상품을 등록할 수 있습니다.
- 상품 조회: 키워드, 카테고리, 가격 정보를 이용해 상품을 조회할 수 있습니다.
- 장바구니 추가: 현재 판매 중인 상품은 장바구니에 추가할 수 있습니다.
<br/>

## 시스템 아키텍처

![system architecture](./docs/img/system_architecture.png)

## ERD

![erd](./docs/img/erd.png)
<br/>
<br/>


## API

<!-- ### [API 문서(swagger)](http://43.201.145.62:8080/swagger-ui/index.html)-->

| API 그룹      | 엔드포인트                                                             | HTTP 메서드 | 설명          |
|-------------|-------------------------------------------------------------------|----------|-------------|
| 회원 관리 API   | /join                                                             | POST     | 회원가입        |
|             | /login                                                            | POST     | 로그인         |
| 상품 관리 API   | /api/products                                                     | POST     | 상품 등록       |
|             | /api/products/{id}                                                | GET      | 상품 상세 조회    |
|             | /api/products/{id}                                                | PUT      | 상품 수정       |
|             | /api/products/{id}                                                | DELETE   | 상품 삭제       |
|             | /api/products?keyword=<br/>&page=&category=&min-price=&max-price= | GET      | 상품 검색       |
| 장바구니 관리 API | /api/user/{user_id}/carts                                         | POST     | 장바구니 추가     |
|             | /api/user/{user_id}/carts                                         | GET      | 장바구니 조회     |
|             | /api/user/{user_id}/carts/{cart_item_id}                          | PUT      | 장바구니 아이템 수정 |
|             | /api/user/{user_id}/carts/{cart_item_id}                          | DELETE   | 장바구니 아이템 삭제 |

<br/>

## 기술 스택

![Static Badge](https://img.shields.io/badge/java_17-ea2c2f?style=for-the-badge)
![Static Badge](https://img.shields.io/badge/spring_boot_3-6DB33F?style=for-the-badge)
![Static Badge](https://img.shields.io/badge/spring_security-6DB33F?style=for-the-badge)
![Static Badge](https://img.shields.io/badge/spring_data_JPA-6DB33F?style=for-the-badge)

![Static Badge](https://img.shields.io/badge/h2_database-0c1de6?style=for-the-badge)
![Static Badge](https://img.shields.io/badge/mysql-016189?style=for-the-badge)
![Static Badge](https://img.shields.io/badge/swagger-84E92C?style=for-the-badge)

![Static Badge](https://img.shields.io/badge/github_actions-2088FF?style=for-the-badge)
![Static Badge](https://img.shields.io/badge/AWS_s3-DF5344?style=for-the-badge)
![Static Badge](https://img.shields.io/badge/AWS_codedeploy-4B612C?style=for-the-badge)
![Static Badge](https://img.shields.io/badge/aws_ec2-F48536?style=for-the-badge)
![Static Badge](https://img.shields.io/badge/aws_rds-2E73B8?style=for-the-badge)

<br/>

## 유스 케이스

### [유스 케이스 보기](https://github.com/jexnjeux/cart-project/wiki/%EC%9C%A0%EC%8A%A4-%EC%BC%80%EC%9D%B4%EC%8A%A4)
