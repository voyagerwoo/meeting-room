# 회의실 예약 API
GraphQL을 활용한 회의실 예약 API

## 기능
- 회의실 예약하기
- 주단위 회의실 예약 내역 조회
- 요청한 시간에 회의실 선점 확인

## 실행 방법
(자바 8 설치 필요)

### clone
```
git clone https://github.com/voyagerwoo/meeting-room.git
```

### 실행
```
./mvnw spring-boot:run
```

### 확인 
> 테스트용 기본 데이터: [data.sql](./src/main/resources/data.sql)
> 
> graphql 스키마: [meetingroom.graphqls](./src/main/resources/graphql/meetingroom.graphqls)
>
> 앤드 포인트: http://localhost:8080/graphql

- http://localhost:8080/graphiql 접속
- 일주일치 조회 쿼리 테스트
```graphql
query {
  findReservationsByStartTimeBetween(startDate:"2019-12-23", endDate: "2019-12-30") 
  {
    id, 
    userName,
    meetingRoomName
  }
}
```
- 특정 시간에 사용 가능한 회의실 조회 쿼리 테스트
```graphql
query {
  findAvailableMeetingRoom(startTime: "2019-12-26 09:00:00", endTime: "2019-12-26 10:00:00")
  {
    id,
    name,
    size
  }
}
```
- 예약 테스트
```graphql
mutation {
  reserve(userId: 1002, meetingRoomId: 10001, startTime: "2019-12-25 09:00:00", endTime: "2019-12-25 10:00:00")
  {
    id,
    userId,
    meetingRoomId
  }
}
```
