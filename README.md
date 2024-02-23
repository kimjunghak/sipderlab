# Spiderlab Backend Task

## Endpoints

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
> | name     | required | String    | Person Name     |
> | email    | required | String    | Person Email    |
> | cellNo   | required | String    | Person Cell No  |
> | password | required | String    | Person Password |


##### Responses

> | http code | content-type       | response                                                                   |
> |-----------|--------------------|----------------------------------------------------------------------------|
> | `200`     | `application/json` | `{"status": 200,"msg": "success"}`                                         |

##### Example cURL

> ```text
>  curl -X POST /member/signup -H "Content-Type: application/json" -d '{ "name": "홍길동2","email": "hong@woodo.kr","cellNo": "010-0001-0002","password": "password"}'  
> ```

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
> | name     | required | String    | Book Name   |
> | isbn     | required | String    | Book ISBN   |
> | price    | required | String    | Book Price  |


##### Responses

> | http code | content-type       | response                                                                   |
> |-----------|--------------------|----------------------------------------------------------------------------|
> | `200`     | `application/json` | `{"status": 200,"msg": "success"}`                                         |

##### Example cURL

> ```text
>  curl -X POST /book/consignment -H "Content-Type: application/json" -d '{"isbn": "9791168473690","name": "세이노의 가르침","price": "1500"}'  
> ```