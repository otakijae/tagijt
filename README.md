# tagijt



### 토이 프로젝트 함 해보자

- 게시글 with 태그 쓰는 앱
- 특정 카테고리에 해당하는 글 작성
- 그 카테고리를 검색
- 기능
  - 디비연동, 
  - 카프카 적용 메시지 내용을 굳이 불필요한데 전파, 
  - ES 검색
- 기타
  - 로깅
  - aop validation, 후속작업
  - 모니터링
  - 스웨거 문서화
  - 테스트
  - nginx 적용까지



---



- 프로젝트 init / DB 연동 게시글 write
- 사용자 로그인, 인증 개발 / 인증 관련 공부



---



- API webclient 구현
  - https://velog.io/@imkkuk/OPEN-API-%EB%A1%9C-%EB%82%A0%EC%94%A8-%EA%B8%B0%EB%8A%A5-%EA%B5%AC%ED%98%84%ED%95%98%EA%B8%B0
- 로깅 ==> 원리 고급 강의 보고 적용
  - 중요한건 uuid
- rest api
  - 게시글 리스트 조회 GET /v1/contents
  - 게시글 조회 GET /v1/contents/:id

  - 게시글 작성 POST /v1/contents
  - 게시글 수정 PUT /v1/contents/:id
  - 게시글 수정 PATCH /v1/contents/:id
  - 게시글 삭제 DELETE /v1/contents/:id
- 간단 회원 가입 기능 추가
- 테스트 작성 
- 전체 조회 페이지, jps 화면 참고
- ES 연동
  - 태깅/카테고리 검색
- mybatis or jdbc template 구현



## todo

- init

- mysql 연동

- Create table / select samples

  ```sql
  CREATE DATABASE tagit default CHARACTER SET UTF8; 
  ```

  ```sql
  샘플 참고
  channel bot_channels bot_info 관계로 보기
  
  DROP TABLE IF EXISTS `bot_info`;
  /*!40101 SET @saved_cs_client     = @@character_set_client */;
  /*!40101 SET character_set_client = utf8 */;
  
  CREATE TABLE `bot_info` (
    `service_id` varchar(10) NOT NULL,
    `bot_no` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
    `bot_type` tinyint(4) unsigned NOT NULL DEFAULT '0',
    `bot_name` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
    `bot_photo` varchar(4096) DEFAULT NULL,
    `bot_url` varchar(4096) DEFAULT NULL,
    `bot_extras` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin,
    `tenant_id` bigint(20) unsigned NOT NULL,
    `admin_email` varchar(255) DEFAULT NULL,
    `status` tinyint(1) unsigned NOT NULL DEFAULT '0',
    `version` tinyint(4) DEFAULT '0',
    `ctime` datetime(6) DEFAULT NULL,
    `mtime` datetime(6) DEFAULT NULL,
    `app_id` varchar(20) NOT NULL DEFAULT '',
    PRIMARY KEY (`service_id`,`bot_no`),
    KEY `bot_no` (`bot_no`),
    KEY `app_id` (`app_id`)
  ) ENGINE=InnoDB AUTO_INCREMENT=135088 DEFAULT CHARSET=utf8;
  /*!40101 SET character_set_client = @saved_cs_client */;
  ```

  ```sql
  CREATE TABLE `content` (
  	`content_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  	`title` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
    `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin,
    `create_ymdt` datetime(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
    `update_ymdt` datetime(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  	PRIMARY KEY (`content_id`)
  ) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
  
  CREATE TABLE `content_tags` (
  	`content_id` bigint(20) unsigned NOT NULL DEFAULT 0,
  	`tag_id` bigint(20) unsigned NOT NULL DEFAULT 0,
    `create_ymdt` datetime(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
    `update_ymdt` datetime(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  	PRIMARY KEY (`content_id`, `tag_id`)
  ) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
  
  CREATE TABLE `tag` (
  	`tag_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  	`name` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
    `color` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
    `create_ymdt` datetime(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
    `update_ymdt` datetime(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  	PRIMARY KEY (`tag_id`)
  ) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
  
  외래키 일단 생략
  
  INSERT INTO content
  (title, description)
  VALUES('첫 컨텐츠', '첫 컨텐츠 내용');
  
  INSERT INTO tag
  (name, color)
  VALUES('일반', '#FFFFFF');
  
  INSERT INTO content_tags
  (content_id, tag_id)
  VALUES(1, 1);
  ```

- mysql 연동

  - user
    - user_id
    - password
    - name
  - content
    - content_id
    - title
    - description
  - content_tag
    - content_id
    - tag_id
  - tag
    - tag_id
    - name
    - color

- requests

  ```
  curl --location --request GET 'http://localhost:5050/v1/contents' \
  --header 'Content-Type: application/json'
  
  curl --location --request GET 'http://localhost:5050/v1/contents/1' \
  --header 'Content-Type: application/json'
  
  curl --location --request GET 'http://localhost:5050/v1/contents/2' \
  --header 'Content-Type: application/json'
  
  curl --location --request POST 'http://localhost:5050/v1/contents' \
  --header 'Content-Type: application/json' \
  -d '{
    "title": "[세 번째 컨텐츠]",
    "description": "세 번째 컨텐츠 내용입니다."
  }'
  
  curl --location --request PUT 'http://localhost:5050/v1/contents/1' \
  --header 'Content-Type: application/json' \
  -d '{
    "title": "첫번째 컨텐츠 (수정)",
    "description": "첫번째 컨텐츠 내용 ~~~ 등등 (수정했음)"
  }'
  
  curl --location --request PUT 'http://localhost:5050/v1/contents/2' \
  --header 'Content-Type: application/json' \
  -d '{
    "title": "두번째 컨텐츠 (수정)",
    "description": "두 번째 컨텐츠 내용 ~~~ 등등 (수정했음)"
  }'
  
  curl --location --request DELETE 'http://localhost:5050/v1/contents/2' \
  --header 'Content-Type: application/json'
  ```

  