# Spiderlab Backend Task

## Endpoints

### Signup

- [x] *회원가입*
    - Input:
        - name (string)
        - email (string)
        - cellNo (string)
        - password (string)
    - Output: -

`POST` `/member/signup` `content-type: application/json`

##### Request Body

> | name     | type     | data type | description     |
> |----------|----------|-----------|-----------------|
> | name     | required | string    | Person Name     |
> | email    | required | string    | Person Email    |
> | cellNo   | required | string    | Person Cell No  |
> | password | required | string    | Person Password |


##### Responses Body

> | http code | content-type       | response                                                                   |
> |-----------|--------------------|----------------------------------------------------------------------------|
> | `200`     | `application/json` | `{"status": 200,"msg": "success"}`                                         |

##### Example cURL

> ```text
>  curl -X POST http://localhost:8080/member/signup -H "Content-Type: application/json" -d '{ "name": "홍길동2","email": "hong@woodo.kr","cellNo": "010-0001-0002","password": "password12"}'  
> ```

### Login

- [x] *로그인*
    - Input:
        - email (string)
        - password (string)
    - Output: -

`POST` `/member/login` `content-type: application/json`

##### Request Body

> | name     | type     | data type | description     |
> |----------|----------|-----------|-----------------|
> | email    | required | string    | Member Name     |
> | password | required | string    | Member Password |


##### Responses Body

> | http code | content-type       | response                                                                   |
> |-----------|--------------------|----------------------------------------------------------------------------|
> | `200`     | `application/json` | `{"status": 200,"msg": "success"}`                                         |

##### Example cURL

> ```text
>  curl -X POST http://localhost:8080/member/login -H "Content-Type: application/json" -d '{"email": "hong@woodo.kr","password": "password12"}'  
> ```
 
#### Description
책을 위탁할 때 사용자 정보가 필요하기 때문에 로그인이 필수로 존재해야 함.   
간단하게 구현하여서 session을 사용했으므로 로그인 이후 브라우저 혹은 포스트맨과 같은 Response 쿠키를 확인할 수 있는 도구를 이용해서 `JSESSIONID`를 가져와야 함.

### Consignment Book

- [x] *도서 위탁*
    - Input:
        - name (string)
        - isbn (string)
        - price (number)
    - Output: -

`POST` `/book/consignment` `content-type: application/json`

##### Request Body

> | name     | type     | data type | description |
> |----------|----------|-----------|-------------|
> | name     | required | string    | Book Name   |
> | isbn     | required | string    | Book ISBN   |
> | price    | required | number    | Book Price  |


##### Responses Body

> | http code | content-type       | response                                                                   |
> |-----------|--------------------|----------------------------------------------------------------------------|
> | `200`     | `application/json` | `{"status": 200,"msg": "success"}`                                         |

##### Example cURL

> ```text
>  curl -X POST http://localhost:8080/book/consignment -H "Cookie: JSESSIONID=C5EF955DEFDAC35594B5A0D40C34A363" -H "Content-Type: application/json" -d '{"isbn": "9791168473690","name": "세이노의 가르침","price": "1500"}'  
> ```
 
### Borrow Book
- [x] *도서 대여*
    - Input:
        - bookIds (array)
    - Output: -

`POST` `/book/borrow` `content-type: application/json`

##### Request Body

> | name    | type     | data type | description  |
> |---------|----------|-----------|--------------|
> | bookIds | required | array     | Book Id List |


##### Responses Body

> | http code | content-type       | response                                                                         |
> |-----------|--------------------|----------------------------------------------------------------------------------|
> | `200`     | `application/json` | `{"status": 200,"msg": "success"}`                                               |
> | `200`     | `application/json` | `{"status": 200,"msg": "success", "additionalInfo": "Some books do not exist."}` |

##### Example cURL

> ```text
>  curl -X POST http://localhost:8080/book/consignment -H "Content-Type: application/json" -d '{"isbn": "9791168473690","name": "세이노의 가르침","price": "1500"}'  
> ```
 
##### Description
동시에 같은 책을 대여할 경우 문제를 대비


### 책 반납
- 책 반납의 경우 Scheduler를 통해서 1초마다 조사하여서 반납 대상을 선정 후에 반납 

### Application 개발 환경
IDE: Intellij Ultimate   
Java: v21   
Kotlin: v1.9.22   
Spring Boot: v3.2.2

profile: `dev`로 실행 시 DataSetup.kt이 실행되어서 기본 데이터 세팅을 하여 테스트 가능

실행: Intellij SipderlabApplication Run
