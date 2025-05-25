# 헥사고날 아키텍처 주문 관리 시스템

이 프로젝트는 헥사고날 아키텍처와 DDD(도메인 주도 설계)를 학습하기 위한 예제 프로젝트입니다.
주문 생성, 조회, 수정 등 기본적인 비즈니스 기능을 통해 클린 아키텍처의 실제 구현을 보여줍니다.

## 기술 스택

- Java 21
- Spring Boot 3.2.3
- Gradle
- H2 Database
- MyBatis
- JUnit 5
- REST Assured
- AssertJ

## 시작하기

### 필수 조건

- Java 21 이상
- Gradle 8.x

### 프로젝트 실행

1. 프로젝트 클론
```bash
git clone https://github.com/your-username/hexagonal-test.git
cd hexagonal-test
```

2. 애플리케이션 실행
```bash
./gradlew bootRun
```

3. 테스트 실행
```bash
./gradlew test
```

## 프로젝트 구조

```
src/
├── main/
│   └── java/
│       └── com/
│           └── example/
│               └── hexagonal/
│                   ├── domain/           # 도메인 객체
│                   ├── application/      # 유스케이스, 포트
│                   └── adapter/          # 어댑터 (웹, MyBatis 저장소)
└── test/
    └── java/
        └── com/
            └── example/
                └── hexagonal/
                    ├── domain/           # 도메인 테스트
                    └── acceptance/       # 인수 테스트
```

## API 엔드포인트

### 주문 생성
```http
POST /orders
Content-Type: application/json

[
  {
    "productName": "상품1",
    "quantity": 1
  }
]
```

### 주문 조회
```http
GET /orders/{id}
```

### 주문 목록 조회
```http
GET /orders
```

### 주문 수정
```http
PUT /orders/{id}
Content-Type: application/json

[
  {
    "productName": "수정된상품1",
    "quantity": 2
  }
]
```

## 테스트

프로젝트는 두 가지 수준의 테스트를 포함합니다:

1. 도메인 테스트
   - `OrderTest`: 주문 도메인 객체 테스트
   - `OrderItemTest`: 주문 항목 도메인 객체 테스트

2. 인수 테스트
   - `OrderAcceptanceTest`: REST API 엔드포인트 테스트

## 아키텍처

이 프로젝트는 헥사고날 아키텍처를 따릅니다:

1. Domain Layer (내부)
   - 순수한 도메인 로직
   - 외부 의존성 없음

2. Application Layer (포트)
   - 유스케이스 정의
   - 포트 인터페이스

3. Adapter Layer (외부)
   - REST API 컨트롤러
   - 저장소 구현

## 개발 가이드

### 새로운 기능 추가하기

1. 도메인 객체 추가/수정
   - `domain` 패키지에 도메인 클래스 추가
   - 비즈니스 규칙을 도메인 객체에 구현

2. 유스케이스 정의
   - `application/port/in`에 유스케이스 인터페이스 추가
   - `application/service`에 구현체 작성

3. 어댑터 구현
   - `adapter/in/web`에 컨트롤러 추가
   - `adapter/out/persistence`에 MyBatis 매퍼 및 XML 작성

4. 테스트 작성
   - 도메인 테스트 작성
   - 인수 테스트 작성

### 코드 컨벤션

- 도메인 객체는 항상 불변성을 유지
- 비즈니스 로직은 도메인 계층에 구현
- 각 계층은 안쪽 방향으로만 의존
- 테스트 코드는 Given/When/Then 패턴 사용

## 라이선스

이 프로젝트는 MIT 라이선스를 따릅니다. 자세한 내용은 [LICENSE](LICENSE) 파일을 참조하세요. 