# LOL 챗봇 Spring Boot 백엔드 실행 안내

## 1. 참조 문서

- `Reference/SpringBoot_Backend_DB_설계_초안.md`
- `Reference/API_명세서_정리_v2.md`
- `Reference/요구기능_정리_v2.md`

## 2. 실행 전 확인

FastAPI 서버가 먼저 `http://localhost:8000`에서 실행되어 있어야 한다.

## 3. Gradle 실행

```powershell
cd lol_chatbot_back
.\gradlew.bat bootRun
```

## 4. 주요 설정

| 항목 | 기본값 |
| --- | --- |
| Spring Boot 포트 | `8080` |
| FastAPI Base URL | `http://localhost:8000` |

FastAPI 주소를 바꾸려면 다음 환경 변수를 사용한다.

```powershell
$env:FASTAPI_BASE_URL="http://localhost:8000"
.\gradlew.bat bootRun
```
