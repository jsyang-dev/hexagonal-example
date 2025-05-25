# 주문 관리 시스템 PRD (Product Requirements Document)

## 1. 개요

### 1.1 목적
- 헥사고날 아키텍처와 DDD(도메인 주도 설계)를 실제로 적용한 주문 관리 시스템을 구현합니다.
- 주니어 개발자도 쉽게 이해하고 따라할 수 있는 예제를 제공합니다.
- 실무에서 자주 사용되는 기술 스택을 활용합니다.

### 1.2 기술 스택
- 언어: Java 21
- 프레임워크: Spring Boot 3.2.3
- 빌드 도구: Gradle
- 데이터베이스: H2 (개발/테스트용 인메모리 DB)
- 영속성: MyBatis
- 테스트: JUnit 5, REST Assured, AssertJ

## 2. 도메인 설계

### 2.1 주요 도메인 객체
1. `Order` (주문)
   - id: 주문 식별자
   - status: 주문 상태 (CREATED)
   - items: 주문 항목 목록

2. `OrderItem` (주문 항목)
   - productName: 상품명
   - quantity: 수량

3. `OrderStatus` (주문 상태)
   - CREATED: 주문 생성됨

### 2.2 비즈니스 규칙
1. 주문 생성 시
   - 최소 1개 이상의 주문 항목이 필요합니다.
   - 주문 상태는 자동으로 'CREATED'로 설정됩니다.

2. 주문 수정 시
   - 존재하는 주문만 수정 가능합니다.
   - 주문 항목 전체를 새로운 항목으로 교체합니다.

## 3. 아키텍처

### 3.1 헥사고날 아키텍처 구조
```
src/main/java/com/example/hexagonal/
├── domain/           # 도메인 객체
│   ├── Order.java
│   ├── OrderItem.java
│   └── OrderStatus.java
├── application/      # 유스케이스와 포트
│   ├── port/
│   │   ├── in/      # 입력 포트 (유스케이스)
│   │   └── out/     # 출력 포트 (리포지토리)
│   └── service/     # 유스케이스 구현체
└── adapter/         # 어댑터
    ├── in/          # 입력 어댑터 (웹)
    └── out/         # 출력 어댑터 (MyBatis)
```

### 3.2 계층별 역할
1. Domain Layer
   - 순수한 도메인 로직만 포함
   - 외부 의존성이 없는 POJO로 구현
   
2. Application Layer
   - 유스케이스 인터페이스 정의 (port/in)
   - 저장소 인터페이스 정의 (port/out)
   - 실제 비즈니스 로직 구현 (service)

3. Adapter Layer
   - Web Adapter: REST API 구현
   - Persistence Adapter: MyBatis 기반 데이터 저장소 구현

## 4. API 명세

### 4.1 주문 생성
- **POST /orders**
- Request Body:
```json
[
  {
    "productName": "상품명",
    "quantity": 수량
  }
]
```
- Response: 201 Created
```json
{
  "id": "주문ID",
  "status": "CREATED",
  "items": [
    {
      "productName": "상품명",
      "quantity": 수량
    }
  ]
}
```

### 4.2 주문 조회
- **GET /orders/{id}**
- Response: 200 OK
```json
{
  "id": "주문ID",
  "status": "CREATED",
  "items": [
    {
      "productName": "상품명",
      "quantity": 수량
    }
  ]
}
```

### 4.3 주문 목록 조회
- **GET /orders**
- Response: 200 OK
```json
[
  {
    "id": "주문ID",
    "status": "CREATED",
    "items": [...]
  }
]
```

### 4.4 주문 수정
- **PUT /orders/{id}**
- Request Body: 주문 생성과 동일
- Response: 200 OK 또는 404 Not Found

## 5. 테스트 전략

### 5.1 도메인 테스트
- 위치: `src/test/java/.../domain/`
- 목적: 도메인 객체의 비즈니스 규칙 검증
- 도구: JUnit 5, AssertJ
- 대상:
  - `OrderTest`: 주문 생성, 상태 변경 등
  - `OrderItemTest`: 주문 항목 생성, 속성 변경 등

### 5.2 인수 테스트
- 위치: `src/test/java/.../acceptance/`
- 목적: 실제 사용자 관점에서 시스템 전체 동작 검증
- 도구: REST Assured
- 시나리오:
  - 주문 생성 성공/실패
  - 주문 조회 성공/실패
  - 주문 목록 조회
  - 주문 수정 성공/실패