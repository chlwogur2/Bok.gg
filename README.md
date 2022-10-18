# Bok.gg

***

***League of Legends (이하 LOL) 전적 검색 +  댓글 서비스입니다.***

- 이미 전적 검색 사이트는 수도 없이 많습니다. 그런데 차별점은 뭘까?
- 제가 만들고자 하는 것은, 기록된 게임 전적에 댓글을 달 수 있게 하여 사용자들로 하여금 지나간 게임에 대한 피드백을 서로 나누고, 전략 토론의 장을 만들고자 하는 것입니다.
- 제 친구들을 대상으로 하는 소규모 프로젝트이고, 공부한 기술들에 대한 정확한 이해, 새로운 기술들의 도입이 우선 목표이기 때문에 개발 속도가 느릴 수 있습니다.

<br><br>

#### 사용한 기술들

***



|                          | 기술 스택                                       |
| :----------------------: | :---------------------------------------------- |
| **서버 개발 프레임워크** | SpringBoot                                      |
|         **ORM**          | JPA                                             |
|         **DBMS**         | MariaDB                                         |
|         **ETC**          | DBeaver / Thymeleaf / Postman / Spring Secuirty |

<br><br>

##### 커밋 컨벤션

***



|              |            의미            |
| :----------: | :------------------------: |
|  **[ADD]**   | 새로운 파일, 도메인의 추가 |
|  **[EDIT]**  |       세부 로직 수정       |
| **[PRETTY]** |      코드 이쁘게 하기      |
| **[Rename]** |        파일명 변경         |
|  **[TEST]**  |        테스트 코드         |
| **[DELETE]** |         파일 삭제          |


<br><br>

#### API 명세

***

**Home**

| Name         | Method | Path | Attribute  | Status       | Comment      |
| ------------ | ------ | ---- | ---------- | ------------ | ------------ |
| 메인 홈 화면 | `GET`  | `/`  | `@Session` | `Developing` | 매치 화면 필 |



**User**

`@RequestMapping("/users")`

| Name        | Method | Path      | Attribute | Status       | Comment     |
| ----------- | ------ | --------- | --------- | ------------ | ----------- |
| 회원가입 폼 | `GET`  | `/signup` |           | `Complete`   |             |
| 회원가입    | `POST` | `/signup` | `@Valid`  | `Developing` | ID 중복검사 |
| 내 정보     | `GET`  | `/mypage` |           | `Developing` | 작성한 댓글 |



**Login**

- Spring Security 가 제공하는 로그인 로직으로 변경할 예정

| Name      | Method | Path          | Attribute | Status     | Comment |
| --------- | ------ | ------------- | --------- | ---------- | ------- |
| 로그인 폼 | `GET`  | `/login`      |           | `Complete` |         |
| 로그인    | `POST` | `/login_proc` | `@Valid`  | `Complete` |         |



**Comment**

`@RequestMapping("/comments")`

| Name | Method | Path | Attribute | Status | Comment |
| ---- | ------ | ---- | --------- | ------ | ------- |
|      |        |      |           |        |         |