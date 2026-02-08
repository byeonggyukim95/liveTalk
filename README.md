Kafka와 WebSocket을 활용한 실시간 채팅 서버를 구현한 프로젝트입니다.

## 🗄 Database Schema
```sql
CREATE TABLE `member` (
  `member_uid` bigint NOT NULL AUTO_INCREMENT,
  `member_id` varchar(45) NOT NULL,
  `password` varchar(256) NOT NULL,
  `created_at` datetime NOT NULL,
  PRIMARY KEY (`member_uid`),
  UNIQUE KEY `member_name_UNIQUE` (`member_id`)
) ENGINE=InnoDB;
```
