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
>  curl -X POST /member/signup -H "Content-Type: application/json" -d '{ "name": "홍길동2","email": "hong@woodo.kr","cellNo": "010-0001-0002","password": "password"}'  
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
>  curl -X POST /member/login -H "Content-Type: application/json" -d '{"email": "hong@woodo.kr","password": "password12"}'  
> ```
 
#### Description
책을 위탁할 때 사용자 정보가 필요하기 때문에 로그인이 필수로 존재해야 함.

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
>  curl -X POST /book/consignment -H "Content-Type: application/json" -d '{"isbn": "9791168473690","name": "세이노의 가르침","price": "1500"}'  
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
>  curl -X POST /book/consignment -H "Content-Type: application/json" -d '{"isbn": "9791168473690","name": "세이노의 가르침","price": "1500"}'  
> ```
 
##### Description
동시에 같은 책을 대여할 경우 문제를 대비
