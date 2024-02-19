# Payhere Recruit Homework

## Tech Stack

- `Language` : Kotlin(Java 17)
- `Framework` : Spring Boot 3.2.2
- `Test`: Kotest 5.4.2(Spring Extension 1.1.2), Mockk(1.12.0), Test Fixture(1.2.0)
- `DB`: MySQL 5.7
- `DB Access`: JPA, QueryDSL(5.0.0), Parameter Logging(`p6spy : 1.9.0` )

## API 설계

### Member(회원)

- `회원 등록`
    - POST : /api/members
    - Request Json
    
    ```json
    {
        "phoneNumber" : "010-7777-7777",
        "password" : "payhere12345"
    }
    ```
    
- `로그인`
    - POST : /api/login
    - Request Json
    
    ```json
    {
        "phoneNumber" : "010-7777-7777",
        "password" : "payhere12345"
    }
    ```
    
- `로그아웃`
    - POST: /api/logout
    - Request Json
    
    ```json
    {
    	"accessToken" : "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIwMTAtNzc3Ny03Nzc3IiwiaWF0IjoxNzA4MzQ3MzcyLCJleHAiOjE3MDg0MzM3NzJ9.pxL0K1rp5xyxwE4RPCkvHl1rOcadQXKPK18EHNRg_F52zJF4-TDz1OJO0Xt8YIVErAsBBji2-8bmm_TuPFdBaQ"
    }
    ```
    

### Product(상품)

- `상품 등록`
    - POST : /api/products
    - Request Json
    
    ```json
    { 
      "categoryId": 1,
      "price": 5000,
      "cost": 3000,
      "description": "맛있는 벌꿀 라떼 입니다.",
      "productName": "벌꿀 라떼",
      "barcode": "1234567890123",
      // 유통기한은 yyyy-MM-ddTHH:mm:ss 형식으로 전송해야 합니다.
      "expirationDate": "2024-01-31T12:00:00",
      "productSize": "Small"
    }
    ```
    
- `상품 조회`
    - **상품 단건 조회**
        - GET : /api/products/{product_id}
    - **상품 키워드 리스트 조회**
        - GET : /api/products?prevProductId={productId}&searchKeyword={keyword}
- `상품 삭제`
    - DELETE : /api/products/{productId}
- `상품 수정`
    - PATCH : /api/products/{productId}
    - Request Json
    
    ```json
    { 
      "categoryId": 1,
      "price": 5000,
      "cost": 3000,
      "description": "라떼가 달달해요.",
      "productName": "호랑이 타이거 슈가 라떼",
      "barcode": "1234567890123",
      "expirationDate": "2024-01-31T12:00:00",
      "productSize": "Large"
    }
    ```
    

## 키워드 검색 - 초성 검색 설계

`JPA`와 `QueryDSL`을 조합하여 사용하면, 특정 `DB`에 종속되지 않고 `DB`의 방언에 맞게 알맞은 쿼리가 생성되어 나간다는 장점이 있습니다.

`RDB`를 활용해서 초성 검색을 구현하면 크게 두 가지 방법이 있습니다.

첫 번째 방법은 `DB를 생성할때 함수를 생성`하여 데이터 조회시 일반 문자를 초성으로 변환하는 것입니다.

두 번째 방법은 데이터를 저장할때 키워드 `검색용 테이블`에 초성을 분리하여 미리 저장해두고 `Join` 하여 조회하는 것입니다.

첫 번째 방법은 모든 테이블의 `행마다 조회시 함수가 실행`되어야 한다는 막대한 `오버헤드`가 있었고,

그렇게 구현한다고 해도 특정 `DB`의 문법에 종속되어 DB 변경시마다 `코드 레벨의 수정`이 불가피하다는 단점이 있습니다.

따라서 두 번째 방법을 선택하여 초성 검색을 위한 `키워드 테이블에 상품 저장`시 미리 초성을 등록해두는 방식으로 구현했습니다.

## 확장 가능한 애플리케이션 구조

애플리케이션을 만들어감에 있어서 확장 가능성은 매우 중요한 문제입니다.

모든 `Service Layer`와 `Presentation Layer`는 인터페이스에 의존하도록 하여, 특정 구현체의 변경으로 인해 코드가 변경되지 않도록 하여 `DIP` 원칙을 지켰습니다.

구현 방식의 변경으로 인해 `클라이언트 코드`가 흔들릴 가능성을 최소화 시켜, 확장 가능성을 지켰습니다.

또한 `도메인 주도 설계` 적용으로, `MSA` 도입시 쉽게 서비스를 떼어낼 수 있는 구조로 설계했습니다.

## Test Code

전통적인 `Spring Application`의 테스트는 대부분 `JUnit`을 이용해 작성하고 있습니다.

하지만 언어로 `Kotlin`을 선택하였기 때문에, `infix function`과 간결한 문법 등으로 테스트 코드를 보다 명확하게 작성할 수 있고 비 개발자도 흐름을 이해할 수 있는 간결한 문법을 가진 `Kotest`를 선택했습니다.

`kotlin-test-fixture`를 이용해 값 검증이 필요없는 객체를 쉽게 생성하도록 지원했고, `mockk`를 이용해 다양한 `유스케이스`의 행위 검증에 사용했습니다.

`Test`는 `Slice-Test` 방식으로 진행하였고, 도메인 마다 계층 폴더를 두어 확인하기 쉽게 폴더 구조를 확립했습니다.

## Run Book

이 프로젝트는 `zip 파일`로 다운로드 받을 수 있습니다.

다운로드 받은 뒤 `docker-compose up -d` 명령어를 입력하면 프로젝트가 `MySQL 5.7`과 함께 실행됩니다.

초기 데이터로 `여러 상품`들과, `회원 1`명이 INSERT 됩니다.

컨테이너를 내리고 `db-volume`을 삭제하지 않고 다시 실행하는 경우 중복 데이터 문제로 에러가 발생할 수 있습니다.

그럴때는 `루트 디렉토리`에서 `test-db` 폴더를 삭제하시고 다시 실행하시면 해결 됩니다.

회원가입을 제외한 상품 API는 JWT 토큰이 필요하므로, 미리 생성된 계정을 이용해주시면 됩니다.

- `ID` : 010-7777-7777
- `PW` : payhere12345

상품 등록시 카테고리 1번은 `디저트`, 2번이 `커피류` 입니다.

등록된 카테고리는 두 개 있으니 참고 부탁드립니다.
