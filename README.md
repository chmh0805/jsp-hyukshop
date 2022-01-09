# JSP 인터넷 쇼핑몰 프로젝트

### 프로젝트 기간 : 21.01.13 ~ 21.01.29
### 프로젝트 수행자 : 정민혁(chmh0805@naver.com)
### EC2 배포 링크 : ~~http://15.164.166.37/shop~~ 무료 라이센스 만료로 EC2 서버 배포는 중단합니다.
### 시연 영상 : https://youtu.be/UMdEGoW_ulQ

## 환경
- Windows 10
- JDK 1.8
- Tomcat 9.0
- STS Tool
- MySQL 8.0
- Lombok
- Gson
- JSTL
- Naver Lucy Filter
- SHA-256
- Encoding : UTF-8

## 디자인 및 상품, 상품이미지, 상품설명 참고 사이트
 1. 브랜디
  - https://www.brandi.co.kr/
 2. 트렌비
  - https://www.trenbe.com/
 3. 발란
  - https://www.balaan.co.kr/

## 참고사항
 - 결제화면에서 결제 완료 시 실제 결제가 이루어지며, 정오 전에 자동 환불처리 됩니다.

## 사용 API
 1. 카카오로그인 API
  - https://developers.kakao.com/product/kakaoLogin
 2. 네이버로그인 API
  - https://nid.naver.com/user2/campaign/introNaverIdLogin.nhn
 - 네이버, 카카오 로그인의 경우 최초 로그인 시 정보입력 창으로 이동하여 입력받은 값과 네이버/카카오 서버로부터 전달받은 id값을 user 테이블에 insert하였습니다.
 - 이후 로그인 시에는 user 테이블에 저장해 둔 카카오 고유id, 네이버 고유id 값으로 SELECT 하여 로그인되도록 구현하였습니다.
 3. 아임포트 API (결제 서비스)
  - https://www.iamport.kr/getstarted
 4. summernote API
  - https://summernote.org/
 5. sweetalert2
  - https://sweetalert2.github.io/


## MySQL 데이터베이스 생성 및 사용자 생성
```sql
CREATE USER 'shopuser'@'%' identified by 'shop0805';
GRANT ALL privileges on *.* TO 'shopuser'@'%';
create database shopdb;
```

## MySQL 테이블 생성
```sql
use shopdb;
```

```sql
CREATE TABLE product(
    id int primary key auto_increment,
    productName varchar(120) unique not null,
    companyId int not null,
    price long not null,
    soldCount int default 0,
    detail longtext not null,
    imgUrl_1 varchar(100) not null,
    imgUrl_2 varchar(100),
    imgUrl_3 varchar(100),
    imgUrl_4 varchar(100),
    writerId int,
    createDate timestamp default now(),
    updateDate timestamp default now()
);
```

```sql
CREATE TABLE user(
    id int primary key auto_increment,
    username varchar(20) unique default null,
    name varchar(20) not null,
    email varchar(50) not null,
    phone varchar(13) not null,
    address varchar(120) not null,
    password char(64) not null,
    kakaoId long,
    naverId long,
    auth varchar(10) default 'user',
    createDate timestamp default now()
);
```

```sql
CREATE TABLE company (
    id int primary key auto_increment,
    name varchar(40) unique not null,
    url varchar(80) unique
);
```

```sql
CREATE TABLE favorite(
    id int primary key auto_increment,
    userId int not null,
    productId int not null,
    createDate timestamp default now()
);
```

```sql
CREATE TABLE cart(
    id int primary key auto_increment,
    userId int not null,
    productId int not null,
    createDate timestamp default now()
);
```

```sql
CREATE TABLE qna(
    id int primary key auto_increment,
    userId int not null,
    productId int not null,
    optionNo int not null,
    password varchar(4),
    detail longtext,
    createDate timestamp default now()
);
```

```sql
CREATE TABLE review(
    id int primary key auto_increment,
    userId int not null,
    productId int not null,
    detail longtext not null,
    createDate timestamp default now(),
    updateDate timestamp default now()
);
```

# 구현한 기능들
 <b>1. 회원가입</b>
 ![image](https://user-images.githubusercontent.com/63082842/106240752-2e5dd200-6248-11eb-9079-161fdddf2b83.png)
 - 회원가입 시 입력한 비밀번호는 SHA-256으로 인코딩되어 DB에 저장됩니다.    
 
 <b>2. 로그인</b>
 ![image](https://user-images.githubusercontent.com/63082842/106240841-53524500-6248-11eb-884c-5c51142c0813.png)
 
 <b>3. 카카오 로그인</b>
 ![image](https://user-images.githubusercontent.com/63082842/106241091-c8be1580-6248-11eb-99b1-bd787c8ab9aa.png)
 
 <b>4. 네이버 로그인</b>
 ![image](https://user-images.githubusercontent.com/63082842/106241324-3407e780-6249-11eb-8828-76e0f6739853.png)
 
 <b>네이버/카카오 최초 로그인 시 추가정보 입력 요구</b>
 ![image](https://user-images.githubusercontent.com/63082842/106241414-5994f100-6249-11eb-816b-39a3450ca076.png)

 <b>5. 로그아웃</b>
 
 <b>6. 메인페이지</b>
 ![image](https://user-images.githubusercontent.com/63082842/106241659-c14b3c00-6249-11eb-90f6-c3230a020bb4.png)
 - Carousel을 활용하였습니다.
 - grid Layout을 활용하였습니다.
 
 <b>7. 상단바 브랜드별 메뉴</b>
 ![image](https://user-images.githubusercontent.com/63082842/106241762-ec359000-6249-11eb-8d99-5bc8ca0e8397.png)
 - SELECT문을 활용하여 company 테이블의 모든 회사명을 불러옵니다. (회사가 추가되어도 따로 수정할 필요가 없습니다.)
 - 모든 페이지에 header와 상단바가 존재하여, filter를 사용하여 모든 페이지에서 사용할 수 있게 하였습니다.
 
 <b>8. 브랜드별 메뉴 페이지</b>
 ![image](https://user-images.githubusercontent.com/63082842/106244992-19387180-624f-11eb-9255-987a3590edcc.png)
 
 <b>9. 검색기능</b>
 ![image](https://user-images.githubusercontent.com/63082842/106245244-82b88000-624f-11eb-9771-98bec7015d12.png)
 - 검색어를 포함하고 있는 상품명과 회사의 모든 결과를 보여줍니다.

 <b>10. 판매량 순 랭킹 페이지</b>
 ![image](https://user-images.githubusercontent.com/63082842/106244815-d8d8f380-624e-11eb-9eee-7b886d0f8d06.png)
 - 유저가 상품을 구매하면 product 테이블의 구매수가 오르게 되고, ORDER BY soldCount DESC을 사용하여 판매량 순으로 조회한 결과입니다.

 <b>11. 전체상품 페이지에서 특정 브랜드 별</b>
 ![image](https://user-images.githubusercontent.com/63082842/106245360-aa0f4d00-624f-11eb-9e85-1a0315e1bb34.png)
 - 좌측에 라디오박스의 브랜드 중 클릭된 브랜드의 상품만 보는 기능입니다.
 
 <b>12. 로그인 시 상단 메뉴 추가</b>
 ![image](https://user-images.githubusercontent.com/63082842/106245552-f8245080-624f-11eb-9a16-4b307b6884c4.png)
 - 유저의 등급이 admin 이상이면 상품등록, 상품수정 메뉴가 생깁니다.
 - 유저의 등급이 기본 이상이면 찜, 장바구니, 정보수정, 로그아웃 메뉴가 생깁니다.

 <b>13. 정보수정 클릭 시 비밀번호 재확인</b>
 ![image](https://user-images.githubusercontent.com/63082842/106245663-2c980c80-6250-11eb-9caa-fb189732be99.png)
 - 유저가 자신이 설정한 비밀번호를 입력하면, 입력받은 값을 SHA-256 인코딩을 적용하여 DB에 저장된 값과 비교합니다.

 <b>14. 회원정보 수정</b>
 ![image](https://user-images.githubusercontent.com/63082842/106245704-3faadc80-6250-11eb-93c0-20da739c78c3.png)
 
 <b>15. 상세상품 페이지 - 상단</b>
 ![image](https://user-images.githubusercontent.com/63082842/106246754-f491c900-6251-11eb-8b09-4ef661d833be.png)
 - product 테이블의 값을 select 하여 보여줍니다.
 - 바로구매 버튼 클릭 시 결제페이지로 이동합니다. (로그인 시에만)
 - 장바구니 버튼 클릭 시 장바구니에 추가됩니다. (로그인 시에만)
 - 찜 버튼 클릭 시 찜목록에 추가됩니다. (로그인 시에만)
 ![image](https://user-images.githubusercontent.com/63082842/106248073-d0cf8280-6253-11eb-9bcc-60baca222f17.png)
  - 로그인 상태에서 버튼 클릭 시
 ![image](https://user-images.githubusercontent.com/63082842/106248122-e2188f00-6253-11eb-8e80-7bfa532fc7ed.png)
  - 비로그인 상태에서 버튼 클릭 시
  - 회원가입 클릭 시 회원가입 페이지로 이동합니다.

 <b>16. 상세상품 페이지 - 탭</b>
 ![image](https://user-images.githubusercontent.com/63082842/106246856-1db25980-6252-11eb-8366-048185434d0a.png)
 - 각 탭을 클릭하면, 해당 탭이 존재하는 위치로 이동합니다.
 - sticky position을 활용하였습니다.
 - 각 탭이 위치한 곳으로 이동하면, 해당 탭의 아래에 검은줄이 표시됩니다.
 
 <b>17. 상세상품 페이지 - 리뷰탭과 Q&A탭</b>
 ![image](https://user-images.githubusercontent.com/63082842/106247074-5eaa6e00-6252-11eb-8ec2-b01854ed929c.png)
 - 제목을 클릭하면 상세 내용 페이지로 이동합니다.
 - Q&A의 경우 작성 시 비밀번호를 입력한 경우, 제목과 작성자명을 안보이게 합니다.
 - 우측 하단의 화살표를 누르면 페이지의 최상단으로 즉시 이동합니다.
 
 <b>18. 상세 리뷰, Q&A 페이지</b>
 ![image](https://user-images.githubusercontent.com/63082842/106247234-96191a80-6252-11eb-8669-4172f8b2e32f.png)
 
 <b>19. Q&A 중 비밀글 클릭 시</b>
 ![image](https://user-images.githubusercontent.com/63082842/106247326-b5b04300-6252-11eb-854d-2dd2896be434.png)
 - 비밀번호 확인 후 일치하면 상세 페이지로 이동합니다.
 
 <b>20. 리뷰 전체보기 클릭 시</b>
 ![image](https://user-images.githubusercontent.com/63082842/106247390-cf518a80-6252-11eb-8cbd-bf39dfb39a6f.png)
 - 보고있던 상품 내용을 함께 불러오며, 상품 사진 클릭 시 상품 페이지로 이동할 수 있습니다.

 <b>21. Q&A 전체보기 클릭 시</b>
 ![image](https://user-images.githubusercontent.com/63082842/106247484-ef814980-6252-11eb-9ab0-51f40149e126.png)
 - 보고있던 상품 내용을 함께 불러오며, 상품 사진 클릭 시 상품 페이지로 이동할 수 있습니다.
 - 비밀글은 여기서도 가려집니다.
 
 <b>22. 리뷰 작성하기 클릭 시</b>
 ![image](https://user-images.githubusercontent.com/63082842/106247571-1475bc80-6253-11eb-88fc-e1b489a42966.png)
 - 사진, 동영상 기능을 제외한 summernote를 활용하였습니다.
 
 <b>23. Q&A 작성하기 클릭 시</b>
 ![image](https://user-images.githubusercontent.com/63082842/106247618-22c3d880-6253-11eb-8167-57a044ebe851.png)
 - 사진, 동영상 기능을 제외한 summernote를 활용하였습니다.
 
 <b>24. 우측 상단 메뉴 중 찜</b>
 ![image](https://user-images.githubusercontent.com/63082842/106247784-5ef73900-6253-11eb-960c-184ec9e37fd6.png)
 - 회원이 찜을 한 상품들만 보여집니다.
 - 좌측 라디오박스에도 회원이 찜을 한 브랜드만 보여집니다.
 
 <b>25. 우측 상단 메뉴 중 장바구니</b>
 ![image](https://user-images.githubusercontent.com/63082842/106247906-8e0daa80-6253-11eb-8e7a-5e9169519fa6.png)
 - 회원이 장바구니에 추가한 상품들만 보여집니다.
 - 상품목록의 하트 버튼을 클릭하여 찜 추가/삭제 기능을 사용할 수 있습니다.
 - 상품목록 우측의 장바구니 버튼을 클릭하여 장바구니 삭제 기능을 사용할 수 있습니다.
 - 주문하기 클릭 시 결제 페이지로 이동합니다.
 
 <b>26. 결제 페이지</b>
 ![image](https://user-images.githubusercontent.com/63082842/106248218-070d0200-6254-11eb-9253-326bde0e397a.png)
 - 장바구니에서 주문하기 클릭 시 장바구니의 전체 상품을 가져옵니다.
 - 상품 상세 페이지에서 바로구매 클릭 시 해당 상품만 가져옵니다.
 - 주문 완료하기 클릭 시 결제 페이지로 이동합니다.
 
 <b>27. 결제 화면</b>
 ![image](https://user-images.githubusercontent.com/63082842/106248490-6703a880-6254-11eb-86c1-a98e98b318a1.png)
 
 <b>28. 결제 실패 시</b>
 ![image](https://user-images.githubusercontent.com/63082842/106248535-7aaf0f00-6254-11eb-9f3f-ae64be1b2cdc.png)
 - 오류 내용을 포함한 알림창이 뜹니다.
 
 <b>29. 결제 성공 시</b>
 ![Screenshot_51](https://user-images.githubusercontent.com/63082842/106248587-8b5f8500-6254-11eb-8e8b-4b29017c396a.png)
 - 성공 내역을 포함한 알림창이 뜨며, 확인 클릭 시 메인페이지로 이동합니다.
 
 <b>30. 아임포트 내역</b>
 ![image](https://user-images.githubusercontent.com/63082842/106249080-37a16b80-6255-11eb-8bb0-35f43afb398b.png)
 - 결제 성공/실패 내역을 볼 수 있습니다.
